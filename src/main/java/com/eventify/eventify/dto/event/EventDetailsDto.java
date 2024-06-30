package com.eventify.eventify.dto.event;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDetailsDto {


    @NotEmpty(message = "Event name cannot be empty")
    private String eventName;

    private String description;

    @NotNull(message = "Event time cannot be null")
    private LocalDateTime eventTime;

    @NotNull(message = "Number of seats cannot be null")
    private Integer numberOfSeats;

    @NotNull(message = "Event pricing cannot be null")
    private Double pricingPerSeat;

    private List<String> categories;
}

