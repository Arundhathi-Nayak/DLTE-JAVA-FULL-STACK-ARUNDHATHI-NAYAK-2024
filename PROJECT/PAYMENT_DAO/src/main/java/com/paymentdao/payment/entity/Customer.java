package com.paymentdao.payment.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Customer {
    @NotNull(message = "{customer.id.null}")
    private Integer customerId;

    @NotNull(message = "{customer.name.null}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{customer.name}")
    private String customerName;

    @NotNull(message = "{customer.address.null}")
    private String customerAddress;

    @NotNull(message = "{customer.status.null}")
    @Pattern(regexp = "^[a-zA-Z\\s]*$",message = "{customer.status}")
    private String customerStatus;

    @NotNull(message = "{customer.Contact.null}")
    @Digits(integer = 10, fraction = 0, message = "{customer.contact}")
    private Long customerContact;

    @NotNull(message = "{customer.username.null}")
    private String username;

    @NotNull(message = "{customer.password.null}")
    private String password;

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", customerContact=" + customerContact +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Customer() {
    }

    public Customer(@NotNull(message = "{customer.id.null}") Integer customerId, @NotNull(message = "{customer.name.null}") @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{customer.name}") String customerName, @NotNull(message = "{customer.address.null}") String customerAddress, @NotNull(message = "{customer.status.null}") @Pattern(regexp = "^[a-zA-Z\\s]*$", message = "{customer.status}") String customerStatus, @NotNull(message = "{customer.Contact.null}") @Digits(integer = 10, fraction = 0, message = "{customer.contact}") Long customerContact, @NotNull(message = "{customer.username.null}") String username, @NotNull(message = "{customer.password.null}") String password) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerStatus = customerStatus;
        this.customerContact = customerContact;
        this.username = username;
        this.password = password;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Long getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(Long customerContact) {
        this.customerContact = customerContact;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
