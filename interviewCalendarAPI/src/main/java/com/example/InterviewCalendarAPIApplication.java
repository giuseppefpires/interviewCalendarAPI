package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.repository.ExtendedRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.repository", repositoryBaseClass = ExtendedRepositoryImpl.class)
public class InterviewCalendarAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewCalendarAPIApplication.class, args);
	}
	
}
