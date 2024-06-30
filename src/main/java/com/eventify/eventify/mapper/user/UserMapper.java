package com.eventify.eventify.mapper.user;

import com.eventify.eventify.dto.user.RegistrationRequestDto;
import com.eventify.eventify.entity.user.User;

public class UserMapper {
    public  static User mapToUser(RegistrationRequestDto registrationRequestDTO, User user)
    {
        user.setEmail(registrationRequestDTO.getEmail());
        return user;

    }

}
