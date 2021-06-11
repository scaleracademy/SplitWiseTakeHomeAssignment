package com.splitwise.exceptions.notfound;

import com.splitwise.exceptions.SplitwiseException;

public class NotFoundException extends SplitwiseException {

    public NotFoundException(String message) {
        super(message);
    }
}
