package com.xgeeks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.xgeeks.repository.ExtendedRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.xgeeks.repository", repositoryBaseClass = ExtendedRepositoryImpl.class)
public class XGeeksApplication {

	public static void main(String[] args) {
		SpringApplication.run(XGeeksApplication.class, args);
	}
	
}
