package com.splitwise.repositories.inmemory;

import com.splitwise.models.User;
import com.splitwise.repositories.interfaces.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class InMemoryUserRepository
        extends InMemoryRepository<User>
        implements UserRepository {
    Map<String, Long> usernameIndex = new HashMap<>();

    @Override
    public void create(User user) {
        super.create(user);
        usernameIndex.put(user.getUsername(), user.getId());
    }

    @Override
    public void save(User user) {
        // remove the old username from the index
        User oldUser = findById(user.getId()).get();
        usernameIndex.remove(oldUser.getUsername());
        super.save(user);
        // add the new username to the index
        usernameIndex.put(user.getUsername(), user.getId());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        if(usernameIndex.containsKey(username))
            return findById(usernameIndex.get(username));
        return Optional.empty();
    }
}
