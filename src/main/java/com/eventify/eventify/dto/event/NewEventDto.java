package com.eventify.eventify.dto.event;

import com.eventify.eventify.entity.event.EventCategory;
import com.eventify.eventify.entity.user.UserProfile;
import com.eventify.eventify.entity.user.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NewEventDto {
    @NotEmpty
    private String eventName;

    @NotEmpty(message = "Description cannot be empty")
    private String description;

    @Future
    private LocalDateTime startDate;

    @Future
    private LocalDateTime endDate;

    @NotNull(message = "Number of seats cannot be null")
    private int numberOfSeats;

    @NotNull
    private double pricingPerSeat;

    @Valid
    private List<EventCategory> categories;

    @Valid
    private UserProfile organizer;

    @Valid
    private List<UserRole> eventManagers;

    @Valid
    private List<UserProfile> attendees;
}

