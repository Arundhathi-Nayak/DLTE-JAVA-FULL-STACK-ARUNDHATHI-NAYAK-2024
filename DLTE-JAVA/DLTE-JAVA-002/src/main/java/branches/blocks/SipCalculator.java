package branches.blocks;

import java.util.Scanner;

public class SipCalculator {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        double principalAmount,estimateReturn=0,total=0;
        // input
        System.out.println("Enter the monthly investment amount: ");
        double monthlyInvestment=sc.nextDouble();
        System.out.println("Enter the expected annual return(%): ");
        double annualReturn=sc.nextDouble();
        double annualreturn=annualReturn/100;
        System.out.println("Enter investment period in year: ");
        int years=sc.nextInt();
        //calculations
        principalAmount=monthlyInvestment*12*years;

        double monthlyRate=annualReturn/12/100;
        double numMonths=12*years;
        total=monthlyInvestment*((Math.pow((1+monthlyRate),(numMonths))-1)*(1+monthlyRate))/monthlyRate;
        double totalmoneyInvested=numMonths*monthlyInvestment;
        estimateReturn=total-totalmoneyInvested;
        //output
        System.out.println("Principal Amount: "+principalAmount);
        System.out.println("Estimate Return: "+estimateReturn);
        System.out.println("Total: "+total);
        sc.close();

    }
}
