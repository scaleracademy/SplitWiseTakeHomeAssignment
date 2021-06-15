package com.splitwise.services.console.commands;

import java.util.List;

public class ExitCommand implements ICommand {
    @Override
    public boolean matches(List<String> input) {
        return input.size() > 0 && input.get(0).toLowerCase().startsWith("exit");
    }

    @Override
    public void execute(List<String> input) {
        System.exit(0);
    }
}
