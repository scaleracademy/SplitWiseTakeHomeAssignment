package com.splitwise.services.settle.group;

import com.splitwise.models.Group;

public interface SettleGroupStrategy {
    String settle(Group group);
}
