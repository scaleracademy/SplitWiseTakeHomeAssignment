package com.splitwise.controllers;

import com.splitwise.exceptions.notfound.ExpenseNotFoundException;
import com.splitwise.exceptions.notfound.GroupNotFoundException;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.exceptions.validations.InvalidParticipantException;
import com.splitwise.models.Expense;
import com.splitwise.models.Group;
import com.splitwise.models.User;
import com.splitwise.repositories.interfaces.ExpenseRepository;
import com.splitwise.repositories.interfaces.GroupRepository;
import com.splitwise.repositories.interfaces.UserRepository;
import com.splitwise.services.authentication.AuthenticationContext;
import com.splitwise.services.paymentstrategy.PaymentStrategy;
import com.splitwise.services.splitstrategy.SplitStrategy;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class ExpenseController {
    UserRepository userRepository;
    ExpenseRepository expenseRepository;
    GroupRepository groupRepository;

    public ExpenseController(UserRepository userRepository,
                             ExpenseRepository expenseRepository,
                             GroupRepository groupRepository) {
        this.userRepository = userRepository;
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
    }

    public Expense createExpenseWithOtherUsers(
            AuthenticationContext authenticationContext,
            List<Long> participantIds,
            PaymentStrategy paymentStrategy,
            SplitStrategy splitStrategy,
            String description,
            Date date) {
        User user = authenticationContext
                .getCurrentlyLoggedInUserOrElseThrow();

        Set<User> participants = participantIds
                .stream()
                .map((id) -> userRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id.toString())))
                .collect(Collectors.toSet());
        participants.add(user);
        Expense expense = new Expense(
                date,
                description,
                participants
        );
        splitStrategy.calculateAmountsOwed(expense);
        paymentStrategy.calculateAmountsPaid(expense);
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
        User user = authenticationContext
                .getCurrentlyLoggedInUserOrElseThrow();
        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(""));

        if (!group.getMembers().contains(user))
            throw new GroupNotFoundException("You're not a member of any such groups");

        Expense expense = new Expense(
                date,
                description,
                group.getMembers()
        );
        expense.setGroup(group);
        splitStrategy.calculateAmountsOwed(expense);
        paymentStrategy.calculateAmountsPaid(expense);
        expenseRepository.save(expense);
        return expense;
    }

    public Expense updateExpenseWithOthers(
            AuthenticationContext authenticationContext,
            Long expenseId,
            List<Long> participantIds,
            PaymentStrategy paymentStrategy,
            SplitStrategy splitStrategy,
            String description,
            Date date) {
        User user = authenticationContext
                .getCurrentlyLoggedInUserOrElseThrow();
        Expense expense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(""));
        if (!expense.getParticipants().contains(user)) {
            throw new ExpenseNotFoundException("");
        }

        Set<User> participants = participantIds
                .stream()
                .map((id) -> userRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id.toString())))
                .collect(Collectors.toSet());
        participants.add(user);
        Group group = expense.getGroup();
        if (group != null) {
            for (User participant : participants) {
                if (!group.getMembers().contains(participant)) {
                    throw new InvalidParticipantException(
                            participant.getFullname()
                                    + " is not a member of group "
                                    + group.getName());
                }
            }
        }

        expense.setParticipants(participants);
        expense.setDate(date);
        expense.setDescription(description);
        splitStrategy.calculateAmountsOwed(expense);
        paymentStrategy.calculateAmountsPaid(expense);
        expenseRepository.save(expense);
        return expense;
    }

    public void deleteExpense(
            AuthenticationContext authenticationContext,
            Long expenseId) {
        User user = authenticationContext
                .getCurrentlyLoggedInUserOrElseThrow();
        Expense expense = expenseRepository
                .findById(expenseId)
                .orElseThrow(() -> new ExpenseNotFoundException(""));

        if (!expense.getParticipants().contains(user))
            throw new ExpenseNotFoundException("You're not a participant in any such expense");

        expenseRepository.delete(expense);
    }
}
