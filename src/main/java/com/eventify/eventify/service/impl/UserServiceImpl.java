package com.eventify.eventify.service.impl;

import com.eventify.eventify.dto.user.RegistrationRequest;
import com.eventify.eventify.entity.user.User;
import com.eventify.eventify.entity.user.UserRole;
import com.eventify.eventify.exception.UserAlreadyExistsException;
import com.eventify.eventify.mapper.UserMapper;
import com.eventify.eventify.repository.UserRepository;
import com.eventify.eventify.repository.UserRoleRepository;
import com.eventify.eventify.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void registerUser(RegistrationRequest registrationRequestDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(registrationRequestDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = UserMapper.mapToUser(registrationRequestDTO, new User());
        newUser.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));

        Optional <UserRole> defaultRole = userRoleRepository.findByName("ATTENDEE");
        Optional <UserRole> eventManager = userRoleRepository.findByName("EVENT MANAGER");

        if (defaultRole.isPresent() && eventManager.isPresent()){
            newUser.setRoles(List.of(defaultRole.get(), eventManager.get()));
        }
        userRepository.save(newUser);

    }


}
