package com.eventify.eventify.service.impl;

import com.eventify.eventify.dto.event.EventBookingDto;
import com.eventify.eventify.dto.event.EventBookingResponseDto;
import com.eventify.eventify.entity.event.Event;
import com.eventify.eventify.entity.event.EventBooking;
import com.eventify.eventify.entity.user.UserProfile;
import com.eventify.eventify.enums.BookingStatus;
import com.eventify.eventify.exception.BadRequestException;
import com.eventify.eventify.exception.ForbiddenException;
import com.eventify.eventify.mapper.event.EventBookingMapper;
import com.eventify.eventify.mapper.event.EventMapper;
import com.eventify.eventify.repository.event.EventBookingRepository;
import com.eventify.eventify.repository.event.EventRepository;
import com.eventify.eventify.repository.user.UserProfileRepository;
import com.eventify.eventify.repository.user.UserRepository;
import com.eventify.eventify.service.IEventBookingService;
import com.eventify.eventify.utility.SecurityUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class EventBookingServiceImpl implements IEventBookingService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EventBookingMapper eventBookingMapper;
    private final SecurityUtils securityUtils;
    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final EventBookingRepository eventBookingRepository;

    @Override
    public EventBookingResponseDto bookEvent(Long eventId, EventBookingDto eventBookingDto) {

        UserProfile userProfile = securityUtils.getCurrentUserProfile(userRepository);
        Event event = eventRepository.findById(eventId).orElseThrow(() -> new BadRequestException("Event not found"));
        if(event.getOrganizer().getId().equals(userProfile.getId())) {
            throw new ForbiddenException("You can't book your own event");
        }
        validateBooking(event, userProfile, eventBookingDto.getNumberOfTickets());

        Double totalPrice = calculateTotalPrice(event, eventBookingDto.getNumberOfTickets());
        EventBooking booking = EventBooking.builder()
                .userProfile(userProfile)
                .event(event)
                .numberOfTickets(eventBookingDto.getNumberOfTickets())
                .totalPrice(totalPrice)
                .status(BookingStatus.CONFIRMED)
                .build();

        EventBooking savedBooking = eventBookingRepository.save(booking);

        return eventBookingMapper.toDto(savedBooking);
    }

    public void validateBooking(Event event, UserProfile userProfile, Integer numberOfTickets) {
        if (event.getStartDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Cannot book events that have already started or ended");
        }

        if (event.getRegistrationDeadline() != null &&
                event.getRegistrationDeadline().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Registration deadline has passed");
        }

        if (event.getOrganizer().getId().equals(userProfile.getId())) {
            throw new BadRequestException("You can't book your own event");
        }

        if (eventBookingRepository.findByEventIdAndUserProfile(event.getId(), userProfile).isPresent()) {
            throw new BadRequestException("User has already booked this event");
        }


        if (event.getNumberOfSeats() < numberOfTickets) {
            throw new BadRequestException("Insufficient number of seats");
        }

    }

    public Double calculateTotalPrice(Event event, Integer numberOfTickets) {
        return event.getPricingPerSeat() * numberOfTickets;
    }

    @Override
    public void cancelBooking(Long bookingId) {
        UserProfile userProfile = securityUtils.getCurrentUserProfile(userRepository);

        EventBooking booking = eventBookingRepository.findById(bookingId).orElseThrow(() -> new BadRequestException("Booking not found"));

        if (!booking.getUserProfile().getId().equals(userProfile.getId())) {
            throw new ForbiddenException("You can only cancel your own bookings");
        }

        if (booking.getStatus().equals(BookingStatus.CANCELLED)) {
            throw new BadRequestException("Booking already cancelled");
        }

        if (booking.getStatus().equals(BookingStatus.CONFIRMED)) {
            throw new BadRequestException("Booking already completed");
        }

        if (booking.getEvent().getStartDate().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("Cannot cancel booking for an event that has already started");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        eventBookingRepository.save(booking);
    }

    @Override
    public Page<EventBookingResponseDto> getUserBookings(Pageable pageable) {
        UserProfile userProfile = securityUtils.getCurrentUserProfile(userRepository);
        Page<EventBooking> bookings = eventBookingRepository.findAllByUserProfile(userProfile, pageable);
        return bookings.map(eventBookingMapper::toDto);
    }
}

