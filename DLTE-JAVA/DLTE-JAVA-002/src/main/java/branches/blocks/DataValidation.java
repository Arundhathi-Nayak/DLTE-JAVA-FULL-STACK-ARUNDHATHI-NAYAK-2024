package branches.blocks;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataValidation {
    public static void main(String[] args) {
        String borrowerName="",borrowerPan="",borrowerAddress="",borrowerEmail="",borrowerIncometype="";
        String  mobileNumber,aadhaar;
        Scanner sc=new Scanner(System.in);
        System.out.println("------Welcome to Worldbank------");
        System.out.println("Enter your name :");
        borrowerName=sc.nextLine();
        String nameExp="^[a-zA-Z .']+$";
        Pattern patternName=Pattern.compile(nameExp);
        Matcher matcherName=patternName.matcher(borrowerName);
        if(matcherName.matches())
            System.out.println("Valid name");
        else
            System.out.println("invalid name");
        System.out.println("Enter your Aadhaar number");
        aadhaar=sc.next();
        String aadhaarExp="[1-9]{1}[0-9]{11}";
        Pattern patternAadhaar=Pattern.compile(aadhaarExp);
        Matcher matcherAadhaar=patternAadhaar.matcher(aadhaar);
        if(matcherAadhaar.matches())
            System.out.println("Valid aadhaar number");
        else
            System.out.println("invalid aadhaar number");
        System.out.println("Fill your pan details");
        borrowerPan=sc.next();
        String cardExp="[A-Z]{5}[0-9]{4}[A-Z]";
        Pattern patternPan=Pattern.compile(cardExp);
        Matcher matcherPan=patternPan.matcher(borrowerPan);
        if(matcherPan.matches())
            System.out.println("Valid Pan number");
        else
            System.out.println("invalid Pan number");
        System.out.println("Enter your Number");
        mobileNumber=sc.next();
        String mobileExp="[6-9]{1}[0-9]{9}";
        Pattern patternMobile=Pattern.compile(mobileExp);
        Matcher matcherMobile=patternMobile.matcher(mobileNumber);
        if(matcherMobile.matches())
            System.out.println("Valid mobile number");
        else
            System.out.println("invalid mobile number");
        System.out.println("Enter your email Id");
        borrowerEmail=sc.next();
        String emailExp="^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-z]{2,})$";
        Pattern patternEmail=Pattern.compile(emailExp);
        Matcher matcherEmail=patternEmail.matcher(borrowerEmail);
        if(matcherEmail.matches())
            System.out.println("Valid Email");
        else
            System.out.println("invalid Email");
        System.out.println("Dear "+borrowerName+" Thanks for showing interest in car loan in our bank we will send mail to "+borrowerEmail+" and number "+mobileNumber);


    }
}
