package com.account.jpa.services;

import com.account.jpa.model.Account;
import com.account.jpa.remotes.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServices {
    @Autowired
    AccountRepository accountRepository;

    public Account callSave(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> callFindAll() {
        return (List<Account>) accountRepository.findAll();
    }

}