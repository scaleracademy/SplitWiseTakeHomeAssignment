package com.splitwise.services.splitstrategy;

import com.splitwise.models.Expense;

public interface SplitStrategy {
    void calculateAmountsOwed(Expense expense);
}


