package com.example.bankaccountservice.entities;

import com.example.bankaccountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String accountId;
    private double balance;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private Long customerId;
    @Transient
    private Customer customer;
    private LocalDate dateCreated;

}
