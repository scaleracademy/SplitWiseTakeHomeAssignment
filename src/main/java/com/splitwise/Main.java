package com.splitwise;

import com.splitwise.services.console.commands.AddGroupCommand;
import com.splitwise.services.console.commands.AuthenticatedCommandHandler;
import com.splitwise.services.console.commands.ExitCommand;
import com.splitwise.services.console.commands.RegisterUserCommand;
import com.splitwise.services.console.handlers.CommandHandler;
import com.splitwise.services.console.handlers.ICommandHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


@SpringBootApplication
@EnableJpaAuditing
public class Main implements CommandLineRunner {
    final AddGroupCommand addGroupCommand;
    final AuthenticatedCommandHandler authenticatedCommandHandler;
    final RegisterUserCommand registerUserCommand;

    public Main(AddGroupCommand addGroupCommand, AuthenticatedCommandHandler authenticatedCommandHandler, RegisterUserCommand registerUserCommand) {
        this.addGroupCommand = addGroupCommand;
        this.authenticatedCommandHandler = authenticatedCommandHandler;
        this.registerUserCommand = registerUserCommand;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        authenticatedCommandHandler.registerCommand(addGroupCommand);

        ICommandHandler commandHandler = new CommandHandler();
        commandHandler.registerCommand(registerUserCommand);
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
