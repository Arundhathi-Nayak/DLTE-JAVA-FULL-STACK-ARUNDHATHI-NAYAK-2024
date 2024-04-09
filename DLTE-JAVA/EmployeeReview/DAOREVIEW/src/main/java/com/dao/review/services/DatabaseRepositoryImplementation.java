package com.dao.review.services;

import com.dao.review.entity.Employee;
import com.dao.review.entity.EmployeeAddress;
import com.dao.review.entity.EmployeeBasicDetails;
import com.dao.review.exceptions.EmployeeExistException;
import com.dao.review.exceptions.EmployeeNotFoundException;
import com.dao.review.remote.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DatabaseRepositoryImplementation implements InputEmployeeDetails {
    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(DatabaseRepositoryImplementation.class);

    ResourceBundle resourceBundle= ResourceBundle.getBundle("accounts");


    @Override
    public Employee create(Employee employee) throws EmployeeExistException {

        String employeeID = employee.getEmployeeBasicDetails().getEmployeeId();

        try {
            String employeesQuery = "INSERT INTO Employee (EmployeeId, EmployeeName, emailId, phoneNumber) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(employeesQuery, employeeID, employee.getEmployeeBasicDetails().getEmployeeName(),
                    employee.getEmployeeBasicDetails().getEmailId(), employee.getEmployeeBasicDetails().getPhoneNumber());

            // Inserting into Address table
            String insertTemporaryAddressQuery = "INSERT INTO EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) VALUES (address_seq.nextval,?,?,?,?,?,?,1)";
            jdbcTemplate.update(insertTemporaryAddressQuery, employeeID, employee.getEmployeeTemporaryAddress().getAddress(),
                    employee.getEmployeeTemporaryAddress().getHouseNumber(), employee.getEmployeeTemporaryAddress().getCity(),
                    employee.getEmployeeTemporaryAddress().getState(), employee.getEmployeeTemporaryAddress().getPinCode());

            String insertPermanentAddressQuery = "INSERT INTO EmployeeAddress(ADDRESSID,EMPLOYEEID,HOUSENAME,STREETNAME,CITYNAME,STATENAME,PINCODE,ISTEMPORARY) VALUES (address_seq.nextval,?,?,?,?,?,?,0)";
            jdbcTemplate.update(insertPermanentAddressQuery, employeeID, employee.getEmployeePermanentAddress().getAddress(),
                    employee.getEmployeePermanentAddress().getHouseNumber(), employee.getEmployeePermanentAddress().getCity(),
                    employee.getEmployeePermanentAddress().getState(), employee.getEmployeePermanentAddress().getPinCode());

            System.out.println(resourceBundle.getString("employee.add") + employeeID + " " + resourceBundle.getString("employeeAdd.success"));
            logger.info(resourceBundle.getString("employee.add") + employeeID + " " + resourceBundle.getString("employeeAdd.success"));
            return employee;
        } catch (DuplicateKeyException e) {
            logger.warn(resourceBundle.getString("Fail.insert") + " " + employeeID + " " + resourceBundle.getString("employee.exists"));
            throw new EmployeeExistException(resourceBundle.getString("employee.e`xist"));
        }
    }

    @Override
    public Employee displayBasedOnEmployeeId(String employeeID) {
        try {
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    "WHERE emp.EMPLOYEEID=?";
            return jdbcTemplate.queryForObject(query, new Object[]{employeeID}, new EmployeeRowMapper());
        } catch (EmptyResultDataAccessException e) {
            logger.warn(resourceBundle.getString("no.employee") + employeeID);
            throw new EmployeeNotFoundException(resourceBundle.getString("no.employee") + employeeID);
        }
    }

    @Override
    public List<Employee> displayBasedOnPinCode(int pinCode) {
        try {
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0\n" +
                    "WHERE ta.PINCODE=? or pa.PINCODE=?";
            List<Employee> employees = jdbcTemplate.query(query, new Object[]{pinCode, pinCode}, new EmployeeRowMapper());
            if (employees.isEmpty()) {
                logger.warn(resourceBundle.getString("no.pincode") + pinCode);
                throw new EmployeeNotFoundException(resourceBundle.getString("no.pincode") + pinCode);
            }
            return employees;
        } catch (EmptyResultDataAccessException e) {
            logger.warn(resourceBundle.getString("no.pincode") + pinCode);
            throw new EmployeeNotFoundException(resourceBundle.getString("no.pincode") + pinCode);
        } catch (DataAccessException e) {
            logger.warn(resourceBundle.getString("no.pincode") + pinCode);
            throw new EmployeeNotFoundException(resourceBundle.getString("no.pincode") + pinCode);
        }
    }

    @Override
    public List<Employee> read() {
        try {
            String query = "SELECT emp.EmployeeName,emp.EmployeeId,emp.emailId,emp.phoneNumber,ta.HOUSENAME,ta.STREETNAME,ta.CITYNAME,ta.STATENAME,ta.PINCODE , pa.HOUSENAME, pa.STREETNAME,pa.CITYNAME, pa.STATENAME,pa.PINCODE  FROM employee emp\n" +
                    "INNER JOIN EmployeeAddress ta ON emp.EMPLOYEEID = ta.EMPLOYEEID AND ta.ISTEMPORARY = 1\n" +
                    "INNER JOIN EmployeeAddress pa ON emp.EMPLOYEEID = pa.EMPLOYEEID AND pa.ISTEMPORARY = 0";
            return jdbcTemplate.query(query, new EmployeeRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException(resourceBundle.getString("fail.fetch"), e);
        }
    }

    public class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            EmployeeBasicDetails basicDetails = new EmployeeBasicDetails(
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

            return new Employee(basicDetails, permanentAddr, temporaryAddr);
        }
    }
}
