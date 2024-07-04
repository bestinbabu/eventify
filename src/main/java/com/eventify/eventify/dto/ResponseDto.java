package com.eventify.eventify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {
    private HttpStatus statusCode;
    private String statusMsg;
    private T  data ;
}
