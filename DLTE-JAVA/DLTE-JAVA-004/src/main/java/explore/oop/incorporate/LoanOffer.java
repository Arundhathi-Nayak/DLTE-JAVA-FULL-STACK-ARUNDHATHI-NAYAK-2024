package explore.oop.incorporate;

public class LoanOffer extends TransactionData{
    void view(){
        for(Transaction each:transactions){
            System.out.println(each);
        }
    }
    public void personalLoan(Customer customer){
        int count=0;
        for(int index=0;index<transactions.length;index++){
            if(transactions[index].getSender()==customer&& transactions[index].getAmount()<=10000){

            }
        }
    }
    public static void main(String[] args) {
        LoanOffer loanOffer=new LoanOffer();
        loanOffer.view();
        loanOffer.personalLoan(loanOffer.customer2);
    }

}
class TransactionData{
    public  Transaction[] transactions=new Transaction[4];
    Customer customer1= new Customer("Aru",4567,1234);
    Customer customer2= new Customer("Aru",3567,1334);
    Customer customer3= new Customer("Aru",4567,1434);
    public TransactionData(){
        transactions[0]=new Transaction(customer2,200,customer3,"Credit");
        transactions[1]=new Transaction(customer3,2200,customer2,"Debit");
        transactions[2]=new Transaction(customer1,500,customer2,"Credit");
        transactions[3]=new Transaction(customer2,600,customer3,"Debit");
    }

}
