package com.eventify.eventify.utility;

import com.eventify.eventify.entity.user.UserProfile;
import com.eventify.eventify.exception.ForbiddenException;
import com.eventify.eventify.repository.user.UserProfileRepository;
import com.eventify.eventify.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class SecurityUtils {

    public String getCurrentUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new ForbiddenException("User is not authenticated");
        }
        return authentication.getName();
    }

    public UserProfile getCurrentUserProfile(UserRepository userRepository) {
        return  userRepository.findByEmail(getCurrentUserEmail()).get().getUserProfile();

    }
}