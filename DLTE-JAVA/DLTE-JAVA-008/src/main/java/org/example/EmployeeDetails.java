package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class EmployeeDetails implements InputInterface {
    private static EmployeeAddress employeeAddress;

    private  static  Employee employee;
    private static EmployeeInformation employeeInformation;

//    public static Employee inputDetail(Scanner scanner) {
//       // Scanner scanner= new Scanner(System.in);
//        System.out.println("Enter your name :");
//        //Scanner scanner=new Scanner(System.in);
//        String employeeName = scanner.nextLine();
////            System.out.println("Enter your middle name :");
////            String employeeMiddleName = scanner.nextLine();
////            System.out.println("Enter your Last name :");
////            String employeeLastName = scanner.nextLine();
//
//        System.out.println("Enter your permanent address :");
//        System.out.println("Enter the Address :");
//        String permanentAddress = scanner.nextLine();
//        System.out.println("Enter the House Number :");
//        String permanentHouseNumber = scanner.nextLine();
//        System.out.println("Enter the city :");
//        String permanentCity = scanner.nextLine();
//        System.out.println("Enter the State :");
//        String permanentState = scanner.nextLine();
//        System.out.println("Enter the PinCode :");
//        int permanentPinCode=0;
//        try {
//             permanentPinCode = scanner.nextInt();
//        }catch (InputMismatchException e){
//            System.out.println("Enter in the Pincode number format");
//        }
//        System.out.println("Enter your temporary address :");
//        System.out.println("Enter the Address :");
//        scanner.nextLine();
//        String temporaryAddress = scanner.nextLine();
//        System.out.println("Enter the House Number :");
//        String temporaryHouseNumber = scanner.nextLine();
//        System.out.println("Enter the city :");
//        String temporaryCity = scanner.nextLine();
//        System.out.println("Enter the State :");
//        String temporaryState = scanner.nextLine();
//        System.out.println("Enter the PinCode :");
//        int temporaryPinCode=0;
//        try {
//            temporaryPinCode = scanner.nextInt();
//        }catch (InputMismatchException e){
//            System.out.println("Enter in the Pincode number format");
//        }
//        employeeAddress = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode);
//
//        System.out.println("Enter the Email Id :");
//        String emailId = scanner.next();
//        System.out.println("Enter the Phone Number :");
//        long phoneNumber=0;
//        try {
//            phoneNumber = scanner.nextLong();
//        }catch (InputMismatchException e){
//            System.out.println("Enter Phone number in number format");
//        }
//        employeeInformation = new EmployeeInformation(emailId, phoneNumber);
//        employee = new Employee(employeeName, employeeAddress, employeeInformation);
//        System.out.println("Employee added successfuly");
//
////        List<Employee> employeeInfo=new ArrayList<>();
////        employeeInfo.add(employee);
////        employeeDetails.create(employeeInfo);
//
//        return employee;
//    }
    // EmployeeDetails employeeDetails=new EmployeeDetails();



    //public static void inputDetail() throws IOException
//    public static Employee inputDetail(Scanner scanner) {
//            System.out.println("Enter your name :");
//            //Scanner scanner=new Scanner(System.in);
//            String employeeName = scanner.nextLine();
////            System.out.println("Enter your middle name :");
////            String employeeMiddleName = scanner.nextLine();
////            System.out.println("Enter your Last name :");
////            String employeeLastName = scanner.nextLine();
//
//            System.out.println("Enter your permanent address :");
//            System.out.println("Enter the Address :");
//            String permanentAddress = scanner.nextLine();
//            System.out.println("Enter the House Number :");
//            String permanentHouseNumber = scanner.nextLine();
//            System.out.println("Enter the city :");
//            String permanentCity = scanner.nextLine();
//            System.out.println("Enter the State :");
//            String permanentState = scanner.nextLine();
//            System.out.println("Enter the PinCode :");
//            int permanentPinCode = scanner.nextInt();
//            System.out.println("Enter your temporary address :");
//            System.out.println("Enter the Address :");
//            scanner.nextLine();
//            String temporaryAddress = scanner.nextLine();
//            System.out.println("Enter the House Number :");
//            String temporaryHouseNumber = scanner.nextLine();
//            System.out.println("Enter the city :");
//            String temporaryCity = scanner.nextLine();
//            System.out.println("Enter the State :");
//            String temporaryState = scanner.nextLine();
//            System.out.println("Enter the PinCode :");
//            int temporaryPinCode = scanner.nextInt();
//            employeeAddress = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode);
//
//            System.out.println("Enter the Email Id :");
//            String emailId = scanner.next();
//            System.out.println("Enter the Phone Number :");
//            long phoneNumber = scanner.nextLong();
//            employeeInformation = new EmployeeInformation(emailId, phoneNumber);
//            employee = new Employee(employeeName, employeeAddress, employeeInformation);
//            System.out.println("Employee added successfuly");
//
////        List<Employee> employeeInfo=new ArrayList<>();
////        employeeInfo.add(employee);
////        employeeDetails.create(employeeInfo);
//
//           return employee;
//    }
////    public static void create(List<Employee> employee) throws IOException {
//
////        FileOutputStream fileOutputStream=new FileOutputStream("OutputFile.txt");
////        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
////        objectOutputStream.writeObject(employee);
////        objectOutputStream.close();
////        fileOutputStream.close();
////    }

//    public static void displayInput(List<Employee> employees) {
//        if (employees.isEmpty()){
//            System.out.println("No employess added yet.");
//        }
//        else{
//            System.out.println("employee Details:");
//            for(Employee emp: employees){
//                System.out.println("Name= "+emp.getEmployeeName());
//                System.out.println("Permanent Address :" + emp.getAddress().getPermanentAddress()+","+emp.getAddress().getPermanentHouseNumber()+","+emp.getAddress().getPermanentCity()+","+emp.getAddress().getPermanentState()+","+emp.getAddress().getPermanentPinCode());
//
//                System.out.println("Temporary Address :"+emp.getAddress().getTemporaryAddress()+","+emp.getAddress().getTemporaryHouseNumber()+","+emp.getAddress().getTemporaryCity()+","+emp.getAddress().getTemporaryState()+","+emp.getAddress().getTemporaryPinCode());
//                System.out.println("Email id :" + emp.getAdditionalInformation().getEmailId() + "\nPhone number :" + emp.getAdditionalInformation().getPhoneNumber());
//                System.out.println(" ");
//
//            }
//        }
//
//
//    }

    @Override
    public Employee inputDetails(Scanner scanner) {
       // Scanner scanner= new Scanner(System.in);
        System.out.println("Enter your name :");
        //Scanner scanner=new Scanner(System.in);
        String employeeName = scanner.nextLine();
//            System.out.println("Enter your middle name :");
//            String employeeMiddleName = scanner.nextLine();
//            System.out.println("Enter your Last name :");
//            String employeeLastName = scanner.nextLine();

        System.out.println("Enter your permanent address :");
        System.out.println("Enter the Address :");
        String permanentAddress = scanner.nextLine();
        System.out.println("Enter the House Number :");
        String permanentHouseNumber = scanner.nextLine();
        System.out.println("Enter the city :");
        String permanentCity = scanner.nextLine();
        System.out.println("Enter the State :");
        String permanentState = scanner.nextLine();
        System.out.println("Enter the PinCode :");
        int permanentPinCode = scanner.nextInt();
        System.out.println("Enter your temporary address :");
        System.out.println("Enter the Address :");
        scanner.nextLine();
        String temporaryAddress = scanner.nextLine();
        System.out.println("Enter the House Number :");
        String temporaryHouseNumber = scanner.nextLine();
        System.out.println("Enter the city :");
        String temporaryCity = scanner.nextLine();
        System.out.println("Enter the State :");
        String temporaryState = scanner.nextLine();
        System.out.println("Enter the PinCode :");
        int temporaryPinCode = scanner.nextInt();
        employeeAddress = new EmployeeAddress(permanentAddress, permanentHouseNumber, permanentCity, permanentState, permanentPinCode, temporaryAddress, temporaryHouseNumber, temporaryCity, temporaryState, temporaryPinCode);

        System.out.println("Enter the Email Id :");
        String emailId = scanner.next();
        System.out.println("Enter the Phone Number :");
        long phoneNumber = scanner.nextLong();
        employeeInformation = new EmployeeInformation(emailId, phoneNumber);
        employee = new Employee(employeeName, employeeAddress, employeeInformation);
        System.out.println("Employee added successfuly");

//        List<Employee> employeeInfo=new ArrayList<>();
//        employeeInfo.add(employee);
//        employeeDetails.create(employeeInfo);

        return employee;
    }

    @Override
    public void displayInput(List<Employee> employees) {

            if (employees.isEmpty()){
                System.out.println("No employess added yet.");
            }
            else{
                System.out.println("employee Details:");
                for(Employee emp: employees){
                    System.out.println("Name= "+emp.getEmployeeName());
                    System.out.println("Permanent Address :" + emp.getAddress().getPermanentAddress()+","+emp.getAddress().getPermanentHouseNumber()+","+emp.getAddress().getPermanentCity()+","+emp.getAddress().getPermanentState()+","+emp.getAddress().getPermanentPinCode());

                    System.out.println("Temporary Address :"+emp.getAddress().getTemporaryAddress()+","+emp.getAddress().getTemporaryHouseNumber()+","+emp.getAddress().getTemporaryCity()+","+emp.getAddress().getTemporaryState()+","+emp.getAddress().getTemporaryPinCode());
                    System.out.println("Email id :" + emp.getAdditionalInformation().getEmailId() + "\nPhone number :" + emp.getAdditionalInformation().getPhoneNumber());
                    System.out.println(" ");

                }
            }



    }


}
