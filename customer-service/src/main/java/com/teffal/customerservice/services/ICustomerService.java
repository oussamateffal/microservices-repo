package com.teffal.customerservice.services;

import com.teffal.customerservice.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    public Customer saveCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public void deleteCustomer(Customer customer);
    public Optional<Customer> findCustomerById(Long id);
    List<Customer> findAllCustomers();
}
