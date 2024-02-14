package com.teffal.transaction.services;

import com.teffal.transaction.clients.AccountRestClient;
import com.teffal.transaction.entities.Transaction;
import com.teffal.transaction.entities.TransactionType;
import com.teffal.transaction.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private TransactionRepository transactionRepository;
    private AccountRestClient accountRestClient;
    @Override
    public List<Transaction> getAllTransactionsByAccountNumber(String accountNumber) {
        List<Transaction> transactions =transactionRepository.findAllByAccountNumber(accountNumber);
        for (Transaction transaction:
             transactions) {
            transaction.setAccount(accountRestClient.getAccount(accountNumber));
        }

        return transactionRepository.findAllByAccountNumber(accountNumber);
    }

    @Override
    public Transaction getTransactionById(Long Id) {
        return null;
    }

    @Override
    public List<Transaction> getAllTransactionsByAccountNumberAndDateTransaction(String accountNumber, String date) {
        return null;
    }

    @Override
    public Transaction registerTransaction(Transaction transaction) {

        transactionRepository.save(transaction);

        return transaction;
    }
}
