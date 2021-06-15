package com.splitwise.services.console.handlers;

import com.splitwise.services.console.commands.ICommand;

import java.util.List;

public interface ICommandHandler {
    void execute(List<String> input);

    boolean matches(List<String> input);

    void registerCommand(ICommand command);

    ICommand getCommand(List<String> input);
}
