package com.splitwise.exceptions.validations;

import com.splitwise.exceptions.SplitwiseException;

public class ValidationException extends SplitwiseException {
    public ValidationException(String message) {
        super(message);
    }
}
