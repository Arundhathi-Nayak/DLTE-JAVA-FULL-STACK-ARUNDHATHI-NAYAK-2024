package org.consolemethod.console;

import org.consolemethod.Details.EmployeeAddress;
import org.consolemethod.Details.EmployeeBasicDetails;
import org.consolemethod.Details.Employee;


import org.reviewdatabase.Details.*;
import org.reviewdatabase.exception.EmployeeNotFoundException;
import org.reviewdatabase.remote.InputEmployeeDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.validation.Validation;

import java.util.*;

import static java.lang.System.exit;

public class ConsoleApp {

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static Logger logger= LoggerFactory.getLogger(ConsoleApp.class);

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputEmployeeDetails inputEmployeeDetails = new DatabaseRepositoryImplementation();
            Validation validation=new Validation();
            try {
                System.out.println(resourceBundle.getString("greet"));
                while (true) {
                   boolean validate=false;
                    System.out.println(resourceBundle.getString("menu.display"));
                    System.out.println(resourceBundle.getString("enter.choice"));
                    int choice=0;
                    do {
                        try {
                            choice = scanner.nextInt();
                            validate = true;
                        }
                        // checking for input format
                        catch (InputMismatchException inputMismatchException) {
                            System.out.println(resourceBundle.getString("Enter.number1"));
                            scanner.nextLine();
                        }
                    }while (!validate);
                    switch (choice) {
                        case 1:addEmployeeDetails(scanner, inputEmployeeDetails, validation);
                            break;
                        case 2:
                            displayEmployeeById(scanner, inputEmployeeDetails);
                            break;
                        case 3:
                            displayAllEmployees(inputEmployeeDetails);
                            break;
                        case 4:
                            displayEmployeeByPinCode(scanner, inputEmployeeDetails);
                            break;
                        case 5:
                            exit(0);
                        default:
                            System.out.println(resourceBundle.getString("invalid.choice"));
                    }
                }
            }catch (EmployeeNotFoundException exception){
                System.out.println(exception.getMessage());
            }
            finally {
                // Close connections
              //  inputEmployeeDetails.closeConnections();
                scanner.close();
            }
        }
    }







    private static void addEmployeeDetails(Scanner scanner, InputEmployeeDetails inputEmployeeDetails, Validation validation) {
        List<org.reviewdatabase.Details.Employee> employees = new ArrayList<>();
        Employee employeeConsole;
        EmployeeAddress employeePermanentAddressConsole;
        EmployeeAddress employeeTemporaryAddressConsole;
        EmployeeBasicDetails employeeBasicDetails;
        do {
            System.out.println(resourceBundle.getString("enter.employeeDetails"));
            System.out.print(resourceBundle.getString("Enter.name"));
            scanner.nextLine();
            String name = scanner.nextLine();

            System.out.print(resourceBundle.getString("enter.id"));
            String id = scanner.nextLine();

            String email;
            boolean validEmail = false;
            do {
                System.out.print(resourceBundle.getString("enter.emailId"));
                email = scanner.nextLine();

                if (!validation.isValidEmail(email)) {
                    System.out.println(resourceBundle.getString("invalid.email"));
                } else {
                    validEmail = true;
                }
            } while (!validEmail);

            long phoneNumber;
            boolean validPhoneNumber = false;
            do {
                System.out.print(resourceBundle.getString("enter.phone"));
                phoneNumber = Long.parseLong(scanner.nextLine());

                if (!validation.isValidPhoneNumber(phoneNumber)) {
                    System.out.println(resourceBundle.getString("invalid.Phone"));
                } else {
                    validPhoneNumber = true;
                }
            } while (!validPhoneNumber);

            String permanentAddress;
            String permanentHouseNumber;
            String permanentCity;
            String permanentState;
            int permanentPinCode;

            System.out.println(resourceBundle.getString("enter.permanentAddress"));
            System.out.print(resourceBundle.getString("enter.address"));
            permanentAddress = scanner.nextLine();

            System.out.print(resourceBundle.getString("enter.HouseNumber"));
            permanentHouseNumber = scanner.nextLine();

            System.out.print(resourceBundle.getString("enter.city"));
            permanentCity = scanner.nextLine();

            System.out.print(resourceBundle.getString("enter.state"));
            permanentState = scanner.nextLine();

            boolean validPermanentPinCode = false;
            do {
                System.out.print(resourceBundle.getString("enter.pincode"));
                permanentPinCode = Integer.parseInt(scanner.nextLine());

                if (!validation.isValidPin(permanentPinCode)) {
                    System.out.println(resourceBundle.getString("invalid.Pin"));
                } else {
                    validPermanentPinCode = true;
                }
            } while (!validPermanentPinCode);

            String temporaryAddress;
            String temporaryHouseNumber;
            String temporaryCity;
            String temporaryState;
            int temporaryPinCode;

            System.out.println(resourceBundle.getString("enter.temporaryaddress"));
            System.out.print(resourceBundle.getString("enter.address"));
            temporaryAddress = scanner.nextLine();

            System.out.print(resourceBundle.getString("enter.HouseNumber"));
            temporaryHouseNumber = scanner.nextLine();

            System.out.print(resourceBundle.getString("enter.city"));
            temporaryCity = scanner.nextLine();

            System.out.print(resourceBundle.getString("enter.state"));
            temporaryState = scanner.nextLine();

            boolean validTemporaryPinCode = false;
            do {
                System.out.print(resourceBundle.getString("enter.pincode"));
                temporaryPinCode = Integer.parseInt(scanner.nextLine());

                if (!validation.isValidPin(temporaryPinCode)) {
                    System.out.println(resourceBundle.getString("invalid.Pin"));
                } else {
                    validTemporaryPinCode = true;
                }
            } while (!validTemporaryPinCode);

            // EmployeebasicDetails basicDetails;
            employeeBasicDetails = new EmployeeBasicDetails(name, id, email, phoneNumber);
            //  EmployeeAddress permanentAddr;
            employeePermanentAddressConsole = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentState, permanentCity, permanentPinCode);
            //  EmployeeAddress temporaryAddr ;
            employeeTemporaryAddressConsole = new EmployeeAddress(temporaryAddress, temporaryHouseNumber, temporaryState, temporaryCity, temporaryPinCode);
            //   basicDetails=translate(employeeBasicDetailsConsole);
            //  permanentAddr=translatePermanentAddress(employeePermanentAddressConsole);
            //   temporaryAddr=translateTemporaryAddress(employeeTemporaryAddressConsole);
            //    Employee employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
            employeeConsole = new Employee(employeeBasicDetails, employeePermanentAddressConsole, employeeTemporaryAddressConsole);
            //     Employee employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
            org.reviewdatabase.Details.Employee employee;
            employee = translateEmployee(employeeConsole);
            employees.add(employee);
            inputEmployeeDetails.create(employees);

            System.out.print(resourceBundle.getString("add.more"));
        } while (scanner.next().equalsIgnoreCase(resourceBundle.getString("yes")));

    }
 // filter based on employee id
    private static void displayEmployeeById(Scanner scanner, InputEmployeeDetails inputEmployeeDetails) {
        Employee employeeConsoleId;
        System.out.println(resourceBundle.getString("enter.id"));
        String employeeId = scanner.next();
        try {
            org.reviewdatabase.Details.Employee employee = inputEmployeeDetails.displayBasedOnEmployeeId(employeeId);
            employeeConsoleId=translate(employee);
            System.out.println(employeeConsoleId.displayEmployeeDetails());
            System.out.println();

        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }


    }
