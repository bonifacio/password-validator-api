package com.boni.passwordvalidatorapi.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MinLowerCaseValidator implements Validator {

    public static final String ERROR_MESSAGE = "the password should have at least one lower case";

    @Override
    public boolean isValid(final String password) {
        return Objects.nonNull(password) && password.matches(".*[a-z].*");
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
