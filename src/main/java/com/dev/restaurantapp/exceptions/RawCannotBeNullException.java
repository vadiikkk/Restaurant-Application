package com.dev.restaurantapp.exceptions;

public class RawCannotBeNullException extends IllegalArgumentException{
    public RawCannotBeNullException(String message) {
        super(message);
    }
}
