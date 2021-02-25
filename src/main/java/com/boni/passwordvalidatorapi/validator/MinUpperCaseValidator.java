package com.boni.passwordvalidatorapi.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MinUpperCaseValidator implements Validator {

    public static final String ERROR_MESSAGE = "the password should have at least one upper case";

    @Override
    public boolean isValid(final String password) {
        return Objects.nonNull(password) && password.matches(".*[A-Z].*");
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
