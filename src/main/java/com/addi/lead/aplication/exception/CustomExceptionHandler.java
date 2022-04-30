package com.addi.lead.aplication.exception;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler({
            SocketTimeoutException.class,
            RetryableException.class
    })
    public ResponseEntity<Map<String, String>> handleSocketException(
            Exception ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("Error", ex.getMessage());
        log.error("Error in API: " + Arrays.toString(ex.getStackTrace()));
        return ResponseEntity
                .status(HttpStatus.GATEWAY_TIMEOUT)
                .body(errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleExceptions(
            Exception ex) {
        Map<String, String> errors = new HashMap<>();
        log.error("Error in API: " + Arrays.toString(ex.getStackTrace()));
        errors.put("Error", "There was a processing error, please try again later.");
        return ResponseEntity
                .status(HttpStatus.PRECONDITION_FAILED)
                .body(errors);
    }
}