// display based on all employees
    private static void displayAllEmployees(InputEmployeeDetails inputEmployeeDetails) {
        Employee employeeConsole;
        List<org.reviewdatabase.Details.Employee> allEmployees = inputEmployeeDetails.read();
        if (!allEmployees.isEmpty()) {
            for (org.reviewdatabase.Details.Employee emp : allEmployees) {
                employeeConsole = translate(emp);
                System.out.println(employeeConsole.displayEmployeeDetails());
                System.out.println();
            }
        } else {
            System.out.println(resourceBundle.getString("employee.not.found"));
            logger.warn(resourceBundle.getString("employee.not.found"));
        }
    }
//filter based on pinCode
    private static void displayEmployeeByPinCode(Scanner scanner, InputEmployeeDetails inputEmployeeDetails) {
        Employee employeeConsolePin;
        System.out.println(resourceBundle.getString("enter.pincode"));
        int pinCode = scanner.nextInt();
        try {
            List<org.reviewdatabase.Details.Employee> employee =  inputEmployeeDetails.displayBasedOnPinCode(pinCode);
            if (!employee.isEmpty()) {
                for (org.reviewdatabase.Details.Employee emp : employee) {
                    employeeConsolePin = translate(emp);
                    System.out.println(employeeConsolePin.displayEmployeeDetails());
                    System.out.println();
                }
            }
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
            logger.warn(e.getMessage());
        }
    }

