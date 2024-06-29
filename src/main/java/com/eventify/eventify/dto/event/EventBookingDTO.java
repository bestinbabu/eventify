package com.eventify.eventify.dto.event;


import com.eventify.eventify.dto.user.UserProfileDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventBookingDTO {


    @NotNull(message = "User details cannot be null")
    private UserProfileDto user;

    @NotNull(message = "Event details cannot be null")
    private NewEventDto event;

    @NotNull(message = "Number of tickets cannot be null")
    private Integer numberOfTickets;

    @NotNull(message = "Total price cannot be null")
    private Double totalPrice;


}
