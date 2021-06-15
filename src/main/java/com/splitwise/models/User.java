package com.splitwise.models;

import com.splitwise.exceptions.validations.InvalidUsernameException;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class User extends Auditable {
    @Getter
    @Setter
    private String fullname;
    @Getter
    @Setter
    private String hashedSaltedPassword;
    @Getter
    private String username;
    @Getter
    @Setter
    private String phoneNumber;

    @Getter
    @Setter
    private Set<Expense> expenses = new HashSet<>();
    @Getter
    @Setter
    private Set<Group> groups = new HashSet<>();

    public void setUsername(String username) {
        if (username.length() < 2) {
            throw new InvalidUsernameException("Username should be minimum 3 chars");
        }
        this.username = username;
    }

    public Double getTotalOwed() {
        // domain logic that belongs to the entity itself
        // Rich Models
        // Fat Models
        // as opposed to Anemic Models
        // Learn more about Domain Driven Design
        // Learn more about Clean Architecture
        // you will have some logic here
        return 0.0;
    }
}
