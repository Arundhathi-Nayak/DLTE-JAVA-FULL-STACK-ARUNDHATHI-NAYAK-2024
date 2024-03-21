package com.account.jpa.controller;

import com.account.jpa.model.Account;
import com.account.jpa.remotes.AccountRepository;
import com.account.jpa.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//http://localhost:8083/accounts/   : Get

//http://localhost:8083/accounts/open  : post


@RestController
@RequestMapping("/accounts")
public class AccountRestApi {
    @Autowired
    AccountServices accountServices;

   @PostMapping("/open")
    public Account openAccount(@RequestBody Account account) {
        return accountServices.callSave(account);
    }
    @PutMapping("/open/update")
    public Account updateAccount(@RequestBody Account account) {
        return accountServices.callSave(account);
    }


    @GetMapping("/")
    public List<Account> getAllAccounts() {
        return accountServices.callFindAll();
    }
}
