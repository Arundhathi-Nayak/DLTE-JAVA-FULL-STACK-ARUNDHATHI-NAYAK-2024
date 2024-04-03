package org.webconsole.console;


import implementation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.validation.Validation;
import org.webconsole.Details.EmployeeAddress;
import org.webconsole.Details.EmployeeBasicDetails;

import java.util.InputMismatchException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.System.exit;

public class ClientConsole {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static Logger logger = LoggerFactory.getLogger(ClientConsole.class);
    private static ServicesService servicesService = new ServicesService();
    private static Services port = servicesService.getServicesPort();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Validation validation = new Validation();
            try {
                System.out.println(resourceBundle.getString("greet"));
                while (true) {
                    System.out.println(resourceBundle.getString("menu.display"));
                    System.out.println(resourceBundle.getString("enter.choice"));
                    int choice;
                    try {
                        choice = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println(resourceBundle.getString("Enter.number1"));
                        scanner.nextLine();
                        continue;
                    }
                    switch (choice) {
                        case 1:
                            addEmployeeDetails(scanner, validation);
                            break;
                        case 2:
                            displayEmployeeById(scanner);
                            break;
                        case 3:
                            displayAllEmployees();
                            break;
                        case 4:
                            displayEmployeeByPinCode(scanner);
                            break;
                        case 5:
                            exit(0);
                        default:
                            System.out.println(resourceBundle.getString("invalid.choice"));
                    }
                }
            } catch (Exception exception) {
                System.out.println
                        (exception.getMessage());
            }
        }
    }

    private static void addEmployeeDetails(Scanner scanner, Validation validation) {
        do {
            org.webconsole.Details.Employee employee = new org.webconsole.Details.Employee();
            EmployeeAddress permanentAddress;
            EmployeeAddress temporaryAddress ;
            EmployeeBasicDetails basicDetails = new EmployeeBasicDetails();

            System.out.println(resourceBundle.getString("enter.employeeDetails"));

            System.out.print(resourceBundle.getString("Enter.name"));
            scanner.nextLine();
            basicDetails.setEmployeeName(scanner.nextLine());

            System.out.print(resourceBundle.getString("enter.id"));
            basicDetails.setEmployeeId(scanner.nextLine());

            String email;
            boolean validEmail;
            do {
                System.out.print(resourceBundle.getString("enter.emailId"));
                email = scanner.nextLine();

                validEmail = validation.isValidEmail(email);
                if (!validEmail) {
                    System.out.println(resourceBundle.getString("invalid.email"));
                } else {
                    basicDetails.setEmailId(email);
                }
            } while (!validEmail);

            long phoneNumber;
            boolean validPhoneNumber;
            do {
                System.out.print(resourceBundle.getString("enter.phone"));
                phoneNumber = Long.parseLong(scanner.nextLine());

                validPhoneNumber = validation.isValidPhoneNumber(phoneNumber);
                if (!validPhoneNumber) {
                    System.out.println(resourceBundle.getString("invalid.Phone"));
                } else {
                    basicDetails.setPhoneNumber(phoneNumber);
                }
            } while (!validPhoneNumber);

            System.out.println(resourceBundle.getString("enter.permanentAddress"));
            permanentAddress = getEmployeeAddressFromUser(scanner, validation);

            System.out.println(resourceBundle.getString("enter.temporaryaddress"));
            temporaryAddress = getEmployeeAddressFromUser(scanner, validation);

            employee.setEmployeeBasicDetails(basicDetails);
            employee.setEmployeePermanentAddress(permanentAddress);
            employee.setEmployeeTemporaryAddress(temporaryAddress);
            Employee emp;
            emp=transalte(employee);
            port.callSaveAll(emp);
            System.out.print(resourceBundle.getString("add.more"));
        } while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));
    }

    private static Employee transalte(org.webconsole.Details.Employee employee) {
        Employee employee1=new Employee();
        EmployeebasicDetails employeeDetails=new EmployeebasicDetails();
        implementation.EmployeeAddress tempAddr=new implementation.EmployeeAddress();
        implementation.EmployeeAddress perAddr=new implementation.EmployeeAddress();

        employeeDetails.setEmployeeName(employee.getEmployeeBasicDetails().getEmployeeName());
        employeeDetails.setEmployeeId(employee.getEmployeeBasicDetails().getEmployeeId());
        employeeDetails.setEmailId(employee.getEmployeeBasicDetails().getEmailId());
        employeeDetails.setPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber());

        perAddr.setAddress(employee.getEmployeePermanentAddress().getAddress());
        perAddr.setHouseNumber(employee.getEmployeePermanentAddress().getHouseNumber());
        perAddr.setCity(employee.getEmployeePermanentAddress().getCity());
        perAddr.setState(employee.getEmployeePermanentAddress().getState());
        perAddr.setPinCode(employee.getEmployeePermanentAddress().getPinCode());

        tempAddr.setAddress(employee.getEmployeeTemporaryAddress().getAddress());
        tempAddr.setHouseNumber(employee.getEmployeeTemporaryAddress().getHouseNumber());
        tempAddr.setCity(employee.getEmployeeTemporaryAddress().getCity());
        tempAddr.setState(employee.getEmployeeTemporaryAddress().getState());
        tempAddr.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());

        employee1.setEmployeebasicDetails(employeeDetails);
        employee1.setEmployeePermanentAddress(perAddr);
        employee1.setEmployeeTemporaryAddress(tempAddr);
        return employee1;


    }

    private static EmployeeAddress getEmployeeAddressFromUser(Scanner scanner, Validation validation) {
        EmployeeAddress address = new EmployeeAddress();

        System.out.print(resourceBundle.getString("enter.address"));
        address.setAddress(scanner.nextLine());

        System.out.print(resourceBundle.getString("enter.HouseNumber"));
        address.setHouseNumber(scanner.nextLine());

        System.out.print(resourceBundle.getString("enter.city"));
        address.setCity(scanner.nextLine());

        System.out.print(resourceBundle.getString("enter.state"));
        address.setState(scanner.nextLine());

        boolean validPinCode;
        do {
            System.out.print(resourceBundle.getString("enter.pincode"));
            int pinCode = Integer.parseInt(scanner.nextLine());
            address.setPinCode(pinCode);

            validPinCode = validation.isValidPin(pinCode);
            if (!validPinCode) {
                System.out.println(resourceBundle.getString("invalid.Pin"));
            }
        } while (!validPinCode);

        return address;
    }

    private static void displayEmployeeById(Scanner scanner) {
        System.out.println(resourceBundle.getString("enter.id"));
        String employeeId = scanner.next();
        try {
            Employee employee = port.callFilterBasedOnID(employeeId);
            if (employee != null) {
                System.out.println(formatEmployee(employee));
                System.out.println();
            } else {
                System.out.println(resourceBundle.getString("no.employee")+ employeeId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }

    private static void displayAllEmployees() {
        try {
            GroupOfEmployee employees = port.callFindAll();
            List<implementation.Employee> employeeList = employees.getEmployeeArrayList();
            if (!employeeList.isEmpty()) {
                for (implementation.Employee emp : employeeList) {
                    System.out.println(formatEmployee(emp));
                    System.out.println();
                }
            } else {
                System.out.println(resourceBundle.getString("employee.not.found"));
                logger.warn(resourceBundle.getString("employee.not.found"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }

    private static void displayEmployeeByPinCode(Scanner scanner) {
        System.out.println(resourceBundle.getString("enter.pincode"));
        int pinCode = scanner.nextInt();
        try {
            GroupOfEmployee result = port.callFilterBasedOnPincode(pinCode);
            List<Employee> employeeList = result.getEmployeeArrayList();
            if (!employeeList.isEmpty()) {
                System.out.println(resourceBundle.getString("employee.with") + pinCode + ":");
                for (Employee emp : employeeList) {
                    System.out.println(formatEmployee(emp));
                    System.out.println();
                }
            } else {
                System.out.println(resourceBundle.getString("no.pin") + pinCode);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }
    private static String formatEmployee(Employee employee) {
        // Format employee details
        return "Employee Details: " +
               "\nEmployee ID: " + employee.getEmployeebasicDetails().getEmployeeId() +
                "\nName: " + employee.getEmployeebasicDetails().getEmployeeName() +
                "\nEmail: " + employee.getEmployeebasicDetails().getEmailId() +
                "\nPhone Number: " + employee.getEmployeebasicDetails().getPhoneNumber() +
                "\nPermanent Address: " + employee.getEmployeePermanentAddress().getAddress() +
                "\nPermanent House Number: " + employee.getEmployeePermanentAddress().getHouseNumber() +
                "\nPermanent City: " + employee.getEmployeePermanentAddress().getCity() +
                "\nPermanent State: " + employee.getEmployeePermanentAddress().getState() +
                "\nPermanent Pin Code: " + employee.getEmployeePermanentAddress().getPinCode() +
                "\nTemporary Address: " + employee.getEmployeeTemporaryAddress().getAddress() +
                "\nTemporary House Number: " + employee.getEmployeeTemporaryAddress().getHouseNumber() +
                "\nTemporary City: " + employee.getEmployeeTemporaryAddress().getCity() +
                "\nTemporary State: " + employee.getEmployeeTemporaryAddress().getState() +
                "\nTemporary Pin Code: " + employee.getEmployeeTemporaryAddress().getPinCode();
    }
}
