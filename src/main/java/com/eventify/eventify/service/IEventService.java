package com.eventify.eventify.service;

import com.eventify.eventify.dto.event.EventDto;
import com.eventify.eventify.dto.event.EventResponseDto;

import java.util.List;

public interface IEventService {

    EventResponseDto createEvent(EventDto eventDto);
    EventResponseDto updateEvent(Long eventId,EventDto eventDto);
    void deleteEvent(Long eventId);
    List<EventResponseDto> getAllEvents();
    EventResponseDto getEventById(Long eventId);

}
