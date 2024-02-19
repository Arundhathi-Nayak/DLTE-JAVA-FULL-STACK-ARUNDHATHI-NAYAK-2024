package services.basic;

import java.security.spec.RSAOtherPrimeInfo;
import java.sql.SQLOutput;
import java.util.Scanner;
//command line interaction : car loan
/*
  Personal details : name, aadhaar , pan,address,mobile,email
  Income: salaried,self employed ITR

 */

public class Blocks {
    public static void main(String args[]){
        String borrowerName,borrowerPan,borrowerAddress,borrowerEmail,borrowerIncometype;
        Long mobileNumber,aadhaar;
        Scanner sc=new Scanner(System.in);
        System.out.println("Fill your name");
        System.out.println("Fill your name");
        borrowerName=sc.nextLine();
        System.out.println("Let us know your Income type(salaried/self employed)");
        borrowerIncometype=sc.nextLine();
        System.out.println("Fill your aadhaar number");
        aadhaar=sc.nextLong();
        System.out.println("Enter your pan");
        borrowerPan=sc.next();
        System.out.println("Mention mobile number");
        mobileNumber=sc.nextLong();
        System.out.println("Enter the email address");
        borrowerEmail=sc.next();
        System.out.println("Welcome to mybank");
        System.out.println("Dear "+borrowerName+" Thanks for showing interest in car loan in our bank we will send mail to "+borrowerEmail+" and number "+mobileNumber);


    }
}
