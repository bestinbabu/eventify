package com.eventify.eventify.controller;

import com.eventify.eventify.constants.AuthConstants;
import com.eventify.eventify.dto.ResponseDto;
import com.eventify.eventify.dto.user.RegistrationRequestDto;
import com.eventify.eventify.service.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class UserController {

    IUserService iUserService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUser(@Valid @RequestBody RegistrationRequestDto registrationRequestDTO) {

        iUserService.registerUser(registrationRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AuthConstants.STATUS_201, AuthConstants.MESSAGE_201));
    }


    @GetMapping("/poda")
    public String hello() {
        return "hi";
    }
}

































