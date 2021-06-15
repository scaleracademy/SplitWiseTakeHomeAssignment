package com.splitwise.services.paymentstrategy;

import com.splitwise.models.Expense;
import com.splitwise.models.User;

import java.util.Map;

public class ExactAmountPaymentStrategy implements PaymentStrategy {
    private final Map<User, Double> amountsPaid;

    public ExactAmountPaymentStrategy(Map<User, Double> amountsPaid) {
        this.amountsPaid = amountsPaid;
    }

    @Override
    public void calculateAmountsPaid(Expense expense) {
        expense.setAmountsPaid(amountsPaid);
    }
}
