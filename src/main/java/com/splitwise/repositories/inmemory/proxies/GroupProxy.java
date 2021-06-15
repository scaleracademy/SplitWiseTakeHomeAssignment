package com.splitwise.repositories.inmemory.proxies;

import com.splitwise.models.Group;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class GroupProxy extends Group {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private UserProxy admin;
    @Getter
    @Setter
    private Set<UserProxy> members = new HashSet<>();
    @Getter
    @Setter
    private Set<ExpenseProxy> expenses = new HashSet<>();

    public Group(String name, User admin, Set<User> members) {
        this.name = name;
        this.admin = admin;
        this.members.addAll(members);
    }
}
