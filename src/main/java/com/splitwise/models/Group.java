package com.splitwise.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "groups")
public class Group extends Auditable {
    private String name;
    @OneToOne
    private User admin;
    @ManyToMany
    private Set<User> members = new HashSet<>();
    @OneToMany(mappedBy = "group")
    private Set<Expense> expenses = new HashSet<>();

    public Group(String name, User admin, Set<User> members) {
        this.name = name;
        this.admin = admin;
        this.members.addAll(members);
    }
}
