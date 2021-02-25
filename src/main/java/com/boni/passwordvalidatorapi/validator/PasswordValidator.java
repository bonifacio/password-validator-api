package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.exception.ListValidatorExeption;
import com.boni.passwordvalidatorapi.validator.exception.ValidatorExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PasswordValidator {

    private static final Logger log = LoggerFactory.getLogger(PasswordValidator.class);

    private final List<Validator> validators;

    public PasswordValidator(List<Validator> validators) {
        this.validators = validators;
    }

    public void validate(String password) {

        var errors = validators.stream()
                .map(validator -> {
                    try {
                        validator.validate(password);
                        return null;
                    } catch (ValidatorExeption validatorExeption) {
                        return validatorExeption.getMessage();
                    }
                })
                .filter(StringUtils::hasText)
                .collect(Collectors.toList());

        if (!errors.isEmpty()) {
            throw new ListValidatorExeption(errors);
        }
    }

}
