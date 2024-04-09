package com.spring.webservice.configuration;

import com.dao.review.exceptions.EmployeeExistException;
import com.dao.review.exceptions.EmployeeNotFoundException;
import com.dao.review.remote.InputEmployeeDetails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.employee.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

//@PreAuthorize("hasAnyAuthority('admin')")
//@PayloadRoot(namespace = url,localPart = "newTransaction")
//@ResponsePayload
//public NewTransactionResponse addNewTransaction(@RequestPayload NewTransactionRequest newTransactionRequest){
//        NewTransactionResponse newTransactionResponse=new NewTransactionResponse();
//        ServiceStatus serviceStatus=new ServiceStatus();
//
//        services.transaction.Transaction actualTransaction=newTransactionRequest.getTransaction();
//        Date date=newTransactionRequest.getTransaction().getTransactionDate().toGregorianCalendar().getTime();
//        Transaction transaction=new Transaction();
//        transaction.setTransactionDate(date);/// yyyy-mm-dd
//        BeanUtils.copyProperties(actualTransaction,transaction);
//
//        transaction=transactionServices.newTransaction(transaction);
//
//        if(transaction!=null){
//        serviceStatus.setStatus("SUCCESS");
//        serviceStatus.setMessage(transaction.getTransactionId()+"is inserted");
//        }else{
//        serviceStatus.setStatus("FAILURE");
//        serviceStatus.setMessage(transaction.getTransactionId()+"not inserted");
//        }
//        newTransactionResponse.setServiceStatus(serviceStatus);
//        BeanUtils.copyProperties(transaction,actualTransaction);
//        newTransactionResponse.setTransaction(actualTransaction);
//
//        return newTransactionResponse;
//        }
@Endpoint
@ComponentScan("com.dao.review.remote")
public class SoapPhase {

    private final String url="http://employee.services";

    @Autowired
    private InputEmployeeDetails inputEmployeeDetails;

    @PayloadRoot(namespace = url,localPart = "createEmployeeRequest")
    @ResponsePayload
    public CreateEmployeeResponse createEmployee(@RequestPayload CreateEmployeeRequest request) throws EmployeeExistException {
        CreateEmployeeResponse employeeResponse = new CreateEmployeeResponse();

        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = request.getEmployee();
        try {
            com.dao.review.entity.Employee employee = new com.dao.review.entity.Employee();
            BeanUtils.copyProperties(actualEmployee, employee);
            employee = inputEmployeeDetails.create(employee);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            employeeResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage("Employee inserted successfully");
        } catch (EmployeeExistException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("Employee already exists");
        }
        employeeResponse.setServiceStatus(serviceStatus);
        return employeeResponse;
    }
    @PayloadRoot(namespace = url,localPart = "filterByIdRequest")
    @ResponsePayload
   public FilterByIdResponse filterByIdRequest(@RequestPayload FilterByIdRequest findAllPayeeRequest) {
        FilterByIdResponse filterByIdResponse = new FilterByIdResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        Employee actualEmployee = new Employee();
        try {
            com.dao.review.entity.Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(findAllPayeeRequest.getEmployeeId());
            BeanUtils.copyProperties(employee, actualEmployee);
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            filterByIdResponse.setEmployee(actualEmployee);
            serviceStatus.setMessage("Employee found successfully");
        } catch (EmployeeNotFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NOT_FOUND);
            serviceStatus.setMessage("Employee not found");
        }
        filterByIdResponse.setServiceStatus(serviceStatus);
        return filterByIdResponse;
    }

    @PayloadRoot(namespace = url,localPart = "findAllEmployeeRequest")
    @ResponsePayload
    public FindAllEmployeeResponse listAllPayee(@RequestPayload FindAllEmployeeResponse findAllPayeeRequest) {
        FindAllEmployeeResponse findAllPayeeResponse=new FindAllEmployeeResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Employee> employees =new ArrayList<>();
        try {
            List<com.dao.review.entity.Employee> daoEmployee = inputEmployeeDetails.read();
            daoEmployee.forEach(each -> {
                Employee currentEmployee = new Employee();
                BeanUtils.copyProperties(each, currentEmployee);
                employees.add(currentEmployee);
            });
           // serviceStatus.setStatus(resourceBundle.getString("success.payee") + " " + HttpStatus.OK.value());
            serviceStatus.setStatus( HttpServletResponse.SC_OK);
            serviceStatus.setMessage("Employee displayed successfully");
        }catch (EmployeeNotFoundException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            serviceStatus.setMessage("Employee not found");
        }
        findAllPayeeResponse.setServiceStatus(serviceStatus);
        findAllPayeeResponse.getEmployee().addAll(employees);
        return findAllPayeeResponse;
    }
}
