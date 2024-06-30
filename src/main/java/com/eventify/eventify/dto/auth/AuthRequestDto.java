package com.eventify.eventify.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequestDto {

    @Email(message = "Invalid email format")  // Validate email format
    @NotEmpty(message = "Email address cannot be empty")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")  // Validate password length
    @NotEmpty(message = "Password cannot be empty")
    private String password;

}

