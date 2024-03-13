package org.example;

import oracle.jdbc.driver.OracleDriver;
import org.example.Details.Employee;
import org.example.Details.InputEmployeeDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {

    Connection connection;
    ResourceBundle resourceBundle= ResourceBundle.getBundle("Database");
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public DatabaseRepositoryImplementation() {
        try{
            Driver driver=new OracleDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(resourceBundle.getString("db.url"),resourceBundle.getString("db.user"),resourceBundle.getString("db.pass"));
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean isEstablished(){
        return connection!=null;
    }
    @Override
    public void create(List<Employee> list) {

        for(Employee employee:list){

            String employeeID=employee.getEmployeebasicDetails().getEmployeeId();
            try{
                String employees = "INSERT INTO Employee (id, name) VALUES (?, ?)";
                preparedStatement=connection.prepareStatement(employees);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeebasicDetails().getEmployeeName());
                int resultBasic=preparedStatement.executeUpdate();

                String permanentaddress = "INSERT INTO EmployeeAddress (employeeId,permanentAddress, permanentHouseNumber,permanentCity, permanentState,permanentPinCode) " +
               "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(permanentaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeePermanentAddress().getAddress());
                preparedStatement.setString(3,employee.getEmployeePermanentAddress().getHouseNumber());
                preparedStatement.setString(4,employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeePermanentAddress().getPinCode());
                int resultPermanent=preparedStatement.executeUpdate();

                String temporaryaddress = "INSERT INTO EmployeeTemporaryAddress(employeeId,temporaryAddress, temporaryHouseNumber,temporaryCity, temporaryState,temporaryPinCode) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStatement=connection.prepareStatement(temporaryaddress);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeeTemporaryAddress().getAddress());
                preparedStatement.setString(3,employee.getEmployeeTemporaryAddress().getHouseNumber());
                preparedStatement.setString(4,employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(5,employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(6,employee.getEmployeeTemporaryAddress().getPinCode());
                int resultTemporary=preparedStatement.executeUpdate();

                String information = "INSERT INTO EmployeeInformation (employeeId, email, phoneNumber) VALUES (?, ?, ?)";
                preparedStatement=connection.prepareStatement(information);
                preparedStatement.setString(1,employeeID);
                preparedStatement.setString(2,employee.getEmployeebasicDetails().getEmailId());
                preparedStatement.setLong(3,employee.getEmployeebasicDetails().getPhoneNumber() );
                int resultInformation=preparedStatement.executeUpdate();
//                connection.commit();

                if(resultBasic!=0){
                    System.out.println("Basic details inserted");
                }else{
                    System.out.println("failed");
                }
               if(resultTemporary!=0) System.out.println("Temporary address inserted");
               if(resultPermanent!=0) System.out.println("Permanent address inserted");
                if(resultInformation!=0) System.out.println("Additional information added");


            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public Employee displayBasedOnEmployeeId(String s) {
        return null;
    }

    @Override
    public Employee displayBasedOnPinCode(int i) {
        return null;
    }

    @Override
    public List<Employee> read() {
//        List<Employee> employees = new ArrayList<>();
//        try {
//            String findAll="SELECT * FROM employee emp INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id=empTAdd.employeeId INNER JOIN EmployeeInformation empInfo ON emp.id=empInfo.employeeId ";
//            preparedStatement=connection.prepareStatement(findAll);
//            resultSet=preparedStatement.executeQuery();
//            while (resultSet.next()){
//                Employee employee1 = null;
//                EmployeeAddress employeeAddress = null;
//                EmployeeInformation employeeInformation = null;
//                employee1.setEmployeeId(resultSet.getString(1));
//                employee1.setEmployeeName(resultSet.getString(2));
//
//                employeeAddress.setPermanentAddress(resultSet.getString(4));
//                employeeAddress.setPermanentHouseNumber(resultSet.getString(5));
//                employeeAddress.setPermanentCity(resultSet.getString(6));
//                employeeAddress.setPermanentState(resultSet.getString(7));
//                employeeAddress.setPermanentPinCode(resultSet.getInt(8));
//                employeeAddress.setTemporaryAddress(resultSet.getString(10));
//                employeeAddress.setTemporaryHouseNumber(resultSet.getString(11));
//                employeeAddress.setTemporaryCity(resultSet.getString(12));
//                employeeAddress.setTemporaryState(resultSet.getString(13));
//                employeeAddress.setTemporaryPinCode(resultSet.getInt(14));
//
//
//                employeeInformation.setEmailId(resultSet.getString(16));
//                employeeInformation.setPhoneNumber(resultSet.getLong(17));
//
//                employees.add (new Employee(employee1.getEmployeeName(),employee1.getEmployeeId(),employeeAddress,employeeInformation));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employees;
        return null;
    }
}
