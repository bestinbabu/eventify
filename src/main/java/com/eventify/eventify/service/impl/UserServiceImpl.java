package com.eventify.eventify.service.impl;

import com.eventify.eventify.dto.user.RegistrationRequestDto;
import com.eventify.eventify.entity.user.User;
import com.eventify.eventify.entity.user.UserRole;
import com.eventify.eventify.exception.UserAlreadyExistsException;
import com.eventify.eventify.mapper.user.UserMapper;
import com.eventify.eventify.repository.user.UserRepository;
import com.eventify.eventify.repository.user.UserRoleRepository;
import com.eventify.eventify.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void registerUser(RegistrationRequestDto registrationRequestDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(registrationRequestDTO.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = UserMapper.mapToUser(registrationRequestDTO, new User());
        newUser.setPassword(passwordEncoder.encode(registrationRequestDTO.getPassword()));

        Optional <UserRole> defaultRole = userRoleRepository.findByName("ATTENDEE");
        Optional <UserRole> eventManager = userRoleRepository.findByName("EVENT MANAGER");

        if (defaultRole.isPresent() && eventManager.isPresent()){
            newUser.setRoles(Set.of(defaultRole.get(), eventManager.get()));
        }
        userRepository.save(newUser);

    }


}
