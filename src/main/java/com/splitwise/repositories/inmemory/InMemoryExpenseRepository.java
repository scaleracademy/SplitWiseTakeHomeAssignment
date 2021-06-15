package com.splitwise.repositories.inmemory;

import com.splitwise.models.Expense;
import com.splitwise.models.User;
import com.splitwise.repositories.inmemory.proxies.ExpenseProxy;
import com.splitwise.repositories.interfaces.ExpenseRepository;
import com.splitwise.repositories.interfaces.UserRepository;

import java.util.List;


public class InMemoryExpenseRepository
        extends InMemoryRepository<Expense>
        implements ExpenseRepository {
    UserRepository userRepository;

    public InMemoryExpenseRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(Expense expense) {
        super.create(expense);
        for(User participant: expense.getParticipants()) {
            participant.getExpenses().add(expense);
        }
    }

    @Override
    public List<ExpenseProxy> findAll() {
        return super.findAll();
    }
}
