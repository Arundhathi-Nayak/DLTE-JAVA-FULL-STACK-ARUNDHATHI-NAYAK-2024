package org.example;


import java.io.Serializable;
import java.util.Scanner;
public class Employee  {
        private String employeeName;
        private  EmployeeAddress address;
        private  EmployeeInformation additionalInformation;

    public Employee(String employeeName, EmployeeAddress address, EmployeeInformation additionalInformation) {
        this.employeeName = employeeName;
        this.address = address;
        this.additionalInformation = additionalInformation;
    }

    public Employee() {

    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeName='" + employeeName + '\'' +
                ", address=" + address +
                ", additionalInformation=" + additionalInformation +
                '}';
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public EmployeeAddress getAddress() {
        return address;
    }

    public void setAddress(EmployeeAddress address) {
        this.address = address;
    }

    public EmployeeInformation getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(EmployeeInformation additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
