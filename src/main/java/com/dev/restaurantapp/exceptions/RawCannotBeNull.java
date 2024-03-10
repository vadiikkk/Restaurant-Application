package com.dev.restaurantapp.exceptions;

public class RawCannotBeNull extends IllegalArgumentException{
    public RawCannotBeNull(String message) {
        super(message);
    }
}
