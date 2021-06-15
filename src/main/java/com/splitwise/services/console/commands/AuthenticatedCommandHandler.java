package com.splitwise.services.console.commands;

import com.splitwise.Splitwise;
import com.splitwise.services.authentication.AuthenticationContext;
import com.splitwise.services.authentication.ConsoleAuthenticationContext;
import com.splitwise.services.console.handlers.CommandHandler;

import java.util.List;

public class AuthenticatedCommandHandler
        extends CommandHandler
        implements ICommand {
    ConsoleAuthenticationContext authenticationContext;

    public AuthenticatedCommandHandler(Splitwise splitwise) {
        this.authenticationContext = new ConsoleAuthenticationContext(splitwise.getUserRepository());
    }

    @Override
    public void execute(List<String> input) {
        String userIdString = input.get(0);
        input = input.subList(1, input.size());
        long userId = Long.parseLong(userIdString.substring(1));
        authenticationContext.setUserId(userId);
        ICommand command = getCommand(input);
        if (command instanceof IAuthenticatedCommand) {
            ((IAuthenticatedCommand) command).setAuthenticationContext(authenticationContext);
        }
        command.execute(input);
    }

    @Override
    public boolean matches(List<String> input) {
        if (input.size() < 1) return false;
        try {
            String firstInput = input.get(0).toLowerCase();
            if (firstInput.charAt(0) != 'u')
                return false;
            Long.parseLong(firstInput.substring(1));
        } catch (NumberFormatException e) {
            return false;
        }

        return super.matches(input.subList(1, input.size()));
    }

}
