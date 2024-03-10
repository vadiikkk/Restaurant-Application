package com.dev.restaurantapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExists(NameAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(RawCannotBeNullException.class)
    public ResponseEntity<String> handleNullRaw(RawCannotBeNullException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(NoSuchItemInDatabaseException.class)
    public ResponseEntity<String> handleNoItemInDatabase(NoSuchItemInDatabaseException exception) {
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(exception.getMessage());
    }
}
