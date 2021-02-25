package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.MinSpecialCharactersValidator;
import com.boni.passwordvalidatorapi.validator.exception.ValidatorExeption;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MinSpecialCharactersValidatorTest {

    @Spy
    private MinSpecialCharactersValidator minSpecialCharactersValidator;

    @Test
    public void shouldValidateWhenPasswordHasAtLeastOnceDigit() throws ValidatorExeption {

        minSpecialCharactersValidator.validate("a!");
        minSpecialCharactersValidator.validate("a@");
        minSpecialCharactersValidator.validate("a#");
        minSpecialCharactersValidator.validate("a$");
        minSpecialCharactersValidator.validate("a%");
        minSpecialCharactersValidator.validate("a^");
        minSpecialCharactersValidator.validate("a&");
        minSpecialCharactersValidator.validate("a*");
        minSpecialCharactersValidator.validate("a(");
        minSpecialCharactersValidator.validate("a)");
        minSpecialCharactersValidator.validate("a-");
        minSpecialCharactersValidator.validate("a+");

        verify(minSpecialCharactersValidator, never()).getErrorMessage();
    }

    @Test
    public void shouldNotValidateWhenPasswordDoesNotHasAnyDigits() {

        ValidatorExeption exception = assertThrows(ValidatorExeption.class, () -> {
            minSpecialCharactersValidator.validate("abc");
        });

        assertEquals(minSpecialCharactersValidator.getErrorMessage(), exception.getMessage());
    }
}