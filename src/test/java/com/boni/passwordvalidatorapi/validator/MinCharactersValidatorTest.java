package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.MinCharactersValidator;
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
class MinCharactersValidatorTest {

    @Spy
    private MinCharactersValidator minCharactersValidator;

    @Test
    public void shouldValidateWhenPasswordHas9orMoreCharacters() throws ValidatorExeption {

        minCharactersValidator.validate("123456789");

        verify(minCharactersValidator, never()).getErrorMessage();
    }

    @Test
    public void shouldNotValidateWhenPasswordHasLessThan9Characters() {

        ValidatorExeption exception = assertThrows(ValidatorExeption.class, () -> {
            minCharactersValidator.validate("12345678");
        });

        assertEquals(minCharactersValidator.getErrorMessage(), exception.getMessage());
    }
}