package com.splitwise.controllers;

import com.splitwise.exceptions.authentication.NotLoggedInException;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.models.Expense;
import com.splitwise.models.User;
import com.splitwise.repositories.ExpenseRepository;
import com.splitwise.repositories.UserRepository;
import com.splitwise.services.authentication.AuthenticationContext;
import com.splitwise.services.paymentstrategy.PaymentStrategy;
import com.splitwise.services.splitstrategy.SplitStrategy;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExpenseController {
    UserRepository userRepository;
    ExpenseRepository expenseRepository;

    public Expense createExpenseWithOtherUsers(
            AuthenticationContext authenticationContext,
            List<Long> participantIds,
            PaymentStrategy paymentStrategy,
            SplitStrategy splitStrategy,
            String description,
            Date date) {
        User user = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new NotLoggedInException(""));

        // create an expense
        List<User> participants = participantIds
                .stream()
                .map((id) -> userRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id.toString())))
                .collect(Collectors.toList());
        participants.add(user);
        Expense expense = new Expense(
                date,
                description,
                participants
        );
        splitStrategy.calculateOwedAmounts(expense);
        paymentStrategy.calculatePaidAmounts(expense);
        expenseRepository.save(expense);
        return expense;
    }

    public Expense createExpenseInGroup(
            AuthenticationContext authenticationContext,
            Long groupId,
            PaymentStrategy paymentStrategy,
            SplitStrategy splitStrategy,
            String description,
            Date date) {

    }
}
