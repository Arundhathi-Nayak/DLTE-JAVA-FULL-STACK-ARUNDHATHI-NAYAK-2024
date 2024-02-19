package branches.blocks;

import java.util.Scanner;

import static java.lang.System.exit;

public class Income {
    public static void main(String[] args) {
        int ch;
        double salary, taxAmount,taxpercent=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your Income: ");
        salary =sc.nextDouble();
        System.out.println("Enter your choice of Regime,");
        System.out.println("1.OldRegime");
        System.out.println("2.NewRegime");
        ch=sc.nextInt();
        switch(ch){
            case 1 :
                if(salary>=0 && salary<=250000){
                    System.out.println("You need not to pay any tax:Exempted");
                }
                if(salary>250000 && salary<=500000){
                    taxpercent=0.05;
                }
                else if(salary>500000 && salary<=750000){
                    taxpercent=0.20;
                }
                else if(salary>750000 && salary<=1000000){
                    taxpercent=0.20;
                }
                else if(salary>1000000 && salary<=1250000){
                    taxpercent=0.30;
                }
                else if(salary>1250000 && salary<=1500000){
                    taxpercent=0.30;
                }
                else{
                    taxpercent=0.30;
                }
                break;
            case 2:
                if(salary>=0 && salary<=250000){
                    System.out.println("You need not to pay any tax:Exempted");
                }
                if(salary>250000 && salary<=500000){
                    taxpercent=0.05;
                }
                else if(salary>500000 && salary<=750000){
                    taxpercent=0.10;
                }
                else if(salary>750000 && salary<=1000000){
                    taxpercent=0.15;
                }
                else if(salary>1000000 && salary<=1250000){
                    taxpercent=0.20;
                }
                else if(salary>1250000 && salary<=1500000){
                    taxpercent=0.25;
                }
                else{
                    taxpercent=0.30;
                }
                break;
            default:exit(0);
        }
        taxAmount=salary*taxpercent;
        System.out.println("Tax to be paid is "+taxAmount+" that is "+taxpercent+" %");
    }
}


