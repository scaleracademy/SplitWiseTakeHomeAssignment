package com.splitwise.repositories.inmemory;

import com.splitwise.models.User;
import com.splitwise.repositories.interfaces.UserRepository;

import java.util.ArrayList;
import java.util.Optional;


public class InMemoryUserRepository implements UserRepository {
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public void create(User obj) {

    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ArrayList<User> findAll() {
        return null;
    }

    @Override
    public void save(User obj) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
