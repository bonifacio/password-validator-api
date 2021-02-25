package com.boni.passwordvalidatorapi.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MinSpecialCharactersValidator implements Validator {

    public static final String ERROR_MESSAGE = "the password should have at least one special character: !@#$%^&*()-+";

    @Override
    public boolean isValid(final String password) {
        return Objects.nonNull(password) && password.matches(".*[!@#$%^&*()-/+].*");
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
