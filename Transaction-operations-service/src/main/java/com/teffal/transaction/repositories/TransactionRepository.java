package com.teffal.transaction.repositories;

import com.teffal.transaction.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findAllByAccountNumber(String accountNumber);
}
