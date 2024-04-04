package com.dev.restaurantapp.exceptions;

public class CannotMakeOrderException extends RuntimeException {
    public CannotMakeOrderException(String message) {
        super(message);
    }
}
