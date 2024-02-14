package com.teffal.customerservice.controlers;

import com.teffal.customerservice.entities.Customer;
import com.teffal.customerservice.services.CustomerServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")

public class CustomerController {

    private CustomerServiceImpl customerService;

    @PostMapping("/saveCustomer")
    public Customer saveCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return customer;
    }
    @GetMapping("/getAllCustomers")
    public List<Customer> findAllCustomers(){
        return customerService.findAllCustomers();
    }

    @GetMapping("/findById/{id}")
    public Customer findCustomerById(@PathVariable Long id){
        return customerService.findCustomerById(id).orElseThrow(()->new RuntimeException("Customer not found"));
    }


}
