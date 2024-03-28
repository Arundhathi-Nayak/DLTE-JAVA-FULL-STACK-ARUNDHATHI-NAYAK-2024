package org.consolemethod;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reviewdatabase.Details.DatabaseRepositoryImplementation;
import org.reviewdatabase.Details.Employee;
import org.reviewdatabase.Details.EmployeeAddress;
import org.reviewdatabase.Details.EmployeebasicDetails;
import org.reviewdatabase.connection.ConnectionCreate;
import org.reviewdatabase.remote.InputEmployeeDetails;
import org.validation.Validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

   @Mock
   private DatabaseRepositoryImplementation databaseRepository;




    @Before
    public void setUp() throws SQLException {

        databaseRepository = new DatabaseRepositoryImplementation();
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        databaseRepository.connection = connection;
    }


    @Test
    public void testCreate()  {

        databaseRepository = mock(DatabaseRepositoryImplementation.class);

        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee(
                new EmployeebasicDetails("neha", "14", "neha@123", 7876543210L),
                new EmployeeAddress("shanthala", "2A", "karnataka", "udupi", 674102),
                new EmployeeAddress("akshaya", "3C", "karnataka", "pettengere", 923456)
        );

        employees.add(employee);

       doNothing().when(databaseRepository).create(employees);
        databaseRepository.create(employees);
        verify(databaseRepository).create(employees);

    }

    @Test
  public  void testDisplayBasedOnEmployeeId()  {

        databaseRepository = mock(DatabaseRepositoryImplementation.class);
        Employee employee = new Employee(
                new EmployeebasicDetails("Arundhathi", "14", "nayak@123", 9876543210L),
                new EmployeeAddress("shanthala", "2A", "karnataka", "udupi", 574102),
                new EmployeeAddress("akshaya", "3C", "karnataka", "pettengere", 123456)
        );


        when(databaseRepository.displayBasedOnEmployeeId("14")).thenReturn(employee);

        Employee actual = databaseRepository.displayBasedOnEmployeeId("14");

        verify(databaseRepository).displayBasedOnEmployeeId("14");
        assertEquals(employee, actual);


    }

    @Test
  public  void testDisplayBasedOnPinCode() {


        databaseRepository = mock(DatabaseRepositoryImplementation.class);
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee(
                new EmployeebasicDetails("Arundhathi", "12", "nayak@123", 9876543210L),
                new EmployeeAddress("shanthala", "2A", "karnataka", "udupi", 573102),
                new EmployeeAddress("akshaya", "3C", "karnataka", "pettengere", 123456)
        );

        employees.add(employee1);

        Employee employee2 = new Employee(
                new EmployeebasicDetails("Avinash", "1", "avinu@123", 3456789876L),
                new EmployeeAddress("shanthala", "6T", "karnataka", "udupi", 573102),
                new EmployeeAddress("kowdoor", "3W", "karnataka", "udupi", 321234)
        );
        employees.add(employee2);


        when(databaseRepository.displayBasedOnPinCode(573102)).thenReturn(employees);

        List<Employee> actual = databaseRepository.displayBasedOnPinCode(573102);

        verify(databaseRepository).displayBasedOnPinCode(573102);
        assertEquals(employees, actual);
    }

    @Test
  public  void testRead() {

        List<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee(
                new EmployeebasicDetails("Avinash", "11", "avinu@123", 3456789876L),
                new EmployeeAddress("shanthala", "6T", "karnataka", "udupi", 543216),
                new EmployeeAddress("kowdoor", "3W", "karnataka", "udupi", 321234)
        );

        employees.add(employee1);
        Employee employee2 = new Employee(
                new EmployeebasicDetails("Arundhathi", "13", "nayak@123", 9876543210L),
                new EmployeeAddress("shanthala", "2A", "karnataka", "udupi", 574102),
                new EmployeeAddress("akshaya", "3C", "karnataka", "pettengere", 123456)
        );

        employees.add(employee2);

        databaseRepository = mock(DatabaseRepositoryImplementation.class);
        when(databaseRepository.read()).thenReturn(employees);

        List<Employee> actualEmployees = databaseRepository.read();

        // Assertions
        assertEquals(employees.size(), actualEmployees.size());
        for (int i = 0; i < employees.size(); i++) {
            assertEquals(employees.get(i), actualEmployees.get(i));
        }
    }

   }



