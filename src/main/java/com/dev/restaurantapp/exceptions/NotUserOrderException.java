package com.dev.restaurantapp.exceptions;

public class NotUserOrderException extends RuntimeException {
    public NotUserOrderException(String message) {
        super(message);
    }
}
