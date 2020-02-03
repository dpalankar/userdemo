package com.example.userdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ SecurityConfig.class })
public class UserdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserdemoApplication.class, args);
	}

}
