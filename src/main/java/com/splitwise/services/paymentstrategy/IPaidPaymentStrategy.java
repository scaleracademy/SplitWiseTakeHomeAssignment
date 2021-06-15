package com.splitwise.services.paymentstrategy;

import com.splitwise.models.Expense;
import com.splitwise.models.User;

import java.util.HashMap;
import java.util.Map;

public class IPaidPaymentStrategy implements PaymentStrategy {
    private final User me;
    private final Double amountPaid;

    public IPaidPaymentStrategy(User me, Double amountPaid) {
        this.me = me;
        this.amountPaid = amountPaid;
    }

    @Override
    public void calculateAmountsPaid(Expense expense) {
        Map<User, Double> amountsPaid = new HashMap<>();
        amountsPaid.put(me, amountPaid);
        for (User participant : expense.getParticipants()) {
            if (participant.equals(me)) continue;
            amountsPaid.put(participant, 0.0);
        }
        expense.setAmountsPaid(amountsPaid);
    }
}
