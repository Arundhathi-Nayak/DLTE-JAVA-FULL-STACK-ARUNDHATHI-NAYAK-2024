package com.hibernate.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="names_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @SequenceGenerator(name = "native", sequenceName = "product_seq",allocationSize = 1)
    private long transactionID;
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    private String name;
    private double transactionAmount;
    private double balance;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", transactionDate=" + transactionDate +
                ", name='" + name + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", balance=" + balance +
                '}';
    }

    public Transaction() {
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Transaction(long transactionID, Date transactionDate, String name, double transactionAmount, double balance) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.name = name;
        this.transactionAmount = transactionAmount;
        this.balance = balance;
    }
}
