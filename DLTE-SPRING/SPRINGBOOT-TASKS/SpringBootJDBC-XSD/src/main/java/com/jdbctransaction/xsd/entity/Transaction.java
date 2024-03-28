package com.jdbctransaction.xsd.entity;

import java.util.Date;

public class Transaction {

    private Long transactionId;
    private Date TransactionDate;
    private String transactionTo;
    private String transactionBy;
    private Integer transactionAmount;
    private String transactionRemarks;

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", TransactionDate=" + TransactionDate +
                ", transactionTo='" + transactionTo + '\'' +
                ", transactionBy='" + transactionBy + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", transactionRemarks='" + transactionRemarks + '\'' +
                '}';
    }

    public Transaction() {
    }

    public Transaction(Long transactionId, Date transactionDate, String transactionTo, String transactionBy, Integer transactionAmount, String transactionRemarks) {
        this.transactionId = transactionId;
        TransactionDate = transactionDate;
        this.transactionTo = transactionTo;
        this.transactionBy = transactionBy;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        TransactionDate = transactionDate;
    }

    public String getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(String transactionTo) {
        this.transactionTo = transactionTo;
    }

    public String getTransactionBy() {
        return transactionBy;
    }

    public void setTransactionBy(String transactionBy) {
        this.transactionBy = transactionBy;
    }

    public Integer getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }
}
