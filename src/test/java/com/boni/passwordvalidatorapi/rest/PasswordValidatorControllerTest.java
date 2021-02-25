package com.boni.passwordvalidatorapi.rest;

import com.boni.passwordvalidatorapi.validator.DuplicateCharactersValidator;
import com.boni.passwordvalidatorapi.validator.MinCharactersValidator;
import com.boni.passwordvalidatorapi.validator.MinDigitsValidator;
import com.boni.passwordvalidatorapi.validator.MinLowerCaseValidator;
import com.boni.passwordvalidatorapi.validator.MinSpecialCharactersValidator;
import com.boni.passwordvalidatorapi.validator.MinUpperCaseValidator;
import com.boni.passwordvalidatorapi.validator.PasswordValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PasswordValidatorController.class)
class PasswordValidatorControllerTest {

    @Autowired
    private MinCharactersValidator minCharactersValidator;

    @Autowired
    private MinDigitsValidator minDigitsValidator;

    @Autowired
    private MinLowerCaseValidator minLowerCaseValidator;

    @Autowired
    private MinSpecialCharactersValidator minSpecialCharactersValidator;

    @Autowired
    private MinUpperCaseValidator minUpperCaseValidator;

    @Autowired
    private DuplicateCharactersValidator duplicateCharactersValidator;

    @Autowired
    private MockMvc mockMvc;

    private PasswordValidator passwordValidator;

    @BeforeEach
    public void init() {
        var validators = List.of(
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
    public void shoudReturnTrue() throws Exception {

        mockMvc.perform(get("/password-validator/AbTp9!fok"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valid': true}"));
    }

    @Test
    public void shoudReturnFalse() throws Exception {

        mockMvc.perform(get("/password-validator/ "))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'valid': false}"));

        mockMvc.perform(get("/password-validator/aa"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'valid': false}"));

        mockMvc.perform(get("/password-validator/ab"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'valid': false}"));

        mockMvc.perform(get("/password-validator/AAAbbbCc"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'valid': false}"));

        mockMvc.perform(get("/password-validator/AbTp9!foo"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'valid': false}"));

        mockMvc.perform(get("/password-validator/AbTp9!foA"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'valid': false}"));

        mockMvc.perform(get("/password-validator/AbTp9 fok"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().json("{'valid': false}"));

    }
}