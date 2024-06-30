package com.eventify.eventify.exception;


import com.eventify.eventify.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(BadRequestException exception, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponseDto> handleForbiddenException(ForbiddenException exception, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorCode(HttpStatus.FORBIDDEN);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(errorResponseDto);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(UserAlreadyExistsException exception, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorCode(HttpStatus.BAD_REQUEST);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponseDto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorCode(HttpStatus.NOT_FOUND);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponseDto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException exception, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorCode(HttpStatus.UNAUTHORIZED);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponseDto);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setApiPath(webRequest.getDescription(false));
        errorResponseDto.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponseDto.setErrorMessage(exception.getMessage());
        errorResponseDto.setErrorTime(LocalDateTime.now());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponseDto);
    }



}
