package com.springjdbc.driver.entity;

import javax.validation.constraints.*;
import java.util.Date;

public class Transaction {

    @NotNull(message ="{transactionId.notNull}")
    @Digits(integer = 3,fraction = 0)
    private Long transactionId;
    @PastOrPresent(message = "{transaction.date}")
    private Date TransactionDate;
    @NotBlank(message = "{transactionFrom.notNull}")
    private String transactionBy;
    @NotBlank(message = "{transactionTo.notNull}")
    private String transactionTo;
    @NotNull(message = "{transactionAmount.notnull}")
    @DecimalMin(value = "0.01",message = "{transactionAmount.not.zero}")
    private Long transactionAmount;
    @NotBlank(message = "{transactionRemarks.notNull}")
    @Pattern(regexp = "(?i)friend|bills|family|general", message = "{transaction.type.pattern}")
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

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(String transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public Transaction(@NotNull(message = "{transactionId.notNull}") @Digits(integer = 3, fraction = 0) Long transactionId, @PastOrPresent(message = "{transaction.date}") Date transactionDate, @NotBlank(message = "{transactionFrom.notNull}") String transactionBy, @NotBlank(message = "{transactionTo.notNull}") String transactionTo, @NotNull(message = "{transactionAmount.notnull}") @DecimalMin(value = "0.01", message = "{transactionAmount.not.zero}") Long transactionAmount, @NotBlank(message = "{transactionRemarks.notNull}") @Pattern(regexp = "(?i)friend|bills|family|general", message = "{transaction.type.pattern}") String transactionRemarks) {
        this.transactionId = transactionId;
        TransactionDate = transactionDate;
        this.transactionBy = transactionBy;
        this.transactionTo = transactionTo;
        this.transactionAmount = transactionAmount;
        this.transactionRemarks = transactionRemarks;
    }
}
