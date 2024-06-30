package com.eventify.eventify.controller;

import com.eventify.eventify.dto.ResponseDto;
import com.eventify.eventify.dto.event.NewEventDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class EventController {

    @PostMapping("/create-event")
    public ResponseEntity<ResponseDto> createEvent(@Valid @RequestBody NewEventDto newEventDto) {
        return null;
    }
}