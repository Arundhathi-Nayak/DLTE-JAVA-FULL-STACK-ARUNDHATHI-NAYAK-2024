package com.dao.review.remote;

import com.dao.review.entity.Employee;
import com.dao.review.exceptions.EmployeeExistException;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface InputEmployeeDetails {
    Employee create( Employee employee) throws EmployeeExistException;
    Employee displayBasedOnEmployeeId(String employeeID);
    List<Employee> displayBasedOnPinCode(int pinCode);
    List<Employee> read();

}
