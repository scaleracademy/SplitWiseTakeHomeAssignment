package com.splitwise.services.splitstrategy;

import com.splitwise.models.Expense;
import com.splitwise.models.User;

import java.util.HashMap;
import java.util.Map;

public class ByRatioSplitStrategy implements SplitStrategy {
    private final Map<User, Integer> ratios;
    private final Double totalAmount;

    public ByRatioSplitStrategy(Map<User, Integer> ratios, Double totalAmount) {
        this.ratios = ratios;
        this.totalAmount = totalAmount;
    }

    @Override
    public void calculateAmountsOwed(Expense expense) {
        Map<User, Double> amountsPaid = new HashMap<>();
        int totalParts = ratios
                .values()
                .stream()
                .reduce(Integer::sum)
                .orElse(0);
        for (User participant : expense.getParticipants()) {
            Double amount = totalAmount * ratios.get(participant) / totalParts;
            amountsPaid.put(participant, amount);
        }
        expense.setAmountsPaid(amountsPaid);
    }
}
