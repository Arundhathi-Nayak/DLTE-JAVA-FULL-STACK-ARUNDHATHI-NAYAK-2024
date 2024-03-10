package org.fileRepository;

import org.example.Employee;
import org.example.InputEmployeeDetails;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileRepositoryImplementation implements InputEmployeeDetails {
    List<Employee> employeeList=new ArrayList<>();
    File filePath=new File("Output.txt");

    public void writeIntoFile() throws IOException, ClassNotFoundException {
//        if(filePath.exists()) {
//            FileInputStream fileInputStream = new FileInputStream(file);
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            employeeList= (List<Employee>) objectInputStream.readObject();//re read file
//            employeeList.addAll(employees);//add new data into existing file
//        }else{
//            employeeList=employees;
//        }
        FileOutputStream fileOutputStream=new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(employeeList);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream=new FileInputStream(filePath);
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        employeeList=(List<Employee>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
    }
    @Override
    public void create(List<Employee> list) {
        try {
            if(filePath.exists()){ readFromFile();}
            for(Employee employee:list) {
                boolean employeeExists = employeeList.stream().anyMatch(alreadyExist -> alreadyExist.getEmployeeId() == employee.getEmployeeId());
                if (employeeExists) System.out.println("Employee already exists");
                else {
                    employeeList.addAll(list);
                }
            }
            writeIntoFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee displayBasedOnEmployeeId(int id) {
        try {
            readFromFile();
            return employeeList.stream().filter(employee1 -> employee1.getEmployeeId().equals(id)).findFirst().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee displayBasedOnPinCode(int pinCode) {
        try {
            readFromFile();
            return employeeList.stream().filter(employee1 -> employee1.getAddress().getTemporaryPinCode()==pinCode).findAny().orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> read() {
        try {
            readFromFile();
            return employeeList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
