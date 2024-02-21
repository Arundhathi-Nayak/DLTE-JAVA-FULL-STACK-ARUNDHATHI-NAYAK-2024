package explore.oop.InternetBanking;

public class MobileBanking {
    public static void main(String[] args) {
        Gpay gpay=new Gpay(123456,1000,"Arundhathi","1234-5678-1234-5643","1234");
        double withdrawAmount=500.0;
        if(gpay.approveWithdraw(withdrawAmount)){
            System.out.println("Withdraw approved,Remaining Balance: "+(gpay.getAccountBalance()-withdrawAmount));
        }else{
            System.out.println("Insufficient funds to Withdrawal.");
        }

        String billName="Electricity";
        double billAmount=100.0;
        String billerType="Utility";
        String uipPin="1234";
        gpay.payBill(billName,billAmount,billerType,uipPin);
    }
}
