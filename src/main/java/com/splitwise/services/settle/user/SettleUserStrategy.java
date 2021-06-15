package com.splitwise.services.settle.user;

import com.splitwise.models.User;

public interface SettleUserStrategy {
    String settle(User user);
}

