package com.splitwise.services.console.commands;

import com.splitwise.services.authentication.AuthenticationContext;

import java.util.List;

public abstract class AbstractAuthenticatedCommand implements IAuthenticatedCommand {
    AuthenticationContext authenticationContext;

    @Override
    public void setAuthenticationContext(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
    }

    @Override
    abstract public boolean matches(List<String> input);

    @Override
    abstract public void execute(List<String> input);


}
