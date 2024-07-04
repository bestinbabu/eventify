package com.eventify.eventify.dto.event;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class EventDto {

        @NotNull
        private String name;

        @NotNull(message = "Description cannot be empty")
        private String description;

        @Future
        private LocalDateTime startDate;

        @Future
        private LocalDateTime endDate;

        @NotNull(message = "Number of seats cannot be null")
        private int numberOfSeats;

        @NotNull
        private double pricingPerSeat;

        @NotNull
        private LocalDateTime registrationDeadline;

        @NotNull
        private Set<String> categories;

        private Set<String> eventManagerEmails;


}
