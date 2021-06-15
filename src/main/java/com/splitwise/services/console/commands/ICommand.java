package com.splitwise.services.console.commands;

import java.util.List;

public interface ICommand {
    boolean matches(List<String> input);

    void execute(List<String> input);
}
