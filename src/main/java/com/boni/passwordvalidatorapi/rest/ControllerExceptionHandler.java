package com.boni.passwordvalidatorapi.rest;

import com.boni.passwordvalidatorapi.validator.exception.ListValidatorExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(ListValidatorExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<PasswordValidatorResponse> listValidatorExeption(ListValidatorExeption listValidatorExeption) {

        listValidatorExeption.getErrorMessages().forEach(log::error);
        return ResponseEntity.badRequest().body(new PasswordValidatorResponse(false));
    }
}