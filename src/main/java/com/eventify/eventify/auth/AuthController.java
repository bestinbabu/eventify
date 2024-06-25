package com.eventify.eventify.auth;

import com.eventify.eventify.constants.AuthConstants;
import com.eventify.eventify.dto.ResponseDto;
import com.eventify.eventify.dto.auth.AuthenticationRequest;
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

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDto> authenticate( @RequestBody AuthenticationRequest request) {
        System.out.println(request.getEmail() + " " + request.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AuthConstants.STATUS_200, AuthConstants.MESSAGE_200));

    }

}
