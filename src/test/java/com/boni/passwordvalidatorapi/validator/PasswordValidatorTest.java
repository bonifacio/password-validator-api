package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.DuplicateCharactersValidator;
import com.boni.passwordvalidatorapi.validator.MinCharactersValidator;
import com.boni.passwordvalidatorapi.validator.MinDigitsValidator;
import com.boni.passwordvalidatorapi.validator.MinLowerCaseValidator;
import com.boni.passwordvalidatorapi.validator.MinSpecialCharactersValidator;
import com.boni.passwordvalidatorapi.validator.MinUpperCaseValidator;
import com.boni.passwordvalidatorapi.validator.PasswordValidator;
import com.boni.passwordvalidatorapi.validator.Validator;
import com.boni.passwordvalidatorapi.validator.exception.ListValidatorExeption;
import com.boni.passwordvalidatorapi.validator.exception.ValidatorExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class PasswordValidatorTest {

    @Spy
    private MinCharactersValidator minCharactersValidator;

    @Spy
    private MinDigitsValidator minDigitsValidator;

    @Spy
    private MinLowerCaseValidator minLowerCaseValidator;

    @Spy
    private MinSpecialCharactersValidator minSpecialCharactersValidator;

    @Spy
    private MinUpperCaseValidator minUpperCaseValidator;

    @Spy
    private DuplicateCharactersValidator duplicateCharactersValidator;

    private List<Validator> validators;

    private PasswordValidator passwordValidator;

    @BeforeEach
    public void init() {
        this.validators = List.of(
                minCharactersValidator,
                minDigitsValidator,
                minLowerCaseValidator,
                minSpecialCharactersValidator,
                minUpperCaseValidator,
                duplicateCharactersValidator
        );
        passwordValidator = new PasswordValidator(validators);
    }

    @Test
    public void shouldValidateWhenPasswordHas9orMoreCharacters() throws ValidatorExeption {

        passwordValidator.validate("AbTp9!fok");
        assertTrue(true);
    }

    @Test
    public void shouldNotValidateWhenPasswordIsInvalid() {

        ListValidatorExeption exeption = assertThrows(ListValidatorExeption.class, () -> {
            passwordValidator.validate("");
        });
        assertTrue(exeption.getErrorMessages().contains(MinCharactersValidator.ERROR_MESSAGE));
    }
}