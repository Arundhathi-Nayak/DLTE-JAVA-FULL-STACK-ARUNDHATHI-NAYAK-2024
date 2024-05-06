package com.paymentdao.payment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyBankOfficials implements UserDetails {

    private Integer customerId;
    private String customerName;
    private String customerAddress;
    private String customerStatus;
    private Long customerContact;
    private String username;
    private String password;
    private  Integer attempts;

    public MyBankOfficials() {
    }

    public MyBankOfficials(Integer customerId, String customerName, String customerAddress, String customerStatus, Long customerContact, String username, String password, Integer attempts) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
        this.attempts = attempts;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public int getMaxAttempt() {
        return maxAttempt;
    }

    private final int maxAttempt=3;

    public Integer getCustomerId() {
        return customerId;
    }


    public String getCustomerName() {
        return customerName;
    }


    public String getCustomerAddress() {
        return customerAddress;
    }


    public String getCustomerStatus() {
        return customerStatus;
    }


    public Long getCustomerContact() {
        return customerContact;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAttempts() {
        return attempts;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
