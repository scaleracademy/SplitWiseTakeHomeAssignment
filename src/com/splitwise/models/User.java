package com.splitwise.models;

import com.splitwise.exceptions.validations.InvalidUsernameException;

import java.util.List;

public class User extends Auditable {
    private String fullname;
    private String hashedSaltedPassword;
    private String username;
    private String phoneNumber;

    private List<Expense> expenses;
    private List<Group> groups;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getHashedSaltedPassword() {
        return hashedSaltedPassword;
    }

    public void setHashedSaltedPassword(String hashedSaltedPassword) {
        this.hashedSaltedPassword = hashedSaltedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username.length() < 2) {
            throw new InvalidUsernameException("Username should be minimum 3 chars");
        }
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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
