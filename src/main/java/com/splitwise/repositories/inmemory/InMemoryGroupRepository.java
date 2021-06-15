package com.splitwise.repositories.inmemory;

import com.splitwise.models.Group;
import com.splitwise.repositories.interfaces.GroupRepository;

import java.util.ArrayList;
import java.util.Optional;


public class InMemoryGroupRepository implements GroupRepository {
    @Override
    public void create(Group obj) {

    }

    @Override
    public Optional<Group> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public ArrayList<Group> findAll() {
        return null;
    }

    @Override
    public void save(Group obj) {

    }

    @Override
    public void delete(Long aLong) {

    }
}
