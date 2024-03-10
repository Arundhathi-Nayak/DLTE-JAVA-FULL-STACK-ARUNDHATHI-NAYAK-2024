package org.example;

import java.util.List;


public interface InputEmployeeDetails {
        void create(List<Employee> employee);
        Employee displayBasedOnEmployeeId(int employeeID);
        Employee displayBasedOnPinCode(int pinCode);
        List<Employee> read();
}
