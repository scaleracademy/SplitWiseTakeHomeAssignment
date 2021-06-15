package com.splitwise.services.console.handlers;

import com.splitwise.services.console.commands.ICommand;
import com.splitwise.services.console.exceptions.UnsupportedCommandException;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler implements ICommandHandler {
    List<ICommand> commands = new ArrayList<>();
    // this is applicable ONLY IF we know that each command has a unique
    // identifier
    // Map<String, ICommand> commandsForIdentifier;

    // you have a command -> Add
    // Add has further sub-commands
    // Add User
    // Add Group

    @Override
    public void execute(List<String> input) {
        getCommand(input).execute(input);
    }

    @Override
    public ICommand getCommand(List<String> input) {
        for(ICommand command: commands) {
            if(command.matches(input)) {
                return command;
            }
        }
        throw new UnsupportedCommandException(input.toString());
    }

    @Override
    public boolean matches(List<String> input) {
        return commands.stream().anyMatch(c -> c.matches(input));
    }

    // if we knew that each command begins from a unique string

    @Override
    public void registerCommand(ICommand command) {
        commands.add(command);
    }
}
