package com.example.bankaccountservice.services;

import com.example.bankaccountservice.Clients.CustomerRestClient;
import com.example.bankaccountservice.Clients.TransactionRestClient;
import com.example.bankaccountservice.entities.Account;
import com.example.bankaccountservice.entities.AccountType;
import com.example.bankaccountservice.model.Customer;
import com.example.bankaccountservice.model.Transaction;
import com.example.bankaccountservice.model.TransactionType;
import com.example.bankaccountservice.repositories.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImpl implements IAccountService{
    private AccountRepository accountRepository;
    private CustomerRestClient customerRestClient;
    private TransactionRestClient transactionRestClient;

    @Override
    public Account createAccount(Account account) {
        Customer customer =customerRestClient.getCustomerById(account.getCustomerId());
        Account exsistingAccount= findAccountByCustomerId(account.getCustomerId()).orElse(null);

        if (customer!=null && exsistingAccount!=null ){

          if (account.getAccountType().equals(exsistingAccount.getAccountType())){
                    throw new RuntimeException("Customer has already an account!!");
                }
        }

            account.setDateCreated(LocalDate.now());
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getAccount(String accountNumber) {
        Account account = accountRepository.findById(accountNumber).orElse(null);
        if(account != null){
            account.setCustomer(customerRestClient.getCustomerById(account.getCustomerId()));
        }


        return Optional.ofNullable(account);
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public void deleteAccount(String accountNumber) {

    }

    @Override
    public void deposit(String accountNumber, double amount) {
        TransactionType transactionType =TransactionType.DEPOSIT;
        Account account = accountRepository.findById(accountNumber).orElse(null);
        if(account != null){
            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);
            Transaction transaction =Transaction.builder()
                    .accountNumber(accountNumber)
                    .customerId(account.getCustomerId())
                    .transactionType(transactionType)
                    .dateTransaction(LocalDate.now())
                    .timeTransaction(LocalTime.now())
                    .montant(amount)
                    .build();
            transactionRestClient.registerTransaction(transaction);

        }

    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        TransactionType transactionType =TransactionType.WITHDRAW;

        Account account = accountRepository.findById(accountNumber).orElse(null);
        if(account != null ){
            if (account.getBalance() < amount){
                throw new RuntimeException("Insufficient balance");
            }
            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);

            Transaction transaction =Transaction.builder()
                    .accountNumber(accountNumber)
                    .customerId(account.getCustomerId())
                    .transactionType(transactionType)
                    .dateTransaction(LocalDate.now())
                    .timeTransaction(LocalTime.now())
                    .montant(-amount)
                    .build();
            transactionRestClient.registerTransaction(transaction);
        }

    }

    @Override

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        TransactionType transactionType =TransactionType.TRANSFER;
        if (fromAccountNumber.equals(toAccountNumber)){
            throw new RuntimeException("Transfer Impossible!");
        }
        Account fromAccount = accountRepository.findById(fromAccountNumber).orElseThrow(() -> new RuntimeException("Account not found: " + fromAccountNumber));
        Account toAccount = accountRepository.findById(toAccountNumber).orElseThrow(() -> new RuntimeException("Account not found: " + toAccountNumber));
        if (fromAccount.getBalance() < amount){
            throw new RuntimeException("Insufficient balance");
        }
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

            Transaction transaction1 =Transaction.builder()
                    .accountNumber(fromAccountNumber)
                    .customerId(fromAccount.getCustomerId())
                    .transactionType(transactionType)
                    .dateTransaction(LocalDate.now())
                    .timeTransaction(LocalTime.now())
                    .montant(-amount)
                    .build();
            transactionRestClient.registerTransaction(transaction1);

            Transaction transaction2 =Transaction.builder()
                    .accountNumber(toAccountNumber)
                    .customerId(toAccount.getCustomerId())
                    .transactionType(transactionType)
                    .dateTransaction(LocalDate.now())
                    .timeTransaction(transaction1.getTimeTransaction())
                    .montant(amount)
                    .build();
            transactionRestClient.registerTransaction(transaction2);
        }



    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> {
            account.setCustomer(customerRestClient.getCustomerById(account.getCustomerId()));
        });
        return accounts;
    }

    @Override
    public Optional<Account> findAccountByCustomerId(Long customerId) {
        return accountRepository.findAccountByCustomerId(customerId);
    }
    //générer une fonction qui retourne la liste des comptes d'un client
    public List<Account> findAccountsByCustomerId(Long customerId){
        return accountRepository.findAccountsByCustomerId(customerId);
    }
}
