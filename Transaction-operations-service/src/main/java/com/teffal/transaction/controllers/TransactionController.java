package com.teffal.transaction.controllers;

import com.teffal.transaction.entities.Transaction;
import com.teffal.transaction.services.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("transactions")
public class TransactionController {

    private TransactionServiceImpl transactionService;

    @GetMapping("getAllTransactions/{accountNumber}")
    public List<Transaction> getAllTransactionsByAccountNumber(@PathVariable String accountNumber){
        return transactionService.getAllTransactionsByAccountNumber(accountNumber);
    }

    @PostMapping("/registerTransaction")
    public Transaction registerTransaction(@RequestBody Transaction transaction){
        transactionService.registerTransaction(transaction);

        return transaction;
    }
}
