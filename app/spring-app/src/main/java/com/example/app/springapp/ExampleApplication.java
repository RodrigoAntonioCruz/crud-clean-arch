package com.example.app.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example"})
@EnableMongoRepositories("com.example.adapter.output.repository")
public class ExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args);
	}
}
