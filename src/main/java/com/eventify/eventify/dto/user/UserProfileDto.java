package com.eventify.eventify.dto.user;

import com.eventify.eventify.dto.user.baseDto.BaseUserProfileDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserProfileDto extends BaseUserProfileDto {

    private String firstName;
    private String lastName;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
