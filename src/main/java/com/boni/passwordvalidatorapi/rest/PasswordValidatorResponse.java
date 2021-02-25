package com.boni.passwordvalidatorapi.rest;

public class PasswordValidatorResponse {

    private boolean valid;

    public PasswordValidatorResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
