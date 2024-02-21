package explore.oop.InternetBanking;

import java.util.Scanner;

public class MobileBanking {
    public static void main(String[] args) {
        Gpay gpay=new Gpay(123456,1000,"Arundhathi","1234-5678-1234-5643","1234");

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter The amount for withdrawal :");
        double withdrawAmount=scanner.nextDouble();
        System.out.println("Enter The pin for withdrawal :");
        String upiPin=scanner.next();
        System.out.println("Enter The Bill name for payment :");
        String billName=scanner.next();
        System.out.println("Enter the amount to payment of bill :");
        double billAmount=scanner.nextDouble();
        System.out.println("Enter the type of bill :");
        String billerType=scanner.next();
        if(gpay.validatePin(upiPin)) {
            if (gpay.approveWithdraw(withdrawAmount)) {
                System.out.println("Withdraw approved,Remaining Balance: " + (gpay.getAccountBalance() - withdrawAmount));
            } else {
                System.out.println("Insufficient funds to Withdrawal.");
            }
        }
        else {
            System.out.println("Invalid Pin, Cannot Withdraw");
        }

    //    String uipPin="1234";
        gpay.payBill(billName,billAmount,billerType,upiPin);
    }
}
