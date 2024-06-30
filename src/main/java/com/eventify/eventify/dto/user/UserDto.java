package com.eventify.eventify.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserDto {

    @NotEmpty(message = "Username cannot be empty")
    private String username;


    @Pattern(regexp = "^\\d+$", message = "Phone number must be numeric")
    private String phoneNumber;
}
