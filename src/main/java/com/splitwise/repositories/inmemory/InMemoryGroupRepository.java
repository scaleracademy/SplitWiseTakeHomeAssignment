package com.splitwise.repositories.inmemory;

import com.splitwise.models.Group;
import com.splitwise.repositories.interfaces.GroupRepository;


public class InMemoryGroupRepository
        extends InMemoryRepository<Group>
        implements GroupRepository {
}

