package org.reviewdatabase.remote;

import org.reviewdatabase.Details.Employee;
import org.reviewdatabase.exception.EmployeeExistException;
import org.reviewdatabase.exception.ValidationException;

import java.sql.SQLException;
import java.util.List;

public interface InputEmployeeDetails {
    List<Employee> create(List<Employee> employee) throws EmployeeExistException, SQLException, ValidationException;
    Employee displayBasedOnEmployeeId(String employeeID);
    List<Employee> displayBasedOnPinCode(int pinCode);
    List<Employee> read();
    void closeConnections();
}
