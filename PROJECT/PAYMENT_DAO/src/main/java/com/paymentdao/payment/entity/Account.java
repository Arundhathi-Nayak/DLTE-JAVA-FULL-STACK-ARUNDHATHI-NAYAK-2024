package com.paymentdao.payment.entity;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


// customer account details
public class Account {
    @NotNull
    private Integer accountId;

    @NotNull
    private Integer customerId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{Pattern.accountType.message}")
    private String accountType;

    @NotNull
    @Range(min = 100000000000L, max = 999999999999L,message = "{payee.senderAcc}")
    @Digits(integer = 12, fraction = 0, message = "{payee.senderAcc}")
    private Long accountNumber;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{Pattern.accountStatus.message}")
    private String accountStatus;

    @NotNull
    @Digits(integer = 8, fraction = 3, message = "{Digits.accountBalance.message}")
    private Integer accountBalance;


    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", customerId=" + customerId +
                ", accountType='" + accountType + '\'' +
                ", accountNumber=" + accountNumber +
                ", accountStatus='" + accountStatus + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Integer getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Integer accountBalance) {
        this.accountBalance = accountBalance;
    }
}
