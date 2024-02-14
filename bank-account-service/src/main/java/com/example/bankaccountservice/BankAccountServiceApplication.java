package com.example.bankaccountservice;

import com.example.bankaccountservice.Clients.CustomerRestClient;
import com.example.bankaccountservice.entities.Account;
import com.example.bankaccountservice.entities.AccountType;
import com.example.bankaccountservice.services.AccountServiceImpl;
import com.example.bankaccountservice.services.IAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static java.text.ChoiceFormat.nextDouble;

@SpringBootApplication
@EnableFeignClients
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankAccountServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(AccountServiceImpl accountService, CustomerRestClient customerRestClient){
        return args -> {
            customerRestClient.getAllCustomers().forEach(customer -> {
             /*   accountService.createAccount(Account.builder()
                        .balance(nextDouble(Math.random() * 500000))
                        .accountType(AccountType.CURRENT_ACCOUNT)
                        .dateCreated(LocalDate.now())
                        .customerId(customer.getId())
                        .build());*/
            });





        };
    }
}
