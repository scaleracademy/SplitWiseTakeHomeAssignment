package com.splitwise.models;

import com.splitwise.exceptions.validations.InvalidUsernameException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User extends Auditable {
    private String fullname;
    private String hashedSaltedPassword;
    private String username;
    private String phoneNumber;

    @ManyToMany(mappedBy = "participants")
    private Set<Expense> expenses = new HashSet<>();

    @ManyToMany(mappedBy = "members")
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

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                "hashedSaltedPassword='" + hashedSaltedPassword + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
