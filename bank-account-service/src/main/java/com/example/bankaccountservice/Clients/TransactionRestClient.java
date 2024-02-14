package com.example.bankaccountservice.Clients;

import com.example.bankaccountservice.model.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "TRANSACTION-SERVICE")
public interface TransactionRestClient {

    @PostMapping("/transactions/registerTransaction")
    Transaction registerTransaction(@RequestBody Transaction transaction);

}
