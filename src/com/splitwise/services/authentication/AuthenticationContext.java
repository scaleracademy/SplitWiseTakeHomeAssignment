package com.splitwise.services.authentication;

import com.splitwise.models.User;

import java.util.Optional;

public interface AuthenticationContext {
    Optional<User> getCurrentlyLoggedInUser();

    Boolean isAuthenticated();
}
