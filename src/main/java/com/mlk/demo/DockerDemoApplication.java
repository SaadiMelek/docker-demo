package com.mlk.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class DockerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DockerDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(StudentRepository studentRepository) {
		return args -> {
			studentRepository.save(
					new Student("Firstname", "Lastname", LocalDate.now().minusYears(20),"email", 20)
			);
		};
	}
}
