package com.teffal.customerservice;

import com.teffal.customerservice.entities.Customer;
import com.teffal.customerservice.services.CustomerServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerServiceImpl customerService){
		return args -> {

			customerService.saveCustomer(Customer.builder()
					.email("oussamateffal@gmail.com")
					.lastName("Oussama")
					.firstName("Teffal")
					.phone("0666666666")
					.build());
			customerService.saveCustomer(Customer.builder()
					.email("mohamed@gmail.com")
					.lastName("mohamed")
					.firstName("arabi")
					.phone("0666666776")
					.build());
		};
	}

}
