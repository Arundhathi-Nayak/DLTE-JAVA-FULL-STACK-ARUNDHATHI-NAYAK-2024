package org.reviewdatabase.remote;

import org.reviewdatabase.Details.Employee;

import java.util.List;

public interface InputEmployeeDetails {
    void create(List<Employee> employee);
    Employee displayBasedOnEmployeeId(String employeeID);
    List<Employee> displayBasedOnPinCode(int pinCode);
    List<Employee> read();
    void closeConnections();
}
