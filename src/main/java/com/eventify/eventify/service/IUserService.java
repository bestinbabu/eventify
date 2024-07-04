package com.eventify.eventify.service;

import com.eventify.eventify.dto.user.RegistrationRequestDto;
import com.eventify.eventify.dto.user.UserProfileDto;

public interface IUserService {

    void registerUser(RegistrationRequestDto registrationRequestDTO);

    UserProfileDto createProfile(UserProfileDto userProfileDto);

    UserProfileDto getProfile();

    UserProfileDto updateProfile(UserProfileDto userProfileDto);


}
