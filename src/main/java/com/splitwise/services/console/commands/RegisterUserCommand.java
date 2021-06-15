package com.splitwise.services.console.commands;

import com.splitwise.Splitwise;
import com.splitwise.controllers.UserController;
import com.splitwise.dtos.UserDTO;
import com.splitwise.models.User;
import com.splitwise.services.console.ConsoleConfiguration;

import java.util.List;


public class RegisterUserCommand implements ICommand {
    UserController userController;

    public RegisterUserCommand(Splitwise splitwise) {
        this.userController = splitwise.getUserController();
    }

    @Override
    public boolean matches(List<String> input) {
        return input.size() >= 4 && input.get(0).toLowerCase().equals(ConsoleConfiguration.REGISTER_COMMAND);
    }

    @Override
    public void execute(List<String> input) {
        UserDTO details = new UserDTO();
        details.username = input.get(1);
        details.phoneNumber = input.get(2);
        details.password = input.get(3);
        User user = userController.register(details);
        System.out.println("Created user: " + user.toString());
    }
}
