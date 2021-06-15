package com.splitwise.repositories.inmemory.proxies;

import com.splitwise.exceptions.validations.InvalidUsernameException;
import com.splitwise.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class UserProxy extends User {
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
    private DBSet<ExpenseProxy> expenses = new DBSet<>();
    @Getter
    @Setter
    private DBSet<GroupProxy> groups = new DBSet<>();

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

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "hashedSaltedPassword='" + hashedSaltedPassword + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
