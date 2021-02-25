package com.boni.passwordvalidatorapi.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MinCharactersValidator implements Validator {

    private static final int MIN_CHARACTER = 9;

    public static final String ERROR_MESSAGE = "the password should have of minimum " + MIN_CHARACTER + " characters";

    @Override
    public boolean isValid(final String password) {
        return Objects.nonNull(password) && password.length() >= MIN_CHARACTER;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
