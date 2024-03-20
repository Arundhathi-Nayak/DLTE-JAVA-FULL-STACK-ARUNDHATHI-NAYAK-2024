package org.reviewdatabase.Details;

import exception.EmployeeNotFoundException;
import oracle.jdbc.driver.OracleDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.validation.Validation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {

    Connection connection;

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Validation validation=new Validation();
    Logger logger= LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);
    ResourceBundle resourceBundle1= ResourceBundle.getBundle("application");
    public DatabaseRepositoryImplementation() {
            connection=ConnectionCreate.createConnection();
    }
    @Override
    public void create(List<Employee> list) {

        for(Employee employee:list){

            if(!validation.isValidPhoneNumber(employee.getEmployeebasicDetails().getPhoneNumber())){
                logger.error("Phone number is corrupted!");
                throw new EmployeeNotFoundException(resourceBundle1.getString("invalid.Phone"));
            }

            if(!validation.isValidEmail(employee.getEmployeebasicDetails().getEmailId())){
                logger.error("Email ID is corrupted!");
                throw new EmployeeNotFoundException(resourceBundle1.getString("invalid.email"));
            }
            if(!validation.isValidPin(employee.employeeTemporaryAddress.getPinCode())){
                logger.error("Temporary pincode is corrupted!");
                throw  new EmployeeNotFoundException(resourceBundle1.getString("invalid.Pin"));
            }
            if(!validation.isValidPin(employee.employeeTemporaryAddress.getPinCode())){
                logger.error("Permanent pincode is corrupted!");
                throw  new EmployeeNotFoundException(resourceBundle1.getString("invalid.Pin"));
            }


            String employeeID=employee.getEmployeebasicDetails().getEmployeeId();
            try{
                connection=ConnectionCreate.createConnection();
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
                System.out.println(resourceBundle1.getString("employee.add") + employeeID +" "+resourceBundle1.getString("employeeAdd.success"));
                logger.info(resourceBundle1.getString("employee.add")+ employeeID +" "+resourceBundle1.getString("employeeAdd.success"));


            }catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println(resourceBundle1.getString("Fail.insert") +" "+ employeeID + " "+resourceBundle1.getString("employee.exists"));
                    logger.error(resourceBundle1.getString("Fail.insert") +" "+ employeeID + " "+resourceBundle1.getString("employee.exists"));
                } else {
                    e.printStackTrace();
                }
            }finally {
                closeConnections();
            }

    }

    }


    @Override
    public Employee displayBasedOnEmployeeId(String employeeId) {
        Employee employee =new Employee();
        try {
            connection=ConnectionCreate.createConnection();
            String query = "SELECT * FROM employee emp " +
                    "INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId " +
                    "INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId " +
                    "INNER JOIN EmployeeInformation empInfo ON emp.id = empInfo.employeeId " +
                    "WHERE emp.id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
            }else{
                throw new EmployeeNotFoundException(resourceBundle1.getString("no.employee") + employeeId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return employee;
    }

    @Override
    public Employee displayBasedOnPinCode(int pinCode) {
        Employee employee = new Employee();
        try {
            connection=ConnectionCreate.createConnection();
            String query = "SELECT * FROM employee emp " +
                    "INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId " +
                    "INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId " +
                    "INNER JOIN EmployeeInformation empInfo ON emp.id = empInfo.employeeId " +
                    "WHERE empPAdd.permanentPinCode = ? OR empTAdd.temporaryPinCode = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pinCode);
            preparedStatement.setInt(2, pinCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );

                EmployeeAddress permanentAddr = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );

                employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
            }else {
                throw new EmployeeNotFoundException(resourceBundle1.getString("no.pincode")+ pinCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return employee;
    }

    @Override
    public List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        try {
            connection=ConnectionCreate.createConnection();
            String findAll = "SELECT * FROM employee emp " +
                    "INNER JOIN EmployeeAddress empPAdd ON emp.id = empPAdd.employeeId " +
                    "INNER JOIN EmployeeTemporaryAddress empTAdd ON emp.id = empTAdd.employeeId " +
                    "INNER JOIN EmployeeInformation empInfo ON emp.id = empInfo.employeeId";
            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString("permanentAddress"),
                        resultSet.getString("permanentHouseNumber"),
                        resultSet.getString("permanentState"),
                        resultSet.getString("permanentCity"),
                        resultSet.getInt("permanentPinCode")
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString("temporaryAddress"),
                        resultSet.getString("temporaryHouseNumber"),
                        resultSet.getString("temporaryState"),
                        resultSet.getString("temporaryCity"),
                        resultSet.getInt("temporaryPinCode")
                );
                EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                        resultSet.getString("name"),
                        resultSet.getString("id"),
                        resultSet.getString("email"),
                        resultSet.getLong("phoneNumber")
                );
                employee = new Employee(basicDetails, permanentAddress, temporaryAddress);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnections();
        }
        return employees;
   }
    public void closeConnections() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
