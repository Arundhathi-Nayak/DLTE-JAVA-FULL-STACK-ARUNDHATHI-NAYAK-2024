package com.spring.webservice;

import com.dao.review.entity.Employee;
import com.dao.review.entity.EmployeeAddress;
import com.dao.review.entity.EmployeeBasicDetails;
import com.dao.review.exceptions.EmployeeNotFoundException;
import com.dao.review.remote.InputEmployeeDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.webservice.configuration.SoapPhase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import services.employee.FilterByIdRequest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class WebserviceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private InputEmployeeDetails inputEmployeeDetails;

    @InjectMocks
    private SoapPhase soapPhase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

 //   @Test
    public void testFilterByIdRequestEmployeeFound() throws Exception {
        // Mock the service method to return a valid employee
        // Creating the expected employee object
        Employee expectedEmployee = new Employee();
        EmployeeBasicDetails employeeBasicDetails=new EmployeeBasicDetails();
        employeeBasicDetails.setEmployeeName("John Doe");
        employeeBasicDetails.setEmployeeId("123");
        employeeBasicDetails.setEmailId("john.doe@example.com");
        employeeBasicDetails.setPhoneNumber(1234567890L);

        EmployeeAddress permanentAddress = new EmployeeAddress();
        permanentAddress.setHouseNumber("123");
        permanentAddress.setState("California");
        permanentAddress.setCity("Los Angeles");
        permanentAddress.setPinCode(90001);

        EmployeeAddress temporaryAddress = new EmployeeAddress();
        temporaryAddress.setHouseNumber("456");
        temporaryAddress.setState("California");
        temporaryAddress.setCity("San Francisco");
        temporaryAddress.setPinCode(94101);

        expectedEmployee.setEmployeeBasicDetails(employeeBasicDetails);
        expectedEmployee.setEmployeePermanentAddress(permanentAddress);
        expectedEmployee.setEmployeeTemporaryAddress(temporaryAddress);

        when(inputEmployeeDetails.displayBasedOnEmployeeId(anyString())).thenReturn(expectedEmployee);

        // Create a request payload
        FilterByIdRequest request = new FilterByIdRequest();
        request.setEmployeeId("123");

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders
                .post("/filterByIdRequest")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk());
              //  .andExpect(MockMvcResultMatchers.content().xml(/* set expected XML response here */));
    }

   // @Test
    public void testFilterByIdRequestEmployeeNotFound() throws Exception {
        // Mock the service method to throw EmployeeNotFoundException
        when(inputEmployeeDetails.displayBasedOnEmployeeId(anyString())).thenThrow(EmployeeNotFoundException.class);

        // Create a request payload
        FilterByIdRequest request = new FilterByIdRequest();
        request.setEmployeeId("123");

        // Perform the request and verify the response
        mockMvc.perform(MockMvcRequestBuilders
                .post("/http://localhost:8082/employeerepo/employee.wsdl")
                .content(asJsonString(request))
                .contentType(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    // Utility method to convert object to JSON string
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

