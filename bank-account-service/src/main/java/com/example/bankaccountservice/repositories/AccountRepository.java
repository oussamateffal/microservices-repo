package com.example.bankaccountservice.repositories;

import com.example.bankaccountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT a FROM Account a WHERE a.customerId = :customerId")
    Optional<Account> findAccountByCustomerId(Long customerId);

    List<Account> findAccountsByCustomerId(Long customerId);
}
