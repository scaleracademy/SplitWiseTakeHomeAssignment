package com.splitwise.services.authentication;


public class PlaintextPasswordEncoder implements PasswordEncoder {
    private static PlaintextPasswordEncoder INSTANCE;

    private PlaintextPasswordEncoder() {
    }

    public static PlaintextPasswordEncoder getInstance() {
        if (INSTANCE == null)
            synchronized (PlaintextPasswordEncoder.class) {
                if (INSTANCE == null)
                    INSTANCE = new PlaintextPasswordEncoder();
            }
        return INSTANCE;
    }

    @Override
    public String encode(String password, String username) {
        return "encoded-" + password;
    }
}
