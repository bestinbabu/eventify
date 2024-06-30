package com.eventify.eventify.auth;

import com.eventify.eventify.dto.auth.AuthRequestDto;
import com.eventify.eventify.filter.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate( @RequestBody AuthRequestDto request) {
        System.out.println(request.getEmail() + " " + request.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var jwtToken = jwtService.generateToken(authentication);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jwtToken);

    }

}
