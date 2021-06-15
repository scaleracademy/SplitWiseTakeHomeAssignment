package com.splitwise.services.authentication;


public class PlaintextPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(String password, String username) {
        return password;
    }
}
