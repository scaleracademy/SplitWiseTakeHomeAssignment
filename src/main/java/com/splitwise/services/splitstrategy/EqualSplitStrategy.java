package com.splitwise.services.splitstrategy;

import com.splitwise.models.Expense;
import com.splitwise.models.User;

import java.util.HashMap;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {
    private final Double totalAmount;

    public EqualSplitStrategy(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public void calculateAmountsOwed(Expense expense) {
        Map<User, Double> amountsPaid = new HashMap<>();
        int count = expense.getParticipants().size();
        for (User participant : expense.getParticipants()) {
            Double amount = totalAmount / count;
            amountsPaid.put(participant, amount);
        }
        expense.setAmountsPaid(amountsPaid);
    }
}
