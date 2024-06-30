package com.eventify.eventify.mapper;

import com.eventify.eventify.dto.user.RegistrationRequestDto;
import com.eventify.eventify.entity.user.User;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserMapper {
    public  static User mapToUser(RegistrationRequestDto registrationRequestDTO, User user)
    {
        user.setEmail(registrationRequestDTO.getEmail());
        return user;

    }

}
