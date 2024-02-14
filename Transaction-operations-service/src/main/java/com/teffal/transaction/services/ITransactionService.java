package com.teffal.transaction.services;

import com.teffal.transaction.entities.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ITransactionService {

    List<Transaction> getAllTransactionsByAccountNumber(String accountNumber);
    Transaction getTransactionById(Long Id);
    List<Transaction> getAllTransactionsByAccountNumberAndDateTransaction(String accountNumber, String date);
    Transaction registerTransaction(Transaction transaction);

}