// translation back from database to employee

    private static Employee translate(org.reviewdatabase.Details.Employee employee) {

        EmployeeBasicDetails employeeBasicDetails =new EmployeeBasicDetails();
        EmployeeAddress tempAddress=new EmployeeAddress();
        EmployeeAddress permAddress=new EmployeeAddress();

        employeeBasicDetails.setEmployeeName(employee.getEmployeebasicDetails().getEmployeeName());
        employeeBasicDetails.setEmployeeId(employee.getEmployeebasicDetails().getEmployeeId());
        employeeBasicDetails.setEmailId(employee.getEmployeebasicDetails().getEmailId());
        employeeBasicDetails.setPhoneNumber(employee.getEmployeebasicDetails().getPhoneNumber());

        permAddress.setAddress(employee.getEmployeePermanentAddress().getAddress());
        permAddress.setHouseNumber(employee.getEmployeePermanentAddress().getHouseNumber());
        permAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        permAddress.setState(employee.getEmployeePermanentAddress().getState());
        permAddress.setPinCode(employee.getEmployeePermanentAddress().getPinCode());

        tempAddress.setAddress(employee.getEmployeeTemporaryAddress().getAddress());
        tempAddress.setHouseNumber(employee.getEmployeeTemporaryAddress().getHouseNumber());
        tempAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        tempAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        tempAddress.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());
        Employee translatedEmployee = new Employee();
        translatedEmployee.setEmployeeBasicDetails(employeeBasicDetails);
        translatedEmployee.setEmployeePermanentAddress(permAddress);
        translatedEmployee.setEmployeeTemporaryAddress(tempAddress);

        return translatedEmployee;
    }

// translation from console pogo to database pojo
    private static org.reviewdatabase.Details.Employee translateEmployee(Employee employee) {
        org.reviewdatabase.Details.EmployeeAddress employeeTemporaryAddress = new org.reviewdatabase.Details.EmployeeAddress();
        org.reviewdatabase.Details.EmployeeAddress employeePermanentAddress = new org.reviewdatabase.Details.EmployeeAddress();
        EmployeebasicDetails employeebasicDetails=new EmployeebasicDetails();
        employeebasicDetails.setEmployeeName(employee.getEmployeeBasicDetails().getEmployeeName());
        employeebasicDetails.setEmployeeId(employee.getEmployeeBasicDetails().getEmployeeId());
        employeebasicDetails.setEmailId(employee.getEmployeeBasicDetails().getEmailId());
        employeebasicDetails.setPhoneNumber(employee.getEmployeeBasicDetails().getPhoneNumber());

        employeePermanentAddress.setAddress(employee.getEmployeePermanentAddress().getAddress());
        employeePermanentAddress.setHouseNumber(employee.getEmployeePermanentAddress().getHouseNumber());
        employeePermanentAddress.setCity(employee.getEmployeePermanentAddress().getCity());
        employeePermanentAddress.setState(employee.getEmployeePermanentAddress().getState());
        employeePermanentAddress.setPinCode(employee.getEmployeePermanentAddress().getPinCode());

        employeeTemporaryAddress.setAddress(employee.getEmployeeTemporaryAddress().getAddress());
        employeeTemporaryAddress.setHouseNumber(employee.getEmployeeTemporaryAddress().getHouseNumber());
        employeeTemporaryAddress.setCity(employee.getEmployeeTemporaryAddress().getCity());
        employeeTemporaryAddress.setState(employee.getEmployeeTemporaryAddress().getState());
        employeeTemporaryAddress.setPinCode(employee.getEmployeeTemporaryAddress().getPinCode());

        org.reviewdatabase.Details.Employee translatedEmployee = new org.reviewdatabase.Details.Employee();
        translatedEmployee.setEmployeebasicDetails(employeebasicDetails);
        translatedEmployee.setEmployeePermanentAddress(employeePermanentAddress);
        translatedEmployee.setEmployeeTemporaryAddress(employeeTemporaryAddress);

        return translatedEmployee;


    }



}
