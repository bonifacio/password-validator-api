package com.boni.passwordvalidatorapi.rest;

import com.boni.passwordvalidatorapi.validator.PasswordValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PasswordValidatorController {

    private final PasswordValidator passwordValidator;

    public PasswordValidatorController(PasswordValidator passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    @GetMapping("/password-validator/{password}")
    public PasswordValidatorResponse validate(@PathVariable String password) {
        passwordValidator.validate(password);
        return new PasswordValidatorResponse(true);
    }
}
