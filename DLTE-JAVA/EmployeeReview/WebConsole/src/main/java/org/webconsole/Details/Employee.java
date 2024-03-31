package org.webconsole.Details;

public class Employee {
    EmployeeBasicDetails employeeBasicDetails;
    EmployeeAddress employeePermanentAddress;
    EmployeeAddress employeeTemporaryAddress;

    public Employee() {
    }

    public Employee(EmployeeBasicDetails employeeBasicDetails, EmployeeAddress employeePermanentAddress, EmployeeAddress getEmployeeTemporaryAddress) {
        this.employeeBasicDetails = employeeBasicDetails;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = getEmployeeTemporaryAddress;
    }

    public EmployeeBasicDetails getEmployeeBasicDetails() {
        return employeeBasicDetails;
    }

    public void setEmployeeBasicDetails(EmployeeBasicDetails employeeBasicDetails) {
        this.employeeBasicDetails = employeeBasicDetails;
    }

    public EmployeeAddress getEmployeePermanentAddress() {
        return employeePermanentAddress;
    }

    public void setEmployeePermanentAddress(EmployeeAddress employeePermanentAddress) {
        this.employeePermanentAddress = employeePermanentAddress;
    }

    public EmployeeAddress getEmployeeTemporaryAddress() {
        return employeeTemporaryAddress;
    }

    public void setEmployeeTemporaryAddress(EmployeeAddress getEmployeeTemporaryAddress) {
        this.employeeTemporaryAddress = getEmployeeTemporaryAddress;
    }
    public String displayEmployeeDetails() {
        return "Employee ID: " + employeeBasicDetails.getEmployeeId() +
                "\nName: " + employeeBasicDetails.getEmployeeName() +
                "\nEmail: " + employeeBasicDetails.getEmailId() +
                "\nPhone Number: " + employeeBasicDetails.getPhoneNumber() +
                "\nPermanent Address: " + employeePermanentAddress.getAddress() +
                "\nPermanent House Number: " + employeePermanentAddress.getHouseNumber() +
                "\nPermanent City: " + employeePermanentAddress.getCity() +
                "\nPermanent State: " + employeePermanentAddress.getState() +
                "\nPermanent Pin Code: " + employeePermanentAddress.getPinCode() +
                "\nTemporary Address: " + employeeTemporaryAddress.getAddress() +
                "\nTemporary House Number: " + employeeTemporaryAddress.getHouseNumber() +
                "\nTemporary City: " + employeeTemporaryAddress.getCity() +
                "\nTemporary State: " + employeeTemporaryAddress.getState() +
                "\nTemporary Pin Code: " + employeeTemporaryAddress.getPinCode();
    }


}
