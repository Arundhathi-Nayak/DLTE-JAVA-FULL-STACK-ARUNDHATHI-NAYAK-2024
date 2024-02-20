package store.oops;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //annotation offered by lombok for setter and getter
@AllArgsConstructor
@NoArgsConstructor
public class Bonds {

        private Integer maturity;
        private  Double interestRate;
        private String taxStatus;
        private Integer period;
        private String bondHolder;

    public static void main(String[] args) {
        Bonds Private_bond=new Bonds(5,5.5,"Paid",5,"Avinash");
        Bonds Zero_coupon_bond=new Bonds(6,6.5,"No Tax",4,"Annaporna");
        Bonds Corporate_bond=new Bonds(4,7.5,"Pending",3,"Aru");
        Bonds Municipal_bond=new Bonds(5,2.5,"Paid",5,"Akshira");
        Bonds Climate_bond=new Bonds(5,5.5,"No Tax",5,"Eeskha");
        Bonds bonds[]={Private_bond,Zero_coupon_bond,Corporate_bond,Municipal_bond,Climate_bond};
        int index=MaximunReturn(bonds);

    }
    public static int MaximunReturn(Bonds[] bonds){
        double maximunIntrest=0;
        int pos=0,count=0;
        for(Bonds each:bonds){
            count++;
            if(each.getInterestRate()>maximunIntrest){
                maximunIntrest=each.getInterestRate();
                pos=count;
            }
        }
        System.out.println("maximun interest Rate is "+maximunIntrest);
        return pos;
    }


}
