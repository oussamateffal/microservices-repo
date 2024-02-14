package com.teffal.transaction.entities;

import com.teffal.transaction.model.Account;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private Long customerId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    private LocalDate dateTransaction;
    private LocalTime timeTransaction;
    private Double montant;
    @Transient
    private Account account;

}
