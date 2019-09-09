package com.example.h2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class H2SampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2SampleApplication.class, args);
	}

}
