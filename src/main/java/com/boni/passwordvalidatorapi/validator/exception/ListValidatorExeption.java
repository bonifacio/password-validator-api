package com.boni.passwordvalidatorapi.validator.exception;

import java.util.Collection;

public class ListValidatorExeption extends RuntimeException {


    private final Collection<String> errorMessages;

    public ListValidatorExeption(Collection<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public Collection<String> getErrorMessages() {
        return errorMessages;
    }
}
