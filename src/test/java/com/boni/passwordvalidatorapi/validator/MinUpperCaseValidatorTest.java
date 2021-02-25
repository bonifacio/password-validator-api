package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.MinUpperCaseValidator;
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
class MinUpperCaseValidatorTest {

    @Spy
    private MinUpperCaseValidator minUpperCaseValidator;

    @Test
    public void shouldValidateWhenPasswordHasAtLeastOnceCapitalLetter() throws ValidatorExeption {

        minUpperCaseValidator.validate("Abc1");

        verify(minUpperCaseValidator, never()).getErrorMessage();
    }

    @Test
    public void shouldNotValidateWhenPasswordDoesNotHasAnyCapitalLetter() {

        ValidatorExeption exception = assertThrows(ValidatorExeption.class, () -> {
            minUpperCaseValidator.validate("abc1");
        });

        assertEquals(minUpperCaseValidator.getErrorMessage(), exception.getMessage());
    }
}