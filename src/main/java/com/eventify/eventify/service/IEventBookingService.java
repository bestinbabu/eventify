package com.eventify.eventify.service;

import com.eventify.eventify.dto.event.EventBookingDto;
import com.eventify.eventify.dto.event.EventBookingResponseDto;
import com.eventify.eventify.dto.event.EventResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEventBookingService {

    EventBookingResponseDto bookEvent(Long eventId, EventBookingDto eventBookingDto);
    void cancelBooking(Long bookingId);
    Page<EventBookingResponseDto> getUserBookings(Pageable pageable);
}

