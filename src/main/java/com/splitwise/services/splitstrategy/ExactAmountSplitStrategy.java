package com.splitwise.services.splitstrategy;

import com.splitwise.models.Expense;
import com.splitwise.models.User;

import java.util.Map;

public class ExactAmountSplitStrategy implements SplitStrategy {
    private final Map<User, Double> amountsOwed;

    public ExactAmountSplitStrategy(Map<User, Double> amountsOwed) {
        this.amountsOwed = amountsOwed;
    }

    @Override
    public void calculateAmountsOwed(Expense expense) {
        expense.setAmountsOwed(amountsOwed);
    }
}
