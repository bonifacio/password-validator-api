package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.MinLowerCaseValidator;
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
class MinLowerCaseValidatorTest {

    @Spy
    private MinLowerCaseValidator minLowerCaseValidator;

    @Test
    public void shouldValidateWhenPasswordHasAtLeastOnceCapitalLetter() throws ValidatorExeption {

        minLowerCaseValidator.validate("ABc1");

        verify(minLowerCaseValidator, never()).getErrorMessage();
    }

    @Test
    public void shouldNotValidateWhenPasswordDoesNotHasAnyCapitalLetter() {

        ValidatorExeption exception = assertThrows(ValidatorExeption.class, () -> {
            minLowerCaseValidator.validate("ABC1");
        });

        assertEquals(minLowerCaseValidator.getErrorMessage(), exception.getMessage());
    }
}