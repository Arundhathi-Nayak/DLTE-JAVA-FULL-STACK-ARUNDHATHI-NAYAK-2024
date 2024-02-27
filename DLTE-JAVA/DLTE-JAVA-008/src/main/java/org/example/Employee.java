package org.example;

import java.sql.SQLOutput;
import java.util.Scanner;
public class Employee {

        private String employeeFirstName;
        private String employeeMiddleName;
        private String employeeLastName;
        private String permanentAddress;
        private String permanentHouseNumber;
        private String permanentState;
        private String permanentCity;
        private Integer permanentPinCode;
        private String temporaryAddress;
        private String temporaryHouseNumber;
        private String temporaryState;
        private String temporaryCity;
        private Integer temporaryPinCode;
        private String emailId;
        private Long phoneNumber;

    @Override
    public String toString() {
        return "Employee{" +
                "employeeFirstName='" + employeeFirstName + '\'' +
                ", employeeMiddleName='" + employeeMiddleName + '\'' +
                ", employeeLastName='" + employeeLastName + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", permanentHouseNumber='" + permanentHouseNumber + '\'' +
                ", permanentState='" + permanentState + '\'' +
                ", permanentCity='" + permanentCity + '\'' +
                ", permanentPinCode=" + permanentPinCode +
                ", temporaryAddress='" + temporaryAddress + '\'' +
                ", temporaryHouseNumber='" + temporaryHouseNumber + '\'' +
                ", temporaryState='" + temporaryState + '\'' +
                ", temporaryCity='" + temporaryCity + '\'' +
                ", temporaryPinCode=" + temporaryPinCode +
                ", emailId='" + emailId + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeMiddleName() {
        return employeeMiddleName;
    }

    public void setEmployeeMiddleName(String employeeMiddleName) {
        this.employeeMiddleName = employeeMiddleName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPermanentHouseNumber() {
        return permanentHouseNumber;
    }

    public void setPermanentHouseNumber(String permanentHouseNumber) {
        this.permanentHouseNumber = permanentHouseNumber;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentCity() {
        return permanentCity;
    }

    public void setPermanentCity(String permanentCity) {
        this.permanentCity = permanentCity;
    }

    public Integer getPermanentPinCode() {
        return permanentPinCode;
    }

    public void setPermanentPinCode(Integer permanentPinCode) {
        this.permanentPinCode = permanentPinCode;
    }

    public String getTemporaryAddress() {
        return temporaryAddress;
    }

    public void setTemporaryAddress(String temporaryAddress) {
        this.temporaryAddress = temporaryAddress;
    }

    public String getTemporaryHouseNumber() {
        return temporaryHouseNumber;
    }

    public void setTemporaryHouseNumber(String temporaryHouseNumber) {
        this.temporaryHouseNumber = temporaryHouseNumber;
    }

    public String getTemporaryState() {
        return temporaryState;
    }

    public void setTemporaryState(String temporaryState) {
        this.temporaryState = temporaryState;
    }

    public String getTemporaryCity() {
        return temporaryCity;
    }

    public void setTemporaryCity(String temporaryCity) {
        this.temporaryCity = temporaryCity;
    }

    public Integer getTemporaryPinCode() {
        return temporaryPinCode;
    }

    public void setTemporaryPinCode(Integer temporaryPinCode) {
        this.temporaryPinCode = temporaryPinCode;
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

    public Employee(String employeeFirstName, String employeeMiddleName, String employeeLastName, String permanentAddress, String permanentHouseNumber, String permanentState, String permanentCity, Integer permanentPinCode, String temporaryAddress, String temporaryHouseNumber, String temporaryState, String temporaryCity, Integer temporaryPinCode, String emailId, Long phoneNumber) {
        this.employeeFirstName = employeeFirstName;
        this.employeeMiddleName = employeeMiddleName;
        this.employeeLastName = employeeLastName;
        this.permanentAddress = permanentAddress;
        this.permanentHouseNumber = permanentHouseNumber;
        this.permanentState = permanentState;
        this.permanentCity = permanentCity;
        this.permanentPinCode = permanentPinCode;
        this.temporaryAddress = temporaryAddress;
        this.temporaryHouseNumber = temporaryHouseNumber;
        this.temporaryState = temporaryState;
        this.temporaryCity = temporaryCity;
        this.temporaryPinCode = temporaryPinCode;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }
//         Scanner scanner=new Scanner(System.in);
//         System.out.println("Enter your First name :");
//         employeeFirstName = scanner.nextLine();
//         System.out.println("Enter your middle name :");
//         employeeMiddleName = scanner.nextLine();
//         System.out.println("Enter your Last name :");
//         employeeLastName = scanner.nextLine();
//         System.out.println("Enter your permanent address :");
//         System.out.println("Enter the Address :");
//         permanentAddress=scanner.nextLine();
//         System.out.println("Enter the House Number :");
//         permanentHouseNumber=scanner.nextLine();
//         System.out.println("Enter the city :");
//         permanentCity=scanner.nextLine();
//         System.out.println("Enter the State :");
//         permanentState= scanner.nextLine();
//         System.out.println("Enter the PinCode :");
//         permanentPinCode=scanner.nextInt();
//         System.out.println("Enter your temporary address :");
//         System.out.println("Enter the Address :");
//         temporaryAddress=scanner.nextLine();
//         System.out.println("Enter the House Number :");
//         temporaryHouseNumber=scanner.nextLine();
//         System.out.println("Enter the city :");
//         temporaryCity=scanner.nextLine();
//         System.out.println("Enter the State :");
//         temporaryState= scanner.nextLine();
//         System.out.println("Enter the PinCode :");
//         temporaryPinCode=scanner.nextInt();
//         System.out.println("Enter the Email Id :");
//         emailId=scanner.next();
//         System.out.println("Enter the Phone Number :");
//         phoneNumber=scanner.nextLong();
//         System.out.println("Hello, " + employeeFirstName +" " +employeeMiddleName+" "+employeeLastName);
//         System.out.println("Your Permanent Address :"+permanentHouseNumber+" "+permanentAddress+" "+permanentCity+" ");

}
