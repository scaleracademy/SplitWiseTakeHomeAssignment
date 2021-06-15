package com.splitwise.repositories.inmemory;

import com.splitwise.models.Expense;
import com.splitwise.repositories.interfaces.ExpenseRepository;

import java.util.ArrayList;
import java.util.Optional;


public class InMemoryExpenseRepository implements ExpenseRepository {
    @Override
    public void create(Expense obj) {

    }

    @Override
    public Optional<Expense> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Expense> findAll() {
        return null;
    }

    @Override
    public void save(Expense obj) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
