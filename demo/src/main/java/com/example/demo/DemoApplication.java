package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure()
				.directory("Pre-test/demo/.env") 
				.load();

		System.setProperty("MYSQL_DATABASE_HOST", dotenv.get("MYSQL_DATABASE_HOST"));
		System.setProperty("MYSQL_DATABASE_USERNAME", dotenv.get("MYSQL_DATABASE_USERNAME"));
		System.setProperty("MYSQL_DATABASE_PASSWORD", dotenv.get("MYSQL_DATABASE_PASSWORD"));
		System.setProperty("MYSQL_DATABASE_NAME", dotenv.get("MYSQL_DATABASE_NAME"));

		SpringApplication.run(DemoApplication.class, args);
	}

}
