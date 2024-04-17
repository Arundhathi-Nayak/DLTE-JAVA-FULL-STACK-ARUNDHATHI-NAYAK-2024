package com.spring.webservice.configuration;

import com.dao.review.exceptions.EmployeeExistException;
import com.dao.review.exceptions.EmployeeNotFoundException;
import com.dao.review.remote.InputEmployeeDetails;
import com.dao.review.services.DatabaseRepositoryImplementation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

//http://localhost:8082/employeerepo/employee.wsdl
@ComponentScan( "com.dao.review")
@Endpoint
public class SoapPhase {
    Logger logger = LoggerFactory.getLogger(SoapPhase.class);

    ResourceBundle resourceBundle= ResourceBundle.getBundle("accounts");

    private final String url="http://employee.services";

    @Autowired
    private InputEmployeeDetails inputEmployeeDetails;

    @PayloadRoot(namespace = url,localPart = "createEmployeeRequest")
    @ResponsePayload
    public CreateEmployeeResponse createEmployee(@Valid @RequestPayload CreateEmployeeRequest request) throws EmployeeExistException {
        CreateEmployeeResponse employeeResponse = new CreateEmployeeResponse();

        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = request.getEmployee();
        try {
            com.dao.review.entity.Employee employee = new com.dao.review.entity.Employee();
            com.dao.review.entity.EmployeeBasicDetails daoBasic=new com.dao.review.entity.EmployeeBasicDetails();
            com.dao.review.entity.EmployeeAddress daoTempAddress= new com.dao.review.entity.EmployeeAddress();
            com.dao.review.entity.EmployeeAddress daoPermAddress=new com.dao.review.entity.EmployeeAddress();
            BeanUtils.copyProperties(actualEmployee.getEmployeeBasicDetails(), daoBasic);
            BeanUtils.copyProperties(actualEmployee.getEmployeePermanentAddress(), daoPermAddress);
            BeanUtils.copyProperties(actualEmployee.getEmployeeTemporaryAddress(), daoTempAddress);
            employee.setEmployeeBasicDetails(daoBasic);
            employee.setEmployeePermanentAddress(daoPermAddress);
            employee.setEmployeeTemporaryAddress(daoTempAddress);
            employee = inputEmployeeDetails.create(employee);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            employeeResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage(resourceBundle.getString("web.success"));
            logger.info(resourceBundle.getString("web.success"));
        } catch (EmployeeExistException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("webEmployee.exists"));
            logger.warn(resourceBundle.getString("webEmployee.exists"));
        }
        employeeResponse.setServiceStatus(serviceStatus);
        return employeeResponse;
    }
    @PayloadRoot(namespace = url,localPart = "filterByIdRequest")
    @ResponsePayload
   public FilterByIdResponse filterByIdRequest(@RequestPayload FilterByIdRequest findAllEmployeeRequest) {
        FilterByIdResponse filterByIdResponse = new FilterByIdResponse();
        ServiceStatus status = new ServiceStatus();
        Employee actualEmployee = new Employee();
        try {
            com.dao.review.entity.Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(findAllEmployeeRequest.getEmployeeId());
            EmployeeBasicDetails basic = new EmployeeBasicDetails();
            EmployeeAddress tempAddress=new EmployeeAddress();
            EmployeeAddress permAddress=new EmployeeAddress();
            BeanUtils.copyProperties(employee.getEmployeeBasicDetails(), basic);
            BeanUtils.copyProperties(employee.getEmployeePermanentAddress(), permAddress);
            BeanUtils.copyProperties(employee.getEmployeeTemporaryAddress(), tempAddress);
            actualEmployee.setEmployeeBasicDetails(basic);
            actualEmployee.setEmployeePermanentAddress(permAddress);
            actualEmployee.setEmployeeTemporaryAddress(tempAddress);
            status.setStatus(HttpServletResponse.SC_OK);
            filterByIdResponse.setEmployee(actualEmployee);
            status.setMessage(resourceBundle.getString("webEmployee.id"));
            logger.info(resourceBundle.getString("webEmployee.id"));
        } catch (EmployeeNotFoundException e) {
            status.setStatus(HttpServletResponse.SC_NO_CONTENT);
            status.setMessage(resourceBundle.getString("employee.notFound"));
            logger.warn(resourceBundle.getString("employee.notFound"));
        }
        filterByIdResponse.setServiceStatus(status);
        return filterByIdResponse;
    }

    @PayloadRoot(namespace = url,localPart = "findAllEmployeeRequest")
    @ResponsePayload
    public FindAllEmployeeResponse listAllPayee(@RequestPayload FindAllEmployeeRequest findAllEmployeeRequest) {
        FindAllEmployeeResponse findAllEmployeeResponse=new FindAllEmployeeResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Employee> employees =new ArrayList<>();
        try {

            List<com.dao.review.entity.Employee> daoEmployee = inputEmployeeDetails.read();
            daoEmployee.forEach(each -> {
                Employee currentEmployee = new Employee();
                EmployeeBasicDetails basic = new EmployeeBasicDetails();
                EmployeeAddress tempAddress=new EmployeeAddress();
                EmployeeAddress permAddress=new EmployeeAddress();
                BeanUtils.copyProperties(each.getEmployeeBasicDetails(), basic);
                BeanUtils.copyProperties(each.getEmployeePermanentAddress(),permAddress);
                BeanUtils.copyProperties(each.getEmployeeTemporaryAddress(),tempAddress);
                currentEmployee.setEmployeeBasicDetails(basic);
                currentEmployee.setEmployeePermanentAddress(permAddress);
                currentEmployee.setEmployeeTemporaryAddress(tempAddress);
                employees.add(currentEmployee);
            });
           // serviceStatus.setStatus(resourceBundle.getString("success.payee") + " " + HttpStatus.OK.value());
            serviceStatus.setStatus( HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("employee.display"));
            logger.info(resourceBundle.getString("employee.display"));
        }catch (EmployeeNotFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("employee.notFound"));
            logger.warn(resourceBundle.getString("employee.notFound"));
        }
        findAllEmployeeResponse.setServiceStatus(serviceStatus);
        findAllEmployeeResponse.getEmployee().addAll(employees);
        return findAllEmployeeResponse;
    }
    @PayloadRoot(namespace = url,localPart = "getEmployeeByPinCodeRequest")
    @ResponsePayload
    public GetEmployeeByPinCodeResponse filterByPinCodeResponse(@RequestPayload GetEmployeeByPinCodeRequest filterAllEmployeeRequest) {
        GetEmployeeByPinCodeResponse getEmployeeByPinCodeResponse = new GetEmployeeByPinCodeResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<Employee> employees = new ArrayList<>();
        try {
            List<com.dao.review.entity.Employee> daoEmployees = inputEmployeeDetails.displayBasedOnPinCode(filterAllEmployeeRequest.getPinCode());
            daoEmployees.forEach(each -> {
                Employee currentEmployee = new Employee();
                EmployeeBasicDetails basic = new EmployeeBasicDetails();
                EmployeeAddress tempAddress=new EmployeeAddress();
                EmployeeAddress permAddress=new EmployeeAddress();
                BeanUtils.copyProperties(each.getEmployeeBasicDetails(), basic);
                BeanUtils.copyProperties(each.getEmployeePermanentAddress(),permAddress);
                BeanUtils.copyProperties(each.getEmployeeTemporaryAddress(),tempAddress);
                currentEmployee.setEmployeeBasicDetails(basic);
                currentEmployee.setEmployeePermanentAddress(permAddress);
                currentEmployee.setEmployeeTemporaryAddress(tempAddress);
                employees.add(currentEmployee);
            });
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("employee.pinCode"));
            logger.info(resourceBundle.getString("employee.pinCode"));
        } catch (EmployeeNotFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage(resourceBundle.getString("employee.notFound"));
            logger.warn(resourceBundle.getString("employee.notFound"));
        }
        getEmployeeByPinCodeResponse.setServiceStatus(serviceStatus);
        getEmployeeByPinCodeResponse.getEmployee().addAll(employees);
        return getEmployeeByPinCodeResponse;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException ve){
        Map<String, String> errors = new HashMap<>();
        ve.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
