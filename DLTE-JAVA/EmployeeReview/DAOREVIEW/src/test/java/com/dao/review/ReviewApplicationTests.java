package com.dao.review;

import com.dao.review.entity.Employee;
import com.dao.review.entity.EmployeeAddress;
import com.dao.review.entity.EmployeeBasicDetails;
import com.dao.review.exceptions.EmployeeNotFoundException;
import com.dao.review.services.DatabaseRepositoryImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReviewApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ResourceBundle resourceBundle;

    @InjectMocks
    private DatabaseRepositoryImplementation employeeService;

    @Test
    public void testDisplayBasedOnEmployeeId() {
        String employeeId = "123";

        // Creating the expected employee object
        Employee expectedEmployee = new Employee();
        EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        employeeBasicDetails.setEmployeeName("Arundhathi");
        employeeBasicDetails.setEmployeeId("123");
        employeeBasicDetails.setEmailId("arundhathi@gmail.com");
        employeeBasicDetails.setPhoneNumber(9234567890L);

        EmployeeAddress permanentAddress = new EmployeeAddress();
        permanentAddress.setHouseNumber("123");
        permanentAddress.setState("Karnataka");
        permanentAddress.setCity("karkala");
        permanentAddress.setPinCode(900013);

        EmployeeAddress temporaryAddress = new EmployeeAddress();
        temporaryAddress.setHouseNumber("456");
        temporaryAddress.setState("karnataka");
        temporaryAddress.setCity("Bangalore");
        temporaryAddress.setPinCode(345678);

        expectedEmployee.setEmployeeBasicDetails(employeeBasicDetails);
        expectedEmployee.setEmployeePermanentAddress(permanentAddress);
        expectedEmployee.setEmployeeTemporaryAddress(temporaryAddress);

        // Mocking the queryForObject method of JdbcTemplate to return the expectedEmployee
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(DatabaseRepositoryImplementation.EmployeeRowMapper.class)))
                .thenReturn(expectedEmployee);

        // Calling the method under test
        Employee actualEmployee = employeeService.displayBasedOnEmployeeId(employeeId);

        // Verifying that the queryForObject method was called with the correct parameters
        verify(jdbcTemplate).queryForObject(anyString(), eq(new Object[]{employeeId}), any(DatabaseRepositoryImplementation.EmployeeRowMapper.class));

        // Verifying that the returned employee matches the expected employee
        assertEquals(expectedEmployee, actualEmployee);
    }
    @Test
    public void testDisplayBasedOnEmployeeIdNotFound() {
        String employeeId = "123";

        // Mocking the queryForObject method of JdbcTemplate to throw an EmptyResultDataAccessException
        when(jdbcTemplate.queryForObject(anyString(), any(Object[].class), any(DatabaseRepositoryImplementation.EmployeeRowMapper.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        // Verifying that EmployeeNotFoundException is thrown
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.displayBasedOnEmployeeId(employeeId));
    }

}
