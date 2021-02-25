package com.boni.passwordvalidatorapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class PasswordValidatorApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordValidatorApiApplication.class, args);
	}

}
