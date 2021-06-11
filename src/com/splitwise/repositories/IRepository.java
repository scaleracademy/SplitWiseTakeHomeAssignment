package com.splitwise.repositories;

import java.util.List;
import java.util.Optional;

public interface IRepository<E, Id> {
    // Create
    public void create(E obj);

    // Read
    public Optional<E> findById(Id id);

    public List<E> findAll();

    // Update
    public void save(E obj);

    // Delete
    public void delete(Id id);
}
