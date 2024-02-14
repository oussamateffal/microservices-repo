package com.teffal.transaction.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor @Builder

public class Account {

    private String accountId;
    private double balance;
    private String  accountType;
    private Long customerId;
    private LocalDate dateCreated;


}
