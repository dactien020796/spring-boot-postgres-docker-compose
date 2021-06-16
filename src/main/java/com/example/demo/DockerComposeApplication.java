package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class DockerComposeApplication {

	private final CustomerRepository repository;
	Logger logger = LoggerFactory.getLogger(DockerComposeApplication.class);

	public DockerComposeApplication(CustomerRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DockerComposeApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		List<Customer> allCustomers = this.repository.findAll();
		logger.info("Number of customers: " + allCustomers.size());

		Customer newCustomer = new Customer();
		newCustomer.setFirstName("John");
		newCustomer.setLastName("Doe");
		logger.info("Saving new customer...");
		this.repository.save(newCustomer);

		allCustomers = this.repository.findAll();
		logger.info("Number of customers: " + allCustomers.size());
	}

}
