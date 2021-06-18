package com.splitwise.services.settle.user;

import com.splitwise.models.User;
import org.springframework.stereotype.Service;

@Service
public class DoNothingSettleUserStrategy implements SettleUserStrategy {
    @Override
    public String settle(User user) {
        return "todo";
    }
}
