package com.splitwise.repositories.inmemory;

import com.splitwise.models.Auditable;
import com.splitwise.repositories.inmemory.proxies.Proxy;
import com.splitwise.repositories.interfaces.IRepository;

import java.util.*;

public abstract class InMemoryRepository<E extends Auditable>
        implements IRepository<E, Long> {
    Map<Long, E> objects = new HashMap<>();

    @Override
    public void create(E obj) {
        objects.put(obj.getId(), obj);
    }

    @Override
    public Optional<E> findById(Long userId) {
        if (objects.containsKey(userId))
            return Optional.of(objects.get(userId).clone());
        return Optional.empty();
    }

    @Override
    public List<E> findAll() {
        return new ArrayList<>(objects.values());
    }

    @Override
    public void save(E obj) {
        objects.replace(obj.getId(), obj);
    }

    @Override
    public void delete(Long userId) {
        objects.remove(userId);
    }
}
