package org.example;

import java.util.Scanner;

public class EmployeeDetails{


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        EmployeeDetails employeeDetails=new EmployeeDetails();
        employeeDetails.inputDetail();
      }
      public void inputDetail(){
          Scanner scanner=new Scanner(System.in);
          System.out.println("Enter your First name :");
          String employeeFirstName = scanner.nextLine();
          System.out.println("Enter your middle name :");
          String employeeMiddleName = scanner.nextLine();
          System.out.println("Enter your Last name :");
          String employeeLastName = scanner.nextLine();
          System.out.println("Enter your permanent address :");
          System.out.println("Enter the Address :");
          String permanentAddress=scanner.nextLine();
          System.out.println("Enter the House Number :");
          String permanentHouseNumber=scanner.nextLine();
          System.out.println("Enter the city :");
          String permanentCity=scanner.nextLine();
          System.out.println("Enter the State :");
          String permanentState= scanner.nextLine();
          System.out.println("Enter the PinCode :");
          int permanentPinCode=scanner.nextInt();
          System.out.println("Enter your temporary address :");
          System.out.println("Enter the Address :");
          String temporaryAddress=scanner.nextLine();
          System.out.println("Enter the House Number :");
          String temporaryHouseNumber=scanner.nextLine();
          System.out.println("Enter the city :");
          String temporaryCity=scanner.nextLine();
          System.out.println("Enter the State :");
          String temporaryState= scanner.nextLine();
          System.out.println("Enter the PinCode :");
          int temporaryPinCode=scanner.nextInt();
          System.out.println("Enter the Email Id :");
          String emailId=scanner.next();
          System.out.println("Enter the Phone Number :");
          long phoneNumber=scanner.nextLong();
          Employee employees=new Employee(employeeFirstName, employeeMiddleName,  employeeLastName, permanentAddress, permanentHouseNumber, permanentState, permanentCity,permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryState, temporaryCity, temporaryPinCode,emailId, phoneNumber);

      }

}
