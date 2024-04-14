package com.paymentdao.payment.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

// payee details
public class Payee {
    @NotNull
    @Digits(integer = 3, fraction = 0, message = "{payee.id}")
    private Integer payeeId;

    @NotNull
    @Range(min = 100000000000L, max = 999999999999L,message = "{payee.senderAcc}")
    @Digits(integer = 12, fraction = 0, message = "{payee.senderAcc}")

    private Long senderAccountNumber;

    @NotNull
    @Range(min = 100000000000L, max = 999999999999L,message = "{payee.payeeAcc}")
    @Digits(integer = 12, fraction = 0, message = "{payee.payeeAcc}")
    private Long payeeAccountNumber;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{payee.holder}")
    private String payeeName;


    public Payee() {
    }

    public Payee(Integer payeeId, Long senderAccountNumber, Long payeeAccountNumber, String payeeName) {
        this.payeeId = payeeId;
        this.senderAccountNumber = senderAccountNumber;
        this.payeeAccountNumber = payeeAccountNumber;
        this.payeeName = payeeName;
    }

    public Integer getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(Integer payeeId) {
        this.payeeId = payeeId;
    }

    public Long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(Long senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public Long getPayeeAccountNumber() {
        return payeeAccountNumber;
    }

    public void setPayeeAccountNumber(Long payeeAccountNumber) {
        this.payeeAccountNumber = payeeAccountNumber;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }
}
