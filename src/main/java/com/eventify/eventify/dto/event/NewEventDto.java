package com.eventify.eventify.dto.event;

import java.time.LocalDateTime;
import java.util.List;

public class NewEventDto {

    private String eventName;
    private String description;
    private LocalDateTime eventTime;
    private Integer numberOfSeats;
    private Double pricingPerSeat;
    private List<String> categories;
}
