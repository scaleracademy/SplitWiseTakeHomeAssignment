package com.splitwise.exceptions.authentication;

public class PasswordDoesNotMatchException extends AuthenticationException {
    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
}
