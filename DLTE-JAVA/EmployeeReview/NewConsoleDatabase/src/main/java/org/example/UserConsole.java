package org.example;

import org.example.Details.Employee;
import org.example.Details.EmployeeAddress;
import org.example.Details.EmployeebasicDetails;
import org.example.Details.InputEmployeeDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import static java.lang.System.exit;

public class UserConsole {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        ResourceBundle resourceBundle= ResourceBundle.getBundle("application");

            InputEmployeeDetails inputEmployeeDetails = new DatabaseRepositoryImplementation();
            try{
            List<Employee> employees = new ArrayList<>();
            while (true) {
                System.out.println(resourceBundle.getString("menu.display"));
                System.out.println(resourceBundle.getString("enter.choice"));
                switch (scanner.nextInt()) {
                    case 1:
                        System.out.println("Enter employee details:");
                        System.out.print("Name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();

                        System.out.print("ID: ");
                        String id = scanner.nextLine();

                        System.out.print("Email: ");
                        String email = scanner.nextLine();

                        System.out.print("Phone number: ");
                        long phoneNumber = Long.parseLong(scanner.nextLine());

                        System.out.println("Enter permanent address details:");
                        System.out.print("Address: ");
                        String permanentAddress = scanner.nextLine();

                        System.out.print("House number: ");
                        String permanentHouseNumber = scanner.nextLine();

                        System.out.print("City: ");
                        String permanentCity = scanner.nextLine();

                        System.out.print("State: ");
                        String permanentState = scanner.nextLine();

                        System.out.print("Pin code: ");
                        int permanentPinCode = Integer.parseInt(scanner.nextLine());

                        System.out.println("Enter temporary address details:");
                        System.out.print("Address: ");
                        String temporaryAddress = scanner.nextLine();

                        System.out.print("House number: ");
                        String temporaryHouseNumber = scanner.nextLine();

                        System.out.print("City: ");
                        String temporaryCity = scanner.nextLine();

                        System.out.print("State: ");
                        String temporaryState = scanner.nextLine();

                        System.out.print("Pin code: ");
                        int temporaryPinCode = Integer.parseInt(scanner.nextLine());

                        EmployeebasicDetails basicDetails = new EmployeebasicDetails(name, id, email, phoneNumber);
                        EmployeeAddress permanentAddr = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentState, permanentCity, permanentPinCode);
                        EmployeeAddress temporaryAddr = new EmployeeAddress(temporaryAddress, temporaryHouseNumber, temporaryState, temporaryCity, temporaryPinCode);

                        Employee employee = new Employee(basicDetails, permanentAddr, temporaryAddr);
                        List<Employee> employeeInfo = new ArrayList<>();
                        employeeInfo.add(employee);
                        inputEmployeeDetails.create(employeeInfo);

                        System.out.print("Do you want to add another employee? (yes/no): ");
                        String choice = scanner.nextLine();
                        if (!choice.equalsIgnoreCase("yes")) {
                            break;
                        }
                        break;
                    case 2:
                        System.out.println(resourceBundle.getString("enter.id"));

                        System.out.println(inputEmployeeDetails.displayBasedOnEmployeeId(scanner.next()));
                        break;
                    case 3:
                        System.out.println(inputEmployeeDetails.read());
                        break;
                    case 4:
                        System.out.println(resourceBundle.getString("enter.pincode"));
                        System.out.println(inputEmployeeDetails.displayBasedOnPinCode(scanner.nextInt()));
                        break;
                    case 5:
                        exit(0);
                }
            }
        } finally {
            // Close connections
            inputEmployeeDetails.closeConnections();
        }
    }
}
