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
    public List<Employee> create(List<Employee> list) {
        List<Employee> createdEmployees = new ArrayList<>();
        ValidationData validation = new ValidationData();
        validation.Validationofdata(list);
        for (Employee employee : list) {
            String employeeID = employee.getEmployeebasicDetails().getEmployeeId();
            try {
                connection = ConnectionCreate.createConnection();
                String employees = "INSERT INTO Employee (EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(employees);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeebasicDetails().getEmployeeName());
                preparedStatement.setString(3, employee.getEmployeebasicDetails().getEmailId());
                preparedStatement.setLong(4, employee.getEmployeebasicDetails().getPhoneNumber());
                preparedStatement.executeUpdate();

                // Inserting into Address table
                String insertTemporaryAddress = "insert into EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,1)";
                preparedStatement = connection.prepareStatement(insertTemporaryAddress);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeeTemporaryAddress().getAddress());
                preparedStatement.setString(3, employee.getEmployeeTemporaryAddress().getHouseNumber());
                preparedStatement.setString(4, employee.getEmployeeTemporaryAddress().getCity());
                preparedStatement.setString(5, employee.getEmployeeTemporaryAddress().getState());
                preparedStatement.setInt(6, employee.getEmployeeTemporaryAddress().getPinCode());
                preparedStatement.executeUpdate();

                String insertPermanentAddress = "insert into EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) values (address_seq.nextval,?,?,?,?,?,?,0)";
                preparedStatement = connection.prepareStatement(insertPermanentAddress);
                preparedStatement.setString(1, employeeID);
                preparedStatement.setString(2, employee.getEmployeePermanentAddress().getAddress());
                preparedStatement.setString(3, employee.getEmployeePermanentAddress().getHouseNumber());
                preparedStatement.setString(4, employee.getEmployeePermanentAddress().getCity());
                preparedStatement.setString(5, employee.getEmployeePermanentAddress().getState());
                preparedStatement.setInt(6, employee.getEmployeePermanentAddress().getPinCode());
                preparedStatement.executeUpdate();

                createdEmployees.add(employee); // Add the created employee to the list of created employees
                System.out.println(resourceBundle1.getString("employee.add") + employeeID +" "+resourceBundle1.getString("employeeAdd.success"));
                logger.info(resourceBundle1.getString("employee.add")+ employeeID +" "+resourceBundle1.getString("employeeAdd.success"));
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    System.out.println(resourceBundle1.getString("Fail.insert") + " " + employeeID + " " + resourceBundle1.getString("employee.exists"));
                    logger.error(resourceBundle1.getString("Fail.insert") + " " + employeeID + " " + resourceBundle1.getString("employee.exists"));
                } else {
                    e.printStackTrace();
                }
            } finally {
                closeConnections();
            }
        }
        return createdEmployees;

    }


    @Override
    public Employee displayBasedOnEmployeeId(String employeeId) {
        Employee employee =new Employee();
        try {
            connection=ConnectionCreate.createConnection();
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "                             INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "                             INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    "                             WHERE emp.EMPLOYEEID=?";
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
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getInt(14)
                );

                EmployeeAddress temporaryAddr = new EmployeeAddress(
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
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
                     "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                             "                             INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                             "                             INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                             "                             WHERE ta.PINCODE=? or pa.PINCODE=?");
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
                            resultSet.getString(10),
                            resultSet.getString(11),
                            resultSet.getString(12),
                            resultSet.getString(13),
                            resultSet.getInt(14)
                    );

                    EmployeeAddress temporaryAddr = new EmployeeAddress(
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getInt(9)
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
            String findAll = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "                             INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "                             INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0";
            preparedStatement = connection.prepareStatement(findAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = null;
                EmployeeAddress permanentAddress = new EmployeeAddress(
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getString(12),
                        resultSet.getString(13),
                        resultSet.getInt(14)
                );
                EmployeeAddress temporaryAddress = new EmployeeAddress(
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getInt(9)
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
