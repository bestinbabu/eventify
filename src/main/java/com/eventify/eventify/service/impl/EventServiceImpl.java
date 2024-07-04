package com.eventify.eventify.service.impl;

import com.eventify.eventify.dto.event.EventDto;
import com.eventify.eventify.dto.event.EventResponseDto;
import com.eventify.eventify.entity.event.Event;
import com.eventify.eventify.entity.event.EventCategory;
import com.eventify.eventify.entity.user.User;
import com.eventify.eventify.entity.user.UserProfile;
import com.eventify.eventify.exception.BadRequestException;
import com.eventify.eventify.exception.ForbiddenException;
import com.eventify.eventify.mapper.event.EventMapper;
import com.eventify.eventify.repository.event.EventCategoryRepository;
import com.eventify.eventify.repository.event.EventRepository;
import com.eventify.eventify.repository.user.UserProfileRepository;
import com.eventify.eventify.repository.user.UserRepository;
import com.eventify.eventify.service.IEventService;
import com.eventify.eventify.utility.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public  class EventServiceImpl implements IEventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final SecurityUtils securityUtils;
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final EventCategoryRepository eventCategoryRepository;

    @Override
    public EventResponseDto createEvent(EventDto eventDto) {
        Event event = eventMapper.createEventDtoToEvent(eventDto);

        setOrganizer(event);
        setCategories(event, eventDto.getCategories());
        setEventManagers(event, eventDto.getEventManagerEmails());

        Event savedEvent = eventRepository.save(event);
        return eventMapper.eventToEventResponseDto(savedEvent);
    }

    private void setOrganizer(Event event) {
        UserProfile organizer = securityUtils.getCurrentUserProfile(userRepository);
        event.setOrganizer(organizer);
        organizer.getOrganizedEvents().add(event);
    }

    private void setCategories(Event event, Set<String> categoryNames) {
        if (!categoryNames.isEmpty()) {
            Set<EventCategory> categories = eventCategoryRepository.findAllByNameIn(categoryNames);
            if (categories.size() != categoryNames.size()) {
                Set<String> notFoundCategories = new HashSet<>(categoryNames);
                notFoundCategories.removeAll(categories.stream().map(EventCategory::getName).collect(Collectors.toSet()));
                throw new BadRequestException("The following categories do not exist: " + String.join(", ", notFoundCategories));
            }
            event.setCategories(categories);
        }
    }

    private void setEventManagers(Event event, Set<String> managerEmails) {
        if (!managerEmails.isEmpty()) {
            Set<UserProfile> eventManagers = validateAndGetEventManagers(managerEmails);
            event.setEventManagers(eventManagers);
            eventManagers.forEach(manager -> manager.getManagedEvents().add(event));
        }
    }

    private Set<UserProfile> validateAndGetEventManagers(Set<String> managerEmails) {
        Set<User> potentialManagers = userRepository.findAllByEmailIn(managerEmails);
        if (potentialManagers.size() != managerEmails.size()) {
            Set<String> notFoundEmails = new HashSet<>(managerEmails);
            notFoundEmails.removeAll(potentialManagers.stream().map(User::getEmail).collect(Collectors.toSet()));
            throw new BadRequestException("The following email(s) do not exist: " + String.join(", ", notFoundEmails));
        }

        Set<UserProfile> eventManagers = potentialManagers.stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("Event Manager")))
                .map(User::getUserProfile)
                .collect(Collectors.toSet());

        if (eventManagers.size() != managerEmails.size()) {
            Set<String> nonManagerEmails = potentialManagers.stream()
                    .filter(user -> user.getRoles().stream().noneMatch(role -> role.getName().equals("Event Manager")))
                    .map(User::getEmail)
                    .collect(Collectors.toSet());
            throw new BadRequestException("The following email(s) are not associated with an EVENT_MANAGER role: " + String.join(", ", nonManagerEmails));
        }

        return eventManagers;
    }

    @Override
    public EventResponseDto updateEvent(Long eventId, EventDto eventDto) {

        Event existingEvent = eventRepository.findById(eventId)
                .orElseThrow(() -> new BadRequestException("Event not found"));

        UserProfile currentUserProfile = securityUtils.getCurrentUserProfile(userRepository);
        if (!existingEvent.getOrganizer().getId().equals(currentUserProfile.getId()) && !existingEvent.getEventManagers().contains(currentUserProfile)) {
            throw new ForbiddenException("You are not authorized to update this event");
        }

        eventMapper.updateEventFromEventDto(eventDto, existingEvent);

        if (!eventDto.getCategories().isEmpty()) {
            updateCategories(existingEvent, eventDto.getCategories());
        }


        if (!eventDto.getEventManagerEmails().isEmpty()) {
            updateEventManagers(existingEvent, eventDto.getEventManagerEmails());
        }


        Event updatedEvent = eventRepository.save(existingEvent);
        return eventMapper.eventToEventResponseDto(updatedEvent);
    }



private void updateCategories(Event event, Set<String> categoryNames) {
    if (categoryNames.isEmpty()) {
        event.getCategories().clear();
    } else {
        Set<EventCategory> categories = eventCategoryRepository.findAllByNameIn(categoryNames);
        if (categories.size() != categoryNames.size()) {
            Set<String> notFoundCategories = new HashSet<>(categoryNames);
            notFoundCategories.removeAll(categories.stream().map(EventCategory::getName).collect(Collectors.toSet()));
            throw new BadRequestException("The following categories do not exist: " + String.join(", ", notFoundCategories));
        }
        event.setCategories(categories);
    }
}

private void updateEventManagers(Event event, Set<String> managerEmails) {
    if (managerEmails.isEmpty()) {
        event.getEventManagers().forEach(manager -> manager.getManagedEvents().remove(event));
        event.getEventManagers().clear();
    } else {
        Set<UserProfile> newEventManagers = validateAndGetEventManagers(managerEmails);

        // Remove event from managers that are no longer associated
        event.getEventManagers().stream()
                .filter(manager -> !newEventManagers.contains(manager))
                .forEach(manager -> manager.getManagedEvents().remove(event));

        // Add event to new managers
        newEventManagers.forEach(manager -> manager.getManagedEvents().add(event));

        event.setEventManagers(newEventManagers);
        }
    }

    @Override
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BadRequestException("Event not found with id: " + eventId));

        UserProfile currentUser = securityUtils.getCurrentUserProfile(userRepository);
        if (!currentUser.equals(event.getOrganizer())) {
            throw new ForbiddenException("Only the event organizer can delete this event");
        }

        if (event.getStartDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Cannot delete events that have already started or ended");
        }

        event.getOrganizer().getOrganizedEvents().remove(event);
        event.getEventManagers().forEach(manager -> manager.getManagedEvents().remove(event));
        event.getCategories().clear();

        eventRepository.delete(event);
    }

    @Override
    public List<EventResponseDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(eventMapper::eventToEventResponseDto).collect(Collectors.toList());
    }


    @Override
    public EventResponseDto getEventById(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new BadRequestException("Event not found with id: " + eventId));
        return eventMapper.eventToEventResponseDto(event);
    }



}
