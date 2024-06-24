package com.eventify.eventify.mapper;

import com.eventify.eventify.dto.user.RegistrationRequest;
import com.eventify.eventify.entity.user.User;

public class UserMapper {
    public  static User mapToUser(RegistrationRequest registrationRequestDTO, User user)
    {
        user.setEmail(registrationRequestDTO.getEmail());
        return user;

    }

}
