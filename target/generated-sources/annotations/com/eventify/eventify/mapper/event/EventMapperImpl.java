package com.eventify.eventify.mapper.event;

import com.eventify.eventify.dto.event.EventDto;
import com.eventify.eventify.dto.event.EventResponseDto;
import com.eventify.eventify.dto.user.CategoryDto;
import com.eventify.eventify.dto.user.baseDto.BaseUserProfileDto;
import com.eventify.eventify.entity.event.Event;
import com.eventify.eventify.entity.event.EventCategory;
import com.eventify.eventify.entity.user.UserProfile;
import com.eventify.eventify.mapper.user.UserProfileMapper;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T13:36:57+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Autowired
    private UserProfileMapper userProfileMapper;
    @Autowired
    private EventCategoryMapper eventCategoryMapper;

    @Override
    public Event createEventDtoToEvent(EventDto eventDto) {
        if ( eventDto == null ) {
            return null;
        }

        Event event = new Event();

        event.setName( eventDto.getName() );
        event.setDescription( eventDto.getDescription() );
        event.setStartDate( eventDto.getStartDate() );
        event.setEndDate( eventDto.getEndDate() );
        event.setRegistrationDeadline( eventDto.getRegistrationDeadline() );
        event.setNumberOfSeats( eventDto.getNumberOfSeats() );
        event.setPricingPerSeat( eventDto.getPricingPerSeat() );

        return event;
    }

    @Override
    public EventResponseDto eventToEventResponseDto(Event event) {
        if ( event == null ) {
            return null;
        }

        EventResponseDto eventResponseDto = new EventResponseDto();

        eventResponseDto.setOrganizer( userProfileMapper.userProfileToBaseUserProfileDto( event.getOrganizer() ) );
        eventResponseDto.setEventManagers( userProfileSetToBaseUserProfileDtoSet( event.getEventManagers() ) );
        eventResponseDto.setCategories( eventCategorySetToCategoryDtoSet( event.getCategories() ) );
        eventResponseDto.setId( event.getId() );
        eventResponseDto.setName( event.getName() );
        eventResponseDto.setDescription( event.getDescription() );
        eventResponseDto.setStartDate( event.getStartDate() );
        eventResponseDto.setEndDate( event.getEndDate() );
        eventResponseDto.setNumberOfSeats( event.getNumberOfSeats() );
        eventResponseDto.setPricingPerSeat( event.getPricingPerSeat() );
        eventResponseDto.setRegistrationDeadline( event.getRegistrationDeadline() );

        return eventResponseDto;
    }

    @Override
    public void updateEventFromEventDto(EventDto EventDto, Event event) {
        if ( EventDto == null ) {
            return;
        }

        if ( EventDto.getName() != null ) {
            event.setName( EventDto.getName() );
        }
        if ( EventDto.getDescription() != null ) {
            event.setDescription( EventDto.getDescription() );
        }
        if ( EventDto.getStartDate() != null ) {
            event.setStartDate( EventDto.getStartDate() );
        }
        if ( EventDto.getEndDate() != null ) {
            event.setEndDate( EventDto.getEndDate() );
        }
        if ( EventDto.getRegistrationDeadline() != null ) {
            event.setRegistrationDeadline( EventDto.getRegistrationDeadline() );
        }
        event.setNumberOfSeats( EventDto.getNumberOfSeats() );
        event.setPricingPerSeat( EventDto.getPricingPerSeat() );
    }

    protected Set<BaseUserProfileDto> userProfileSetToBaseUserProfileDtoSet(Set<UserProfile> set) {
        if ( set == null ) {
            return null;
        }

        Set<BaseUserProfileDto> set1 = new LinkedHashSet<BaseUserProfileDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( UserProfile userProfile : set ) {
            set1.add( userProfileMapper.userProfileToBaseUserProfileDto( userProfile ) );
        }

        return set1;
    }

    protected Set<CategoryDto> eventCategorySetToCategoryDtoSet(Set<EventCategory> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDto> set1 = new LinkedHashSet<CategoryDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( EventCategory eventCategory : set ) {
            set1.add( eventCategoryMapper.categoryToCategoryDto( eventCategory ) );
        }

        return set1;
    }
}
