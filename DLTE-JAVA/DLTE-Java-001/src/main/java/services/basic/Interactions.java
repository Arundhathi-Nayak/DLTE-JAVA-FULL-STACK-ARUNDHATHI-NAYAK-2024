package services.basic;

import java.util.Scanner;

import static java.lang.System.exit;

public class Interactions {
    public static void main(String[] args) {
        int ch=0;
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("----Welcome to World Bank-----");
            System.out.println("Choose which one do you prefer");
            System.out.println("1.Personal Loan");
            System.out.println("2.Internet Banking");
            System.out.println("3.Mobile Banking");
            System.out.println("4.FD Calculator");
            System.out.println("5.Exit");
            ch=sc.nextInt();
            switch(ch){
                case 1:
                    String borrowerName,borrowerPan,borrowerAddress,borrowerEmail,borrowerIncometype;
                    Long mobileNumber=0L,aadhaar;
                    System.out.println("Fill your name");
                    borrowerName=sc.next();
                    System.out.println("Let us know your Income type(salaried/self employed)");
                    borrowerIncometype=sc.next();
                    System.out.println("Fill your aadhaar number");
                    aadhaar=sc.nextLong();
                    System.out.println("Enter your pan");
                    borrowerPan=sc.next();
                    System.out.println("Enter the email address");
                    borrowerEmail=sc.next();
                    System.out.println("Mention mobile number");
                    mobileNumber=sc.nextLong();
                    System.out.println("Welcome to mybank");
                    System.out.println("Dear "+borrowerName+" Thanks for showing interest in car loan in our bank we will send mail to "+borrowerEmail+" and number "+mobileNumber);
                    break;
                case 2:
                    Long accNo=0L,cardNumber;
                    String name,email;
                    System.out.println("Enter the Name :");
                    name=sc.next();
                    System.out.println("Enter the email address :");
                    email=sc.next();
                    System.out.println("Enter Account Number :");
                    accNo=sc.nextLong();
                    System.out.println("Enter the Card Number");
                    cardNumber=sc.nextLong();
                    System.out.println("Dear "+ name +" Thank you,for selecting the internet banking");
                case 3:
                    Long accNumber=0L,cardNo;
                    String accname,emailadd;
                    System.out.println("Enter the Name :");
                    accname=sc.next();
                    System.out.println("Enter the email address :");
                    emailadd=sc.next();
                    System.out.println("Enter Account Number :");
                    accNumber=sc.nextLong();
                    System.out.println("Enter the Card Number");
                    cardNo=sc.nextLong();
                    System.out.println("Dear "+ accname +" Thank you,for selecting the Mobile banking");
                case 4:
                    double totalInvestment=0;
                    double ROI,EstimatedReturn,totalValue;
                    int year;
                    System.out.println("Enter your Investments");
                    totalInvestment=sc.nextDouble();
                    System.out.println("Enter the Rate of Interest");
                    ROI=sc.nextDouble();
                    System.out.println("Enter time period in year");
                    year=sc.nextInt();
                    EstimatedReturn=totalInvestment+(totalInvestment*ROI*year/100);
                    totalValue=EstimatedReturn+totalInvestment;
                    System.out.println("Estimated Return "+ EstimatedReturn);
                    System.out.println("Total Return "+totalValue);
                case 5 :
                    exit(0);
            }
        }
    }
}
