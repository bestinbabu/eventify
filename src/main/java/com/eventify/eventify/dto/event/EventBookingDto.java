package com.eventify.eventify.dto.event;


import com.eventify.eventify.dto.user.UserDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventBookingDto {


    @NotNull(message = "User details cannot be null")
    private UserDto user;

    @NotNull(message = "Event details cannot be null")
    private EventDetailsDto event;

    @NotNull(message = "Number of tickets cannot be null")
    private Integer numberOfTickets;

    @NotNull(message = "Total price cannot be null")
    private Double totalPrice;


}
