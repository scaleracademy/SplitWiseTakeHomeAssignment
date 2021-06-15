package com.splitwise.exceptions.validations;

public class InvalidUsernameException extends ValidationException {
    public InvalidUsernameException(String message) {
        super(message);
    }
}
