package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.MinDigitsValidator;
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
class MinDigitsValidatorTest {

    @Spy
    private MinDigitsValidator minDigitsValidator;

    @Test
    public void shouldValidateWhenPasswordHasAtLeastOnceDigit() throws ValidatorExeption {

        minDigitsValidator.validate("abc1");

        verify(minDigitsValidator, never()).getErrorMessage();
    }

    @Test
    public void shouldNotValidateWhenPasswordDoesNotHasAnyDigits() {

        ValidatorExeption exception = assertThrows(ValidatorExeption.class, () -> {
            minDigitsValidator.validate("abc");
        });

        assertEquals(minDigitsValidator.getErrorMessage(), exception.getMessage());
    }
}