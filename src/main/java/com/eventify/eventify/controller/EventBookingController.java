package com.eventify.eventify.controller;


import com.eventify.eventify.dto.ResponseDto;
import com.eventify.eventify.dto.event.EventBookingDto;
import com.eventify.eventify.dto.event.EventBookingResponseDto;
import com.eventify.eventify.service.impl.EventBookingServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class EventBookingController {

    private final EventBookingServiceImpl eventBookingService;



    @PostMapping("/book-event")
    public ResponseEntity<ResponseDto> bookEvent(Long eventId,EventBookingDto eventBookingDto) {
        EventBookingResponseDto eventBookingResponseDto = eventBookingService.bookEvent(eventId,eventBookingDto);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK, "Event booked successfully", eventBookingResponseDto));

    }

    @PostMapping("/cancel-booking")
    public ResponseEntity<ResponseDto> cancelBooking(Long bookingId) {
        eventBookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK, "Booking cancelled successfully", null));
    }

    @GetMapping("/bookings")
    public ResponseEntity<ResponseDto> getBookings() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<EventBookingResponseDto> bookings = eventBookingService.getUserBookings(pageable);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK, "Bookings fetched successfully", bookings));
    }
}
