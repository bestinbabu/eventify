package com.eventify.eventify.mapper.event;

import com.eventify.eventify.dto.event.EventResponseDto;
import com.eventify.eventify.dto.event.EventDto;
import com.eventify.eventify.entity.event.Event;
import com.eventify.eventify.mapper.user.UserProfileMapper;
import com.eventify.eventify.mapper.user.UserRoleMapper;
import org.mapstruct.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {UserProfileMapper.class, EventCategoryMapper.class, UserRoleMapper.class})
public interface EventMapper {

    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "eventManagers", ignore = true)
    @Mapping(target = "attendees", ignore = true)
    Event createEventDtoToEvent(EventDto eventDto);



    @Mapping(target = "organizer", source = "organizer", qualifiedByName = "userProfileToBaseUserProfileDto")
    @Mapping(target = "eventManagers", source = "eventManagers", qualifiedByName = "userProfileToBaseUserProfileDto")
    @Mapping(target = "categories", source = "categories")
    EventResponseDto eventToEventResponseDto(Event event);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "eventManagers", ignore = true)
    @Mapping(target = "attendees", ignore = true)
    void updateEventFromEventDto(EventDto EventDto, @MappingTarget Event event);
}