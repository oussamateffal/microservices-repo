package com.example.bankaccountservice.controlers;

import com.example.bankaccountservice.Clients.CustomerRestClient;
import com.example.bankaccountservice.entities.Account;
import com.example.bankaccountservice.services.AccountServiceImpl;
import com.example.bankaccountservice.services.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private AccountServiceImpl accountService;

    @PostMapping("/createAccount")
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @GetMapping("/getAccount/{accountNumber}")
    public Account getAccount( @PathVariable  String accountNumber){
        return accountService.getAccount(accountNumber).orElse(null);
    }
    @GetMapping("/getAllAccounts")
    public List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
    @PostMapping("/deposit/{accountNumber}/{amount}")
    public void deposit(@PathVariable String accountNumber,@PathVariable double amount){

        accountService.deposit(accountNumber,amount);
    }

    @PostMapping("/withdraw/{accountNumber}/{amount}")
    public void withdraw(@PathVariable String accountNumber,@PathVariable double amount){
        accountService.withdraw(accountNumber,amount);
    }
    @PostMapping("/transfer/{fromAccount}/{toAccount}/{amount}")
    public void transfer(@PathVariable String fromAccount,@PathVariable String toAccount,@PathVariable double amount){
        accountService.transfer(fromAccount,toAccount,amount);
    }
}
