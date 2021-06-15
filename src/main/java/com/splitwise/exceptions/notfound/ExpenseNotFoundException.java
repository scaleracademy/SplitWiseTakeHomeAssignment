package com.splitwise.exceptions.notfound;

public class ExpenseNotFoundException extends NotFoundException {
    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
