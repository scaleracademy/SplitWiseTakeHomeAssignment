package com.splitwise.exceptions.validations;

public class DuplicateUsernameException extends ValidationException {
    public DuplicateUsernameException(String message) {
        super(message);
    }
}
