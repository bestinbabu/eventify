package com.eventify.eventify.mapper.event;

import com.eventify.eventify.dto.event.EventBookingDto;
import com.eventify.eventify.dto.event.EventBookingResponseDto;
import com.eventify.eventify.entity.event.EventBooking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventBookingMapper {


    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "eventName", source = "event.name")
    EventBookingResponseDto toDto(EventBooking eventBooking);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userProfile", ignore = true)
    @Mapping(target = "event", ignore = true)

    EventBooking toEntity(EventBookingDto dto);
}
