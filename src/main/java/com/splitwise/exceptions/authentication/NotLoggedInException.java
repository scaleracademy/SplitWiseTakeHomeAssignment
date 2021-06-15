package com.splitwise.exceptions.authentication;

public class NotLoggedInException extends AuthenticationException {
    public NotLoggedInException(String message) {
        super(message);
    }
}
