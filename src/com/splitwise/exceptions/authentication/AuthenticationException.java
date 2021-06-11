package com.splitwise.exceptions.authentication;

import com.splitwise.exceptions.SplitwiseException;

public class AuthenticationException extends SplitwiseException {
    public AuthenticationException(String message) {
        super(message);
    }
}
