package com.eventify.eventify.dto.user.baseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseUserProfileDto {
    private String fullName;
    private String phoneNumber;
    private String email;
}
