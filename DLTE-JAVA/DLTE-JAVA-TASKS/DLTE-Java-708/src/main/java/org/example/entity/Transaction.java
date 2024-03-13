package org.example.entity;

import java.util.Date;

public class Transaction {
    private Date date;
    private long transactionID;
    private String user;
    private double amount;
    private double balance;

    public Transaction() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Transaction(Date date, long transactionID, String user, double amount, double balance) {
        this.date = date;
        this.transactionID = transactionID;
        this.user = user;
        this.amount = amount;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "date=" + date +
                ", transactionID=" + transactionID +
                ", user='" + user + '\'' +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }
}
