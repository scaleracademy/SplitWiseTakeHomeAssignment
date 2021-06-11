package com.splitwise.controllers;

import com.splitwise.dtos.UserDTO;
import com.splitwise.exceptions.authentication.NotLoggedInException;
import com.splitwise.exceptions.authentication.PasswordDoesNotMatchException;
import com.splitwise.exceptions.validations.DuplicateUsernameException;
import com.splitwise.models.Expense;
import com.splitwise.models.Group;
import com.splitwise.models.User;
import com.splitwise.repositories.UserRepository;
import com.splitwise.services.authentication.AuthenticationContext;
import com.splitwise.services.authentication.PasswordEncoder;

import java.util.List;


// Client 1 -> work with user, but this client doesn't want to CRUD users
// Client 2 -> will work with password
// SOLID -> Interface Segregation -> Keep your interfaces minimal

public class UserController {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder = null;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * This method requires the passwordEncoder to be set
     */
    public User register(UserDTO details) {
        User user = new User();

        if (userRepository.findByUsername(details.username).isPresent()) {
            throw new DuplicateUsernameException("Username already exists!");
        }

        user.setUsername(details.username);
        user.setPhoneNumber(details.phoneNumber);
        user.setFullname(details.fullname);
        String saltedHashedPassword = passwordEncoder.encode(details.password, details.username);
        user.setHashedSaltedPassword(saltedHashedPassword);

        userRepository.create(user);
        return user;
    }

    public void changePassword(AuthenticationContext authenticationContext,
                               String oldPassword, String newPassword) {
        User user = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new NotLoggedInException("you gotta login to change the password"));
        if (!user.getHashedSaltedPassword().equals(passwordEncoder.encode(oldPassword, user.getUsername()))) {
            throw new PasswordDoesNotMatchException("");
        }
        user.setHashedSaltedPassword(passwordEncoder.encode(newPassword, user.getUsername()));
        userRepository.save(user);
    }

    public void updateProfile(AuthenticationContext authenticationContext,
                              UserDTO newDetails) {
        User user = authenticationContext
                .getCurrentlyLoggedInUser()
                .orElseThrow(() -> new NotLoggedInException("you gotta login to change the password"));
        if (!user.getHashedSaltedPassword().equals(passwordEncoder.encode(oldPassword, user.getUsername()))) {
            throw new PasswordDoesNotMatchException("");
        }

        user.setFullname(newDetails.fullname);
        user.setPhoneNumber(newDetails.phoneNumber);
        userRepository.save(user);
    }

    public Double getMyTotalOwed(AuthenticationContext authenticationContext) {
        // calculate and return the total amount owed by this user
        // get user
        // and delegate the task to the user itself
    }

    public List<Expense> getMyExpenseHistory(AuthenticationContext authenticationContext) {
        // get user and delegate
    }

    public List<Group> getMyGroups(AuthenticationContext authenticationContext) {
        // get user and delegate
    }

}
