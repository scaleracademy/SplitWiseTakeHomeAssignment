package com.splitwise.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class Group extends Auditable {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private User admin;
    @Getter
    @Setter
    private Set<User> members = new HashSet<>();
    @Getter
    @Setter
    private Set<Expense> expenses = new HashSet<>();

    public Group(String name, User admin, Set<User> members) {
        this.name = name;
        this.admin = admin;
        this.members.addAll(members);
    }
}
