package com.example.bankaccountservice.model;

import com.fasterxml.jackson.databind.node.LongNode;
import jakarta.persistence.Transient;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Transaction {
    private Long id;
    private String accountNumber;
    private Long customerId;
    private TransactionType transactionType;
    private LocalDate dateTransaction;
    private LocalTime timeTransaction;
    private Double montant;

}
