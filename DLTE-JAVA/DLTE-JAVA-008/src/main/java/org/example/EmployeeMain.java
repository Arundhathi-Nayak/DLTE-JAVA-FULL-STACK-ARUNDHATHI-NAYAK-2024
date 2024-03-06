package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class EmployeeMain {
    public static void main(String[] args) throws IOException {
        List<Employee> employees = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1.Add Employee\n2.Display Employee\n3.Exit");
            System.out.println("Enter your choice: ");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    do{
                        //Employee employee=EmployeeDetails.inputDetails(scanner);
                        EmployeeDetails employeeDetails=new EmployeeDetails();
                        employees.add(employeeDetails.inputDetails(scanner));
                   // employees.add(employee);
                    System.out.println("Do you want to add one more employee?(yes/no): ");
                    scanner.nextLine();
                }while(scanner.nextLine().equalsIgnoreCase("yes"));
                  break;
                case 2:EmployeeDetails employeeDetails=new EmployeeDetails();
                    employeeDetails.displayInput( employees);
                    //EmployeeDetails.displayInput(employees);
                       break;
                case 3:exit(0);
                default:
                    System.out.println("invalid choice");
                    break;
            }
        }


    }

}
