package com.dev.restaurantapp.exceptions;

import java.util.NoSuchElementException;

public class NoSuchItemInDatabaseException extends NoSuchElementException {
    public NoSuchItemInDatabaseException(String message) {
        super(message);
    }
}
