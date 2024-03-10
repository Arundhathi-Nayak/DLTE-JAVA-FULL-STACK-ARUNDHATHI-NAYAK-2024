package org.example;

import java.util.Date;

public class Transaction {
    private Date transactionDate;
    private  Integer transactionAmount;
    private String transactionTo;
    private String transactionRemarks;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDate=" + transactionDate +
                ", transactionAmount=" + transactionAmount +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                '}';
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public Transaction(Date transactionDate, Integer transactionAmount, String transactionTo, String transactionRemarks) {
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionTo = transactionTo;
        this.transactionRemarks = transactionRemarks;
    }
}
