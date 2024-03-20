package com.account.jpa.controller;

import com.account.jpa.model.Account;
import com.account.jpa.remotes.AccountRepository;
import com.account.jpa.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestApi {
    @Autowired
    AccountServices accountServices;
    AccountRepository accountRepository;

    @PostMapping("/open")
    public Account openAccount(@RequestBody Account account) {
        return accountServices.callSave(account);
    }

    @PutMapping("/update/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Account existingAccount = accountRepository.findById(id).orElse(null);
        if (existingAccount != null) {
            return accountServices.callSave(existingAccount);
        } else {
            throw new RuntimeException("Account not found with id: " + id);
        }
    }

    @GetMapping("")
    public List<Account> getAllAccounts() {
        return accountServices.callFindAll();
    }
}
