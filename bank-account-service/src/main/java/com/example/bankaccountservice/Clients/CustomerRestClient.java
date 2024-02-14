package com.example.bankaccountservice.Clients;

import com.example.bankaccountservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "CUSTOMER-SERVICE")
public interface CustomerRestClient {
    @GetMapping("/customers/findById/{id}")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getCustomerByIdFallback")
    Customer getCustomerById(@PathVariable Long id);

    @GetMapping("/customers/getAllCustomers")
    @CircuitBreaker(name = "customerService", fallbackMethod = "getAllCustomersFallback")
    List<Customer> getAllCustomers();

    default Customer getCustomerByIdFallback(Long id,Exception e){
        Customer customer = Customer.builder()
                .id(id)
                .firstName("Customer not available")
                .lastName("Customer not available")
                .email("Customer not available")
                .build();
        return customer;

    }
    default List<Customer> getAllCustomersFallback(Exception e){
        return List.of(Customer.builder()
                .id(0L)
                .firstName("Customer not available")
                .lastName("Customer not available")
                .email("Customer not available")
                .build())   ;

    }
}
