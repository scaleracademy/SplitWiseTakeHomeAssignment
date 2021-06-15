package com.splitwise.repositories.inmemory;

import java.util.ArrayList;
import java.util.Optional;

public interface IRepository<E, Id> {
    // Create
    public void create(E obj);

    // Read
    public Optional<E> findById(Id id);

    public ArrayList<E> findAll();

    // Update
    public void save(E obj);

    // Delete
    public void delete(Id id);
}
