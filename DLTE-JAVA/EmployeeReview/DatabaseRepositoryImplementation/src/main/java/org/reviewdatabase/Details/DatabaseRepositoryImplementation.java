package org.reviewdatabase.Details;

import org.reviewdatabase.exception.EmployeeNotFoundException;
import org.reviewdatabase.connection.ConnectionCreate;
import org.reviewdatabase.remote.InputEmployeeDetails;
import org.reviewdatabase.validation.ValidationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.validation.Validation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {

    public Connection connection;

    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Validation validation=new Validation();
    Logger logger= LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);
    ResourceBundle resourceBundle1= ResourceBundle.getBundle("application");
    public DatabaseRepositoryImplementation() {
            connection= ConnectionCreate.createConnection();
    }
    @Override
    public void create(List<Employee> list) {
        ValidationData validation=new ValidationData();
        validation.Validationofdata(list);
        for(Employee employee:list){

            String employeeID=employee.getEmployeebasicDetails().getEmployeeId();
            try{
                connection=ConnectionCreate.createConnection();
                String employees = "INSERT INTO Employee (EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(employees);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeebasicDetails().getEmployeeName());
                preparedStatement.setString(3, employee.getEmployeebasicDetails().getEmailId());
                preparedStatement.setLong(4, employee.getEmployeebasicDetails().getPhoneNumber());
                preparedStatement.executeUpdate();

                // Inserting into Address table
                String address = "INSERT INTO EmployeeAddress (EmployeeId, permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(address);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeePermanentAddress().getAddress());
                preparedStatement.setString(3, employee.getEmployeePermanentAddress().getHouseNumber());
                preparedStatement.setString(4, employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5, employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6, employee.getEmployeePermanentAddress().getPinCode());
                preparedStatement.setString(7, employee.getEmployeeTemporaryAddress().getAddress());
                preparedStatement.setString(8, employee.getEmployeeTemporaryAddress().getHouseNumber());
                preparedStatement.setString(9, employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(10, employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(11, employee.getEmployeeTemporaryAddress().getPinCode());
                preparedStatement.executeUpdate();

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
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,empAdd.permanentAddress,empAdd.permanentHouseNumber,empAdd.permanentState,empAdd.permanentCity,empAdd.permanentPinCode,empAdd.temporaryAddress,empAdd.temporaryHouseNumber,empAdd.temporaryState,empAdd.temporaryCity,empAdd.temporaryPinCode FROM Employee emp " +
                    "INNER JOIN EmployeeAddress empAdd ON emp.EmployeeId = empAdd.EmployeeId " +
                    "WHERE emp.EmployeeId = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employeeId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                        resultSet.getString("EmployeeName"),
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("emailId"),
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
    public List<Employee> displayBasedOnPinCode(int pinCode) {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = ConnectionCreate.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,empAdd.permanentAddress,empAdd.permanentHouseNumber,empAdd.permanentState,empAdd.permanentCity,empAdd.permanentPinCode,empAdd.temporaryAddress,empAdd.temporaryHouseNumber,empAdd.temporaryState,empAdd.temporaryCity,empAdd.temporaryPinCode FROM employee emp " +
                             "INNER JOIN EmployeeAddress empAdd ON emp.EmployeeId = empAdd.EmployeeId " +
                             "WHERE empAdd.permanentPinCode = ? OR empAdd.temporaryPinCode = ?");
        ) {
            preparedStatement.setInt(1, pinCode);
            preparedStatement.setInt(2, pinCode);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    EmployeebasicDetails basicDetails = new EmployeebasicDetails(
                            resultSet.getString("EmployeeName"),
                            resultSet.getString("EmployeeId"),
                            resultSet.getString("emailId"),
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

                    employees.add(new Employee(basicDetails, permanentAddr, temporaryAddr));
                }
            }
        } catch (SQLException e) {
            throw new EmployeeNotFoundException(resourceBundle1.getString("no.pincode")+ pinCode);
        }
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(resourceBundle1.getString("no.pincode")+ pinCode);
        }
        return employees;
    }

    @Override
    public List<Employee> read() {
        List<Employee> employees = new ArrayList<>();
        try {
            connection=ConnectionCreate.createConnection();
            String findAll = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,empAdd.permanentAddress,empAdd.permanentHouseNumber,empAdd.permanentState,empAdd.permanentCity,empAdd.permanentPinCode,empAdd.temporaryAddress,empAdd.temporaryHouseNumber,empAdd.temporaryState,empAdd.temporaryCity,empAdd.temporaryPinCode FROM employee emp " +
                    "INNER JOIN EmployeeAddress empAdd ON emp.EmployeeId = empAdd.EmployeeId ";
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
                        resultSet.getString("EmployeeName"),
                        resultSet.getString("EmployeeId"),
                        resultSet.getString("emailId"),
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
