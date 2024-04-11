package com.bean.lazy;

import java.util.ArrayList;
import java.util.List;

public class Bank {
//    private List<Account> accounts;
//
//    public Bank() {
//        // Initialize accounts list eagerly
//        this.accounts = new ArrayList<>();
//        // Simulate adding some accounts
//        this.accounts.add(new Account("123456", 1000.0));
//        this.accounts.add(new Account("789012", 500.0));
//        this.accounts.add(new Account("345678", 1500.0));
//    }
//
//    public List<Account> getAccounts() {
//        return accounts;
//    }





    private List<Account> accounts;

    public Bank() {
        // Initialize accounts list lazily
        this.accounts = null;
    }

    public List<Account> getAccounts() {
        // Lazy initialization of accounts
        if (accounts == null) {
            accounts = new ArrayList<>();
            // Simulate adding some accounts
            accounts.add(new Account("123456", 1000.0));
            accounts.add(new Account("789012", 500.0));
            accounts.add(new Account("345678", 1500.0));
        }
        return accounts;
    }
}
