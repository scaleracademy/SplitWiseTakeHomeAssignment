package com.splitwise.services.console.commands;

import com.splitwise.services.authentication.AuthenticationContext;

public interface IAuthenticatedCommand extends ICommand {
    public void setAuthenticationContext(AuthenticationContext authenticationContext);
    }
