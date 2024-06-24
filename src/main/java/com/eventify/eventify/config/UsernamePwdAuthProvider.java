package com.eventify.eventify.config;

import com.eventify.eventify.entity.user.User;
import com.eventify.eventify.entity.user.UserRole;
import com.eventify.eventify.exception.BadCredentialsException;
import com.eventify.eventify.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UsernamePwdAuthProvider implements AuthenticationProvider {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        System.out.println(email+password);

        Optional <User> user = userRepository.findByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return new UsernamePasswordAuthenticationToken(email, password, getGrantedAuthorities(user));
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Optional<User> user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        List<UserRole> roles = user.get().getRoles();

        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            role.getPrivileges().forEach(privilege -> {
                authorities.add(new SimpleGrantedAuthority("PRIVILEGE_" + privilege.getName()));
            });
        }

        return authorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
