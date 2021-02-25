package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.exception.ValidatorExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Validator {

    Logger log = LoggerFactory.getLogger(Validator.class);

    boolean isValid(String password);

    String getErrorMessage();

    default void validate(final String password) throws ValidatorExeption {

        var isValid = isValid(password);
        log.info("[{}]: {}", this.getClass().getName(), isValid);
        if (!isValid) {
            throw new ValidatorExeption(getErrorMessage());
        }
    }
}
