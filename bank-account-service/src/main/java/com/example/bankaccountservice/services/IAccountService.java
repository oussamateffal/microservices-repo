package com.example.bankaccountservice.services;

import com.example.bankaccountservice.entities.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Account createAccount(Account account);
    Optional<Account> getAccount(String accountNumber);
    Account updateAccount(Account account);
    void deleteAccount(String accountNumber);
    void deposit(String accountNumber, double amount);
    void withdraw(String accountNumber, double amount);
    void transfer(String fromAccountNumber, String toAccountNumber, double amount);
    List<Account> getAllAccounts();
    Optional<Account> findAccountByCustomerId(Long customerId);


}
