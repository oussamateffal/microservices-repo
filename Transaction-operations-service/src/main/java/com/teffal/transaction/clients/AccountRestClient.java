package com.teffal.transaction.clients;

import com.teffal.transaction.model.Account;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BANKACCOUNT-SERVICE")
public interface AccountRestClient {

    @GetMapping("/accounts/getAccount/{accountNumber}")
    @CircuitBreaker(name = "accountService", fallbackMethod = "getDefaultAccount")
    public Account getAccount(@PathVariable String accountNumber);

    default Account getDefaultAccount(String accountNumber,Exception e){


    return Account.builder()
            .accountId(accountNumber)
            .accountType("defaultType")
            .balance(0)
            .build();
    }
}