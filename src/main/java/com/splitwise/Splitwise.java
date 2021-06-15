package com.splitwise;

import com.splitwise.controllers.ExpenseController;
import com.splitwise.controllers.GroupController;
import com.splitwise.controllers.UserController;
import com.splitwise.repositories.inmemory.InMemoryExpenseRepository;
import com.splitwise.repositories.inmemory.InMemoryGroupRepository;
import com.splitwise.repositories.inmemory.InMemoryUserRepository;
import com.splitwise.repositories.interfaces.ExpenseRepository;
import com.splitwise.repositories.interfaces.GroupRepository;
import com.splitwise.repositories.interfaces.UserRepository;
import com.splitwise.services.authentication.PasswordEncoder;
import com.splitwise.services.authentication.PlaintextPasswordEncoder;
import lombok.Getter;

public class Splitwise {
    @Getter
    UserController userController;
    @Getter
    GroupController groupController;
    @Getter
    ExpenseController expenseController;
    @Getter
    GroupRepository groupRepository;
    @Getter
    UserRepository userRepository;
    @Getter
    ExpenseRepository expenseRepository;
    PasswordEncoder passwordEncoder;

    public Splitwise() {
        userRepository = new InMemoryUserRepository();
        groupRepository = new InMemoryGroupRepository();
        expenseRepository = new InMemoryExpenseRepository();

        passwordEncoder = PlaintextPasswordEncoder.getInstance();
        userController = new UserController(userRepository);
        userController.setPasswordEncoder(passwordEncoder);
        groupController = new GroupController(userRepository, groupRepository);
        expenseController = new ExpenseController(userRepository, expenseRepository, groupRepository);
    }
}
