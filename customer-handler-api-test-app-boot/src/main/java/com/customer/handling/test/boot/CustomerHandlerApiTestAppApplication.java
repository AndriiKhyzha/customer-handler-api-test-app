package com.customer.handling.test.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.customer.handling.test")
public class CustomerHandlerApiTestAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerHandlerApiTestAppApplication.class, args);

	}
}

