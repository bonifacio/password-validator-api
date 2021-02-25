package com.boni.passwordvalidatorapi.validator;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DuplicateCharactersValidator implements Validator {

    public static final String ERROR_MESSAGE = "the password should not have duplicate characters";

    @Override
    public boolean isValid(final String password) {

        return Objects.nonNull(password) && password.length() == password.chars().distinct().count();
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE;
    }
}
