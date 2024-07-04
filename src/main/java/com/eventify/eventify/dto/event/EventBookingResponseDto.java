package com.eventify.eventify.dto.event;

import com.eventify.eventify.enums.BookingStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Data
public class EventBookingResponseDto {
    private final Long id;
    private final Long eventId;
    private final String eventName;
    private final LocalDateTime bookingDate;
    private final Integer numberOfTickets;
    private final Double totalPrice;
    private final BookingStatus status;
}

