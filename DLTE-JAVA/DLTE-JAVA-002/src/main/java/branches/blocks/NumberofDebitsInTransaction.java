package branches.blocks;

import java.util.Scanner;

public class NumberofDebitsInTransaction {
    public static void main(String[] args) {
        int debitCount=0;
        double currentBalance=0,newBalance=0;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the current Balance : ");
        currentBalance=sc.nextDouble();
        for(int i=0;i<10;i++){
            System.out.println("Enter the current Balance after "+i+" transaction");
            newBalance=sc.nextDouble();
            if(newBalance<currentBalance){
                debitCount++;
            }
            currentBalance=newBalance;
        }
        System.out.println("Total number of transaction are "+debitCount);
        sc.close();
    }
}
