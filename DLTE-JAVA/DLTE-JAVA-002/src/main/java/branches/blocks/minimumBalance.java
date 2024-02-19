package branches.blocks;

import java.util.Arrays;
import java.util.Scanner;

public class minimumBalance {
    public static void main(String[] args) {
        double[] balance=new double[4];
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<balance.length;i++) {
            System.out.println("Enter the account balance Customer:");
            balance[i] = sc.nextDouble();
        }
        System.out.println(Arrays.toString(balance));
        minimumBalance.updatebalance(balance);
        System.out.println(Arrays.toString(balance));
    }
    public static void updatebalance(double[] minBalance){
        double charges;
        for(int i=0;i<minBalance.length;i++){
            if(minBalance[i]>=5000 & minBalance[i]<=9999){
                charges=0.03*minBalance[i];
                minBalance[i]=minBalance[i]-charges;
            }
            else if(minBalance[i]>=1000 & minBalance[i]<=4999){
                charges=0.05*minBalance[i];
                minBalance[i]=minBalance[i]-charges;
            }
        }
        return;
    }
}
