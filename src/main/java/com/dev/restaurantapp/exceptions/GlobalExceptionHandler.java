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

    @ExceptionHandler(CannotMakeOrderException.class)
    public ResponseEntity<String> handleCannotMakeOrder(CannotMakeOrderException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(NotUserOrderException.class)
    public ResponseEntity<String> handleNotUsersOrder(NotUserOrderException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(OrderAlreadyDoneException.class)
    public ResponseEntity<String> handleOrderAlreadyDone(OrderAlreadyDoneException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }
}
