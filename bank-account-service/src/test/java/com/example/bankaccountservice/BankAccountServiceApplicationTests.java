package com.example.bankaccountservice;

import com.example.bankaccountservice.Clients.TransactionRestClient;
import com.example.bankaccountservice.entities.Account;
import com.example.bankaccountservice.repositories.AccountRepository;
import com.example.bankaccountservice.services.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRestClient transactionRestClient;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void transferFundsSuccessfully() {
        Account fromAccount = new Account();
        fromAccount.setBalance(500);
        when(accountRepository.findById("123")).thenReturn(Optional.of(fromAccount));

        Account toAccount = new Account();
        toAccount.setBalance(200);
        when(accountRepository.findById("456")).thenReturn(Optional.of(toAccount));

        accountService.transfer("123", "456", 100);

        assertEquals(400, fromAccount.getBalance());
        assertEquals(300, toAccount.getBalance());
    }

    @Test
    void transferFundsInsufficientBalance() {
        Account fromAccount = new Account();
        fromAccount.setBalance(50);
        when(accountRepository.findById("123")).thenReturn(Optional.of(fromAccount));

        Account toAccount = new Account();
        toAccount.setBalance(200);
        when(accountRepository.findById("456")).thenReturn(Optional.of(toAccount));

        assertThrows(RuntimeException.class, () -> accountService.transfer("123", "456", 100));
    }

    @Test
    void transferFundsSameAccount() {
        assertThrows(RuntimeException.class, () -> accountService.transfer("123", "123", 100));
    }

    @Test
    void transferFundsAccountNotFound() {
        when(accountRepository.findById("123")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> accountService.transfer("123", "456", 100));
    }
}