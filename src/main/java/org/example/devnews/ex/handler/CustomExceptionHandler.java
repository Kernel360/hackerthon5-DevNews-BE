package org.example.devnews.ex.handler;


import io.swagger.v3.oas.annotations.Hidden;
import org.example.devnews.dto.ResponseDto;
import org.example.devnews.ex.CustomApiException;
import org.example.devnews.ex.CustomForbiddenException;
import org.example.devnews.ex.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Hidden
public class CustomExceptionHandler {
    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException customApiException) {
        return new ResponseEntity<>(new ResponseDto(-1, customApiException.getMessage(), null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> validationException(CustomValidationException customValidationException) {
        return new ResponseEntity<>(new ResponseDto(-1, customValidationException.getMessage(), customValidationException.getErrMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomForbiddenException.class)
    public ResponseEntity<?> forbiddenException(CustomForbiddenException customForbiddenException) {
        return new ResponseEntity<>(new ResponseDto(-1, customForbiddenException.getMessage(), null), HttpStatus.FORBIDDEN);
    }
}
