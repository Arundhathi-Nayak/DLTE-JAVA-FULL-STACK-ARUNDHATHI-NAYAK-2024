package com.account.jpa.model;

import javax.persistence.*;

@Entity
@Table(name="user_account")
public class Account {
//the genearator value is throwing error
    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;
    private String email;
    private String name;
    private double balance;

    public Account(long accountNumber, String email, String name, double balance) {
        this.accountNumber = accountNumber;
        this.email = email;
        this.name = name;
        this.balance = balance;
    }

    public Account() {

    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
