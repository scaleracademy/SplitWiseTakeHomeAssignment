package com.splitwise;

import com.splitwise.controllers.ExpenseController;
import com.splitwise.controllers.GroupController;
import com.splitwise.controllers.UserController;
import com.splitwise.repositories.interfaces.ExpenseRepository;
import com.splitwise.repositories.interfaces.GroupRepository;
import com.splitwise.repositories.interfaces.UserRepository;
import com.splitwise.services.authentication.PasswordEncoder;
import com.splitwise.services.authentication.PlaintextPasswordEncoder;
import com.splitwise.services.console.commands.AddGroupCommand;
import com.splitwise.services.console.commands.AuthenticatedCommandHandler;
import com.splitwise.services.console.commands.ExitCommand;
import com.splitwise.services.console.commands.RegisterUserCommand;
import com.splitwise.services.console.handlers.CommandHandler;
import com.splitwise.services.console.handlers.ICommandHandler;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Splitwise splitwise = new Splitwise();

        AuthenticatedCommandHandler authenticatedCommandHandler = new AuthenticatedCommandHandler(splitwise);
        authenticatedCommandHandler.registerCommand(new AddGroupCommand(splitwise));

        ICommandHandler commandHandler = new CommandHandler();
        commandHandler.registerCommand(new RegisterUserCommand(splitwise));
        commandHandler.registerCommand(new ExitCommand());
        commandHandler.registerCommand(authenticatedCommandHandler);


        while (true) {
            System.out.print("\n> ");
            List<String> input = Arrays.asList(br.readLine().split(" "));
            try {
                commandHandler.execute(input);
            } catch (Exception e) {
                System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
