package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DemoApplication {
	private static final Logger logging = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		logging.info("Performing action...");
		Dotenv dotenv = Dotenv.configure()
				.directory("demo\\.env") 
				.load();

		System.setProperty("MYSQL_DATABASE_HOST", dotenv.get("MYSQL_DATABASE_HOST"));
		System.setProperty("MYSQL_DATABASE_USERNAME", dotenv.get("MYSQL_DATABASE_USERNAME"));
		System.setProperty("MYSQL_DATABASE_PASSWORD", dotenv.get("MYSQL_DATABASE_PASSWORD"));
		System.setProperty("MYSQL_DATABASE_NAME", dotenv.get("MYSQL_DATABASE_NAME"));

		SpringApplication.run(DemoApplication.class, args);
	}

}
