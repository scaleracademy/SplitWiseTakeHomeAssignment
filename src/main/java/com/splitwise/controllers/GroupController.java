package com.splitwise.controllers;

import com.splitwise.exceptions.notfound.GroupNotFoundException;
import com.splitwise.exceptions.notfound.UserNotFoundException;
import com.splitwise.exceptions.validations.CannotModifyGroupException;
import com.splitwise.models.Group;
import com.splitwise.models.User;
import com.splitwise.repositories.interfaces.GroupRepository;
import com.splitwise.repositories.interfaces.UserRepository;
import com.splitwise.services.authentication.AuthenticationContext;
import com.splitwise.services.settle.group.SettleGroupStrategy;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class GroupController {
    final UserRepository userRepository;
    final GroupRepository groupRepository;
    final SettleGroupStrategy settleGroupStrategy;

    public GroupController(UserRepository userRepository,
                           GroupRepository groupRepository, SettleGroupStrategy settleGroupStrategy) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.settleGroupStrategy = settleGroupStrategy;
    }

    public Group createGroup(
            AuthenticationContext authenticationContext,
            List<Long> participantIds,
            String name) {
        User user = authenticationContext
                .getCurrentlyLoggedInUserOrElseThrow();

        Set<User> participants = participantIds
                .stream()
                .map((id) -> userRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id.toString())))
                .collect(Collectors.toSet());
        participants.add(user);
        Group group = new Group(
                name,
                user,
                participants
        );
        groupRepository.save(group);
        return group;
    }

    public Group updateGroup(
            AuthenticationContext authenticationContext,
            List<Long> participantIds,
            String name,
            Long groupId) {
        User user = authenticationContext
                .getCurrentlyLoggedInUserOrElseThrow();
        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(""));
        if (!group.getAdmin().equals(user)) {
            throw new CannotModifyGroupException("You're not the admin of the group");
        }
        Set<User> participants = participantIds
                .stream()
                .map((id) -> userRepository
                        .findById(id)
                        .orElseThrow(() -> new UserNotFoundException(id.toString())))
                .collect(Collectors.toSet());
        participants.add(user);
        group.setMembers(participants);
        group.setName(name);
        groupRepository.save(group);
        return group;
    }

    public void deleteGroup(
            AuthenticationContext authenticationContext,
            Long groupId) {
        User user = authenticationContext
                .getCurrentlyLoggedInUserOrElseThrow();
        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(""));
        if (!group.getAdmin().equals(user)) {
            throw new CannotModifyGroupException("You're not the admin of the group");
        }
        groupRepository.delete(group);
    }


    public String settleUp(AuthenticationContext authenticationContext,
                           Long groupId) {
        User user = authenticationContext.getCurrentlyLoggedInUserOrElseThrow();
        Group group = groupRepository
                .findById(groupId)
                .orElseThrow(() -> new GroupNotFoundException(""));
        if (!group.getMembers().contains(user)) {
            throw new GroupNotFoundException("");
        }
        return settleGroupStrategy.settle(group);
    }
}
