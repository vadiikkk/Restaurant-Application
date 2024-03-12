package com.dev.restaurantapp.exceptions;

public class NameAlreadyExistsException extends RuntimeException {
    public NameAlreadyExistsException(String username) {
        super(username);
    }
}
