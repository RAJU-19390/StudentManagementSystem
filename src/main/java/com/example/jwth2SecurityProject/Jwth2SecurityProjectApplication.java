package com.example.jwth2SecurityProject;

import org.springframework.boot.SpringApplication;
import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jwth2SecurityProjectApplication {

	public static final Logger logger = Logger.getLogger(Jwth2SecurityProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Jwth2SecurityProjectApplication.class, args);
		logger.info("JWT Security Project Application started successfully.");
	}
}

