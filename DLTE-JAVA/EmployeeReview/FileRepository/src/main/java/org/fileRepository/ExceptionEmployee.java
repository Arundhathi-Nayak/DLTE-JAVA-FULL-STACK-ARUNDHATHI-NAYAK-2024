package org.fileRepository;

import org.example.Employee;

import java.util.ResourceBundle;

public class ExceptionEmployee extends RuntimeException {
    public ExceptionEmployee(){
        super("Employee already exists");
    }
    public ExceptionEmployee(String additionalInfo){
        super("Employee already exists");
    }
}
