package branches.blocks;

import java.util.Scanner;

public class IncomeTax {
    public static void main(String[] args) {
        double salary;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the salary : ");
        salary=sc.nextInt();
        if(salary>0 && salary<=250000){
            System.out.println("Existing tax rates is Exempt");
            System.out.println("New tax rates is Exempt");
        }
        else if(salary>250000 && salary<=500000){
            System.out.println("Existing tax rates is "+0.05*salary);
            System.out.println("New tax rates is "+0.05*salary);
        }
        else if(salary>500000 && salary<=750000){
            System.out.println("Existing tax rates is "+0.20*salary);
            System.out.println("New tax rates is "+0.10*salary);
        }
        else if(salary>750000 && salary<=1000000){
            System.out.println("Existing tax rates is "+0.20*salary);
            System.out.println("New tax rates is "+0.15*salary);
        }
        else if(salary>1000000 && salary<=1250000){
            System.out.println("Existing tax rates is "+0.30*salary);
            System.out.println("New tax rates is "+0.20*salary);
        }
        else if(salary>1250000 && salary<=1500000){
            System.out.println("Existing tax rates is "+0.30*salary);
            System.out.println("New tax rates is "+0.25*salary);
        }
        else {
            System.out.println("Existing tax rates is "+0.30*salary);
            System.out.println("New tax rates is "+0.30*salary);
        }

    }
}
