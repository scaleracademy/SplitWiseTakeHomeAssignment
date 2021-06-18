package com.splitwise.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "expenses")
public class Expense extends Auditable {
    private String description;
    private Date date;

    private Double totalAmount;
    private Boolean isSettled;
    @ManyToOne
    private Group group;

    @ManyToMany
    private Set<User> participants = new HashSet<>();

    @ElementCollection
    private Map<User, Double> amountsPaid = new HashMap<>();
    @ElementCollection
    private Map<User, Double> amountsOwed = new HashMap<>();

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
