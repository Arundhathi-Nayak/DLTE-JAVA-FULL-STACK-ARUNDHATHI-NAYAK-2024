package org.fileRepository;

import org.example.Employee;
import org.example.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.TelnetOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
//import java.util.ResourceBundle;

public class FileRepositoryImplementation implements InputEmployeeDetails {
    List<Employee> employeeList=new ArrayList<>();
    File filePath=new File("Output.txt");
   ResourceBundle resourceBundle= ResourceBundle.getBundle("application");
    Logger logger= LoggerFactory.getLogger(FileRepositoryImplementation.class);
    public void writeIntoFile() throws IOException, ClassNotFoundException {
        List<Employee> employees=new ArrayList<>();
//        if(filePath.exists()) {
//            FileInputStream fileInputStream = new FileInputStream(filePath);
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            employeeList= (List<Employee>) objectInputStream.readObject();//re read file
//            employeeList.addAll(employees);//add new data into existing file
//        }else{
//            employeeList=employees;
//        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(employeeList);
            objectOutputStream.writeObject(employeeList);
        }

    }
    public void readFromFile() throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            employeeList = (List<Employee>) objectInputStream.readObject();
        }
    }
    @Override
    public void create(List<Employee> list) {
        try {
            if(filePath.exists()){ readFromFile();}

            for(Employee employee:list) {
                 boolean employeeExists = employeeList.stream().anyMatch(alreadyExist -> alreadyExist.getEmployeeId().equals(employee.getEmployeeId()));

                if (employeeExists) {
                    throw new  ExceptionEmployee("Employee Already exists");
                   // System.out.println("Employee Already exists");
                    //System.out.println(resourceBundle.getString("employee.exists"));
                }
                else {
                    employeeList.addAll(list);
                }
            }
            
            writeIntoFile();
        }
        catch (ExceptionEmployee exceptionEmployee){
          //  exceptionEmployee.printStackTrace();
            logger.trace("EmployeeAlreadyExistsException occurred", exceptionEmployee);
        }
        catch (IOException e) {

            logger.atTrace();
        } catch (ClassNotFoundException e) {

            logger.atTrace();
        }
    }

    @Override
    public Employee displayBasedOnEmployeeId(String id) {
        try {
            readFromFile();
            return employeeList.stream().filter(employee1 -> employee1.getEmployeeId().equals(id)).findFirst().orElse(null);
        } catch (IOException e) {

            logger.trace("IOException occurred", e);
        } catch (ClassNotFoundException e) {

            logger.trace("ClassNotFoundException occurred", e);
        }
        return null;
    }

    @Override
    public Employee displayBasedOnPinCode(int pinCode) {
        try {
            readFromFile();
            return employeeList.stream().filter(employee1 -> employee1.getAddress().getTemporaryPinCode()==pinCode).findAny().orElse(null);
        } catch (IOException e) {

            logger.trace("IOException occurred", e);
        } catch (ClassNotFoundException e) {

            logger.trace("ClassNotFoundException occurred", e);
        }
        return null;
    }

    @Override
    public List<Employee> read() {
        try {
            readFromFile();
            return employeeList;
        } catch (IOException e) {

            logger.trace("IOException occurred", e);
        } catch (ClassNotFoundException e) {

            logger.trace("ClassNotFoundException occurred", e);
        }
        return null;
    }

}
