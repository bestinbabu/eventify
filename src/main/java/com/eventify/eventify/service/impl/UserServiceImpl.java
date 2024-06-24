package com.eventify.eventify.service.impl;

import com.eventify.eventify.dto.user.RegistrationRequest;
import com.eventify.eventify.entity.user.User;
import com.eventify.eventify.entity.user.UserRole;
import com.eventify.eventify.exception.UserAlreadyExistsException;
import com.eventify.eventify.mapper.UserMapper;
import com.eventify.eventify.repository.UserRepository;
import com.eventify.eventify.repository.UserRoleRepository;
import com.eventify.eventify.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;

    @Override
    public void registerUser(RegistrationRequest registrationRequestDTO) {

        if (userRepository.findByEmail(registrationRequestDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = UserMapper.mapToUser(registrationRequestDTO, new User());
        newUser.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));

        UserRole defaultRole = userRoleRepository.findByName("ATTENDEE");
        UserRole eventManager = userRoleRepository.findByName("EVENT_MANAGER");
        newUser.setRoles(List.of(defaultRole,eventManager));
        userRepository.save(newUser);


    }


}