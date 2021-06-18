package com.splitwise.services.settle.group;

import com.splitwise.models.Group;
import org.springframework.stereotype.Service;

@Service
public class DoNothingSettleGroupStrategy implements SettleGroupStrategy {
    @Override
    public String settle(Group group) {
        return "todo";
    }
}
