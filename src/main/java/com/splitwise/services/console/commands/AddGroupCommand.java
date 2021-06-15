package com.splitwise.services.console.commands;

import com.splitwise.Splitwise;
import com.splitwise.controllers.GroupController;
import com.splitwise.models.Group;

import java.util.ArrayList;
import java.util.List;


public class AddGroupCommand extends AbstractAuthenticatedCommand {
    GroupController groupController;

    public AddGroupCommand(Splitwise splitwise) {
        this.groupController = splitwise.getGroupController();
    }

    @Override
    public boolean matches(List<String> input) {
        return input.size() >= 2 && input.get(0).toLowerCase().equals("addgroup");
    }

    @Override
    public void execute(List<String> input) {
        String name = input.get(1);
        List<Long> participants = new ArrayList<>();
        for (int i = 2; i < input.size(); i++) {
            participants.add(Long.parseLong(input.get(i)));
        }
        Group group = groupController.createGroup(authenticationContext, participants, name);
        System.out.println("Created group: " + group.toString());
    }
}
