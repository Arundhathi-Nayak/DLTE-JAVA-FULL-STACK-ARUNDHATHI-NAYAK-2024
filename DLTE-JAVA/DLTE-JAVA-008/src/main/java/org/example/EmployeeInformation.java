package org.example;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

import java.io.Serializable;

public class EmployeeInformation  {
    private String emailId;
    private Long phoneNumber;

    public EmployeeInformation(String emailId, Long phoneNumber) {
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public EmployeeInformation() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "EmployeeInformation{" +
                "emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}
