package com.dev.restaurantapp.exceptions;

public class OrderAlreadyDoneException extends RuntimeException {
    public OrderAlreadyDoneException(String message) {
        super(message);
    }
}
