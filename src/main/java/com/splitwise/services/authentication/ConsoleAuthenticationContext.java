package com.splitwise.services.authentication;

import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.models.User;
import com.splitwise.repositories.interfaces.UserRepository;

import java.util.Optional;

public class ConsoleAuthenticationContext implements AuthenticationContext {
    UserRepository userRepository;
    Long userId = -1L;

    public ConsoleAuthenticationContext(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getCurrentlyLoggedInUser() {
        return userRepository.findById(userId);
    }

    @Override
    public User getCurrentlyLoggedInUserOrElseThrow() {
        return getCurrentlyLoggedInUser()
                .orElseThrow(() -> new UserNotFoundException(""));
    }

    @Override
    public Boolean isAuthenticated() {
        return getCurrentlyLoggedInUser().isPresent();
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
