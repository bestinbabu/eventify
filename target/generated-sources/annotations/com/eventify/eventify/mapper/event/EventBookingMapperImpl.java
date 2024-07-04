package com.eventify.eventify.mapper.event;

import com.eventify.eventify.dto.event.EventBookingDto;
import com.eventify.eventify.dto.event.EventBookingResponseDto;
import com.eventify.eventify.entity.event.Event;
import com.eventify.eventify.entity.event.EventBooking;
import com.eventify.eventify.enums.BookingStatus;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T13:36:57+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22 (Oracle Corporation)"
)
@Component
public class EventBookingMapperImpl implements EventBookingMapper {

    @Override
    public EventBookingResponseDto toDto(EventBooking eventBooking) {
        if ( eventBooking == null ) {
            return null;
        }

        Long eventId = null;
        String eventName = null;
        Long id = null;
        Integer numberOfTickets = null;
        Double totalPrice = null;
        BookingStatus status = null;

        eventId = eventBookingEventId( eventBooking );
        eventName = eventBookingEventName( eventBooking );
        id = eventBooking.getId();
        numberOfTickets = eventBooking.getNumberOfTickets();
        totalPrice = eventBooking.getTotalPrice();
        status = eventBooking.getStatus();

        LocalDateTime bookingDate = null;

        EventBookingResponseDto eventBookingResponseDto = new EventBookingResponseDto( id, eventId, eventName, bookingDate, numberOfTickets, totalPrice, status );

        return eventBookingResponseDto;
    }

    @Override
    public EventBooking toEntity(EventBookingDto dto) {
        if ( dto == null ) {
            return null;
        }

        EventBooking.EventBookingBuilder eventBooking = EventBooking.builder();

        eventBooking.numberOfTickets( dto.getNumberOfTickets() );

        return eventBooking.build();
    }

    private Long eventBookingEventId(EventBooking eventBooking) {
        if ( eventBooking == null ) {
            return null;
        }
        Event event = eventBooking.getEvent();
        if ( event == null ) {
            return null;
        }
        Long id = event.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String eventBookingEventName(EventBooking eventBooking) {
        if ( eventBooking == null ) {
            return null;
        }
        Event event = eventBooking.getEvent();
        if ( event == null ) {
            return null;
        }
        String name = event.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
