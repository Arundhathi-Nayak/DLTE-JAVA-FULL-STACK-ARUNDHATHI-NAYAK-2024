package com.soapclient.services.console;

import com.soapclient.services.configuration.WebServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;
import services.employee.*;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class EmployeeClient {
   private static Logger logger = LoggerFactory.getLogger(EmployeeClient.class);

   private static ResourceBundle resourceBundle= ResourceBundle.getBundle("accounts");
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(WebServiceConfiguration.class);
        context.refresh();

        WebServiceTemplate webServiceTemplate = context.getBean(WebServiceTemplate.class);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(resourceBundle.getString("menu"));
            System.out.println(resourceBundle.getString("emp.create"));
            System.out.println(resourceBundle.getString("emp.id"));
            System.out.println(resourceBundle.getString("emp.list"));
            System.out.println(resourceBundle.getString("emp.pin"));
            System.out.println(resourceBundle.getString("emp.exit"));
            System.out.print(resourceBundle.getString("enter.choice"));
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createEmployee(webServiceTemplate, scanner);
                    break;
                case 2:
                    filterByIdRequest(webServiceTemplate, scanner);
                    break;
                case 3:
                    findAllEmployeeRequest(webServiceTemplate);
                    break;
                case 4:
                    filterByPinCodeRequest(webServiceTemplate, scanner);
                    break;
                case 5:
                    System.out.println(resourceBundle.getString("exiting"));
                    context.close();
                    System.exit(0);
                default:
                    System.out.println(resourceBundle.getString("invalid.choice"));
            }
        }
    }

    private static void createEmployee(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        boolean addMoreEmployees = true;

        while (addMoreEmployees) {
            System.out.println(resourceBundle.getString("enter.employee"));
            System.out.print(resourceBundle.getString("enter.id"));
            String employeeId = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.name"));
            String employeeName = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.email"));
            String email = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.phoneNumber"));
            Long phoneNumber = scanner.nextLong();
            scanner.nextLine(); // Consume newline character

            System.out.println(resourceBundle.getString("enter.permanentDetails"));
            System.out.print(resourceBundle.getString("enter.address"));
            String permAddress = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.houseNumber"));
            String permHouseNumber = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.city"));
            String permCity = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.state"));
            String permState = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.pinCode"));
            Integer permPinCode = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            System.out.println(resourceBundle.getString("enter.temporaryAddress"));
            System.out.print(resourceBundle.getString("enter.address"));
            String tempAddress = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.houseNumber"));
            String tempHouseNumber = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.city"));
            String tempCity = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.state"));
            String tempState = scanner.nextLine();
            System.out.print(resourceBundle.getString("enter.pinCode"));
            Integer tempPinCode = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            EmployeeBasicDetails basicDetails = new EmployeeBasicDetails();
            basicDetails.setEmployeeId(employeeId);
            basicDetails.setEmployeeName(employeeName);
            basicDetails.setEmailId(email);
            basicDetails.setPhoneNumber(phoneNumber);

            EmployeeAddress permanentAddress = new EmployeeAddress();
            permanentAddress.setAddress(permAddress);
            permanentAddress.setHouseNumber(permHouseNumber);
            permanentAddress.setCity(permCity);
            permanentAddress.setState(permState);
            permanentAddress.setPinCode(permPinCode);

            EmployeeAddress temporaryAddress = new EmployeeAddress();
            temporaryAddress.setAddress(tempAddress);
            temporaryAddress.setHouseNumber(tempHouseNumber);
            temporaryAddress.setCity(tempCity);
            temporaryAddress.setState(tempState);
            temporaryAddress.setPinCode(tempPinCode);

            Employee employee = new Employee();
            employee.setEmployeeBasicDetails(basicDetails);
            employee.setEmployeePermanentAddress(permanentAddress);
            employee.setEmployeeTemporaryAddress(temporaryAddress);

            CreateEmployeeRequest request = new CreateEmployeeRequest();
            request.setEmployee(employee);

            CreateEmployeeResponse response = (CreateEmployeeResponse) webServiceTemplate.marshalSendAndReceive(request);
            ServiceStatus status = response.getServiceStatus();
            System.out.println(resourceBundle.getString("create.status") + status.getStatus());
            logger.info(resourceBundle.getString("create.status"));
            System.out.println(resourceBundle.getString("create.message") + status.getMessage());
            logger.info(resourceBundle.getString("create.message"));


//                Employee createdEmployee = response.getEmployee();
//                displayEmployeeDetails(createdEmployee);

            System.out.print(resourceBundle.getString("add.another.employee"));
            String choice = scanner.next();
            if (!"yes".equalsIgnoreCase(choice)) {
                addMoreEmployees = false;
            }
            scanner.nextLine(); // Consume newline character
        }
    }

    private static void filterByIdRequest(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        System.out.print(resourceBundle.getString("filter.id"));
        String employeeId = scanner.nextLine();
        FilterByIdRequest request = new FilterByIdRequest();
        request.setEmployeeId(employeeId);
        FilterByIdResponse response = (FilterByIdResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();
        System.out.println(resourceBundle.getString("id.response") + status.getStatus());
        System.out.println(resourceBundle.getString("id.message") + status.getMessage());
            Employee filteredEmployee = response.getEmployee();
            displayEmployeeDetails(filteredEmployee);

    }

    private static void findAllEmployeeRequest(WebServiceTemplate webServiceTemplate) {
        FindAllEmployeeRequest request = new FindAllEmployeeRequest();
        FindAllEmployeeResponse response = (FindAllEmployeeResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();
        System.out.println(resourceBundle.getString("filter.idResponse")+ status.getStatus());
        System.out.println(resourceBundle.getString("filter.idMessage") + status.getMessage());
            List<Employee> employees = response.getEmployee();
            for (Employee employee : employees) {
                displayEmployeeDetails(employee);
                System.out.println("---------------------------");

        }
    }

    private static void filterByPinCodeRequest(WebServiceTemplate webServiceTemplate, Scanner scanner) {
        System.out.print(resourceBundle.getString("enter.pinCodeFilter"));
        int pinCode = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        GetEmployeeByPinCodeRequest request = new GetEmployeeByPinCodeRequest();
        request.setPinCode(pinCode);
        GetEmployeeByPinCodeResponse response = (GetEmployeeByPinCodeResponse) webServiceTemplate.marshalSendAndReceive(request);
        ServiceStatus status = response.getServiceStatus();
        System.out.println(resourceBundle.getString("pinCode.response") + status.getStatus());
        System.out.println(resourceBundle.getString("pinCode.message") + status.getMessage());
            List<Employee> employees = response.getEmployee();
            for (Employee employee : employees) {
                displayEmployeeDetails(employee);
                System.out.println("---------------------------");
            }

    }

    private static void displayEmployeeDetails(Employee employee) {
        EmployeeBasicDetails basicDetails = employee.getEmployeeBasicDetails();
        EmployeeAddress permanentAddress = employee.getEmployeePermanentAddress();
        EmployeeAddress temporaryAddress = employee.getEmployeeTemporaryAddress();

        System.out.println("\nEmployee Details:");
        System.out.println(resourceBundle.getString("enter.id")+ basicDetails.getEmployeeId());
        System.out.println(resourceBundle.getString("enter.name") + basicDetails.getEmployeeName());
        System.out.println(resourceBundle.getString("enter.email")+ basicDetails.getEmailId());
        System.out.println(resourceBundle.getString("enter.phoneNumber")+ basicDetails.getPhoneNumber());

        System.out.println("\nPermanent Address:");
        System.out.println(resourceBundle.getString("enter.address") + permanentAddress.getAddress());
        System.out.println(resourceBundle.getString("enter.houseNumber") + permanentAddress.getHouseNumber());
        System.out.println(resourceBundle.getString("enter.city")+ permanentAddress.getCity());
        System.out.println(resourceBundle.getString("enter.state") + permanentAddress.getState());
        System.out.println(resourceBundle.getString("enter.pinCode") + permanentAddress.getPinCode());

        System.out.println("\nTemporary Address:");
        System.out.println(resourceBundle.getString("enter.address") + temporaryAddress.getAddress());
        System.out.println(resourceBundle.getString("enter.houseNumber")+ temporaryAddress.getHouseNumber());
        System.out.println(resourceBundle.getString("enter.city") + temporaryAddress.getCity());
        System.out.println(resourceBundle.getString("enter.state") + temporaryAddress.getState());
        System.out.println(resourceBundle.getString("enter.pinCode") + temporaryAddress.getPinCode());
    }

}
