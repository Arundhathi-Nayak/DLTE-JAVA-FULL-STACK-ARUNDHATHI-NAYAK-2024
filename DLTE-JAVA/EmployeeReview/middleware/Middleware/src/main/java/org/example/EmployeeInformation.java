package org.example;

import java.io.Serializable;

public class EmployeeInformation implements Serializable {
    private String emailId;
    private long phoneNumber;

    @Override
    public String toString() {
        return "EmployeeInformation{" +
                "emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }


    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public EmployeeInformation(String emailId, long phoneNumber) {
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }
}
