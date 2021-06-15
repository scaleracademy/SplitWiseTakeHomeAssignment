package com.splitwise.repositories.inmemory.proxies;

import com.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class ExpenseProxy extends Expense {
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private Double totalAmount;
    @Getter
    @Setter
    private Boolean isSettled;
    @Getter
    @Setter
    private GroupProxy group;

    @Getter
    @Setter
    private DBSet<UserProxy> participants = new HashSet<>();
    @Getter
    private DBMap<UserProxy, Double> amountsPaid = new HashMap<>();
    @Getter
    @Setter
    private DBMap<UserProxy, Double> amountsOwed = new HashMap<>();

    public Expense(Date date, String description, Set<User> participants) {
        this.date = date;
        this.description = description;
        this.participants.addAll(participants);
    }

    public void setAmountsPaid(Map<User, Double> amountsPaid) {
        // make sure that the amounts add up to a certain value
        // if the total amount is set, then make sure they add up to the total amount
        this.amountsPaid = amountsPaid;
    }
}
