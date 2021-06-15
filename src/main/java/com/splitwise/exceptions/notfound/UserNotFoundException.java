package com.splitwise.exceptions.notfound;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
