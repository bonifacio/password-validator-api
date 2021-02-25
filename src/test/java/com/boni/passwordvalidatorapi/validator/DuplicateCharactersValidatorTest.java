package com.boni.passwordvalidatorapi.validator;

import com.boni.passwordvalidatorapi.validator.DuplicateCharactersValidator;
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
class DuplicateCharactersValidatorTest {

    @Spy
    private DuplicateCharactersValidator duplicateCharactersValidator;

    @Test
    public void shouldValidateWhenPasswordHas9orMoreCharacters() throws ValidatorExeption {

        duplicateCharactersValidator.validate("123abc");

        verify(duplicateCharactersValidator, never()).getErrorMessage();
    }

    @Test
    public void shouldNotValidateWhenPasswordHasLessThan9Characters() {

        ValidatorExeption exception = assertThrows(ValidatorExeption.class, () -> {
            duplicateCharactersValidator.validate("123abc1");
        });

        assertEquals(duplicateCharactersValidator.getErrorMessage(), exception.getMessage());
    }
}