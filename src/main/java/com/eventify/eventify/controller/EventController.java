package com.eventify.eventify.controller;

import com.eventify.eventify.dto.ResponseDto;
import com.eventify.eventify.dto.event.EventDto;
import com.eventify.eventify.dto.event.EventResponseDto;
import com.eventify.eventify.service.impl.EventServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class EventController {

    private final EventServiceImpl eventService;

    @PostMapping("/create-event")
    public ResponseEntity<ResponseDto> createEvent(@Valid @RequestBody EventDto eventDto) {

        EventResponseDto eventResponseDto = eventService.createEvent(eventDto);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.CREATED, "Event created successfully", eventResponseDto));
    }

    @PutMapping("/update-event/{eventId}")
    public ResponseEntity<ResponseDto> updateEvent(@PathVariable Long eventId, @Valid @RequestBody EventDto eventDto) {
        EventResponseDto eventResponseDto = eventService.updateEvent(eventId, eventDto);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK, "Event updated successfully", eventResponseDto));
    }

    @DeleteMapping("/delete-event/{eventId}")
    public ResponseEntity<ResponseDto> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK, "Event deleted successfully", null));
    }

    @GetMapping("/events")
    public ResponseEntity<ResponseDto> getEvents() {
        List<EventResponseDto> events = eventService.getAllEvents();
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK, "Events fetched successfully", events));
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<ResponseDto> getEventById(@PathVariable Long eventId) {
        EventResponseDto event = eventService.getEventById(eventId);
        return ResponseEntity.ok(new ResponseDto<>(HttpStatus.OK, "Event fetched successfully", event));
    }
}