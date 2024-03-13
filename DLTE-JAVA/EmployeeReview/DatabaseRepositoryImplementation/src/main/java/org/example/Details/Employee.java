package org.example.Details;

public class Employee {
    EmployeebasicDetails employeebasicDetails;
    EmployeeAddress employeePermanentAddress;
    EmployeeAddress employeeTemporaryAddress;

    @Override
    public String toString() {
        return "Employee{" +
                "employeebasicDetails=" + employeebasicDetails +
                ", employeePermanentAddress=" + employeePermanentAddress +
                ", employeeTemporaryAddress=" + employeeTemporaryAddress +
                '}';
    }

    public EmployeebasicDetails getEmployeebasicDetails() {
        return employeebasicDetails;
    }

    public void setEmployeebasicDetails(EmployeebasicDetails employeebasicDetails) {
        this.employeebasicDetails = employeebasicDetails;
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

    public void setEmployeeTemporaryAddress(EmployeeAddress employeeTemporaryAddress) {
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }

    public Employee(EmployeebasicDetails employeebasicDetails, EmployeeAddress employeePermanentAddress, EmployeeAddress employeeTemporaryAddress) {
        this.employeebasicDetails = employeebasicDetails;
        this.employeePermanentAddress = employeePermanentAddress;
        this.employeeTemporaryAddress = employeeTemporaryAddress;
    }
}
