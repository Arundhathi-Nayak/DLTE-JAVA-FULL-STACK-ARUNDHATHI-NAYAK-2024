package store.oops;

import java.util.Date;
import java.util.Scanner;

import static java.lang.System.exit;

public class TransactionSupport {


    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Transaction[] transactions={
                new Transaction(new Date(2024,12,11),1000,"Aru","Family"),
                new Transaction(new Date(2024,11,8),2000,"Divija","Education"),
                new Transaction(new Date(2024,8,6),1500,"Akshira","Emergency"),
                new Transaction(new Date(2024,5,6),1200,"Eeksha","Bills"),
                new Transaction(new Date(2024,12,1),9000,"Avinash","Bills"),
        };


            System.out.println("---------Welcome to transaction Section---------");
            System.out.println("1.Filter transaction by the range of Dates");
            System.out.println("2.Find Transaction with least Amount");
            System.out.println("3.Find Transaction with Maximum Amount");
            System.out.println("4.Get the number of Transaction made to a particular Beneficiary");
            System.out.println("5.Filter Transaction Based on a particular remarks");
            System.out.println("6.Sort the Transaction based on beneficiary in descending order");
            System.out.println("7.Sort Transaction Based on amount in Ascending order");
            System.out.println("8.Exit");
            System.out.println("Enter Your Choice :");
            int choice=scanner.nextInt();
            TransactionSupport support=new TransactionSupport();
            switch (choice){
                case 1:support.filterOnRangeOfDates(transactions);
                       break;
                case 2:support.leastTransactionAmount(transactions);
                        break;
                case 3:support.maximunTransactionAmount(transactions);
                       break;
                case 4:support.countTransactionToBeneficiary(transactions);
                    break;
                case 5:support.filterByRemarks(transactions);
                    break;
                case 6:support.sortByDescendingBeneficiary(transactions);
                       break;
                case 7:support.sortByAscendingAmounts(transactions);
                       break;
                case 8:exit(0);

            }
        }

    public void filterOnRangeOfDates(Transaction[] customer){
        String StartRange,EndRange;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Start Range of Dates in format(dd/mm/yy1" +
                ")");
        StartRange=scanner.next();
//        System.out.println("Enter the End Range of Dates in format(DD/MM/YYYY)");
//        EndRange=scanner.next();
        String splitDate[]= StartRange.split("/");
        //String endDate[]=EndRange.split("/");
        for (Transaction each:customer) {
            if(Integer.parseInt(splitDate[0])==(each.getTransactionDate().getDate())&& Integer.parseInt(splitDate[1])==(each.getTransactionDate().getMonth())&&Integer.parseInt(splitDate[2])==(each.getTransactionDate().getYear())){

                    System.out.println("Date: " + each.getTransactionDate() + ", Amount: " + each.getTransactionAmount() + ", To: " + each.getTransactionTo() + ", Remarks: " + each.getTransactionRemarks());
                }
            }
        }
        public void leastTransactionAmount(Transaction[] customer){
        Transaction minTransaction = customer[0];
        for(int index=0;index<customer.length;index++){
            if(customer[index]!=null && customer[index].getTransactionAmount()<=minTransaction.getTransactionAmount()){
                minTransaction=customer[index];
            }

        }
            System.out.println("Transaction with Least amount of Transfer");
            displayTransactionDetails(minTransaction);
        }
    public void maximunTransactionAmount(Transaction[] customer){
        Transaction maxTransaction = customer[0];
        for(int index=0;index<customer.length;index++){
            if(customer[index]!=null && customer[index].getTransactionAmount()>=maxTransaction.getTransactionAmount()){
                maxTransaction=customer[index];
            }

        }
        System.out.println("Transaction with Least amount of Transfer");
        displayTransactionDetails(maxTransaction);
    }
    public void countTransactionToBeneficiary(Transaction[] customer){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter Beneficiary name:");
        String beneficiary=scanner.next();
        int count=0;
        for(int index=0;index< customer.length;index++){
            if(customer[index]!=null && customer[index].getTransactionTo().equalsIgnoreCase(beneficiary)) {
                count++;
            }
        }
        System.out.println("Number of Transaction Made is "+beneficiary+" : "+count);

    }
    public void filterByRemarks(Transaction[] customer){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter the Remarks :");
        String remarks=scanner.next();
        for(Transaction each:customer){
            if(each!=null && each.getTransactionRemarks().equalsIgnoreCase(remarks)){
                displayTransactionDetails(each);
            }

        }
    }
    public void sortByDescendingBeneficiary(Transaction[] customer){
        for(int select=0;select<customer.length-1;select++) {
            for (int next = 0; next < customer.length - select -1;next++) {
                if (customer[next].getTransactionTo().compareTo(customer[next + 1].getTransactionTo()) < 0) {
                    Transaction backup = customer[select];
                    customer[select] = customer[next];
                    customer[next] = backup;
                }

            }
        }
        for(Transaction each:customer){
            System.out.println(each.getTransactionTo());
            System.out.println(each.getTransactionTo());
        }

    }
    public void sortByAscendingAmounts(Transaction[] customer){
        for(int select=0;select<customer.length-1;select++) {
            for (int next = 0; next < customer.length - select -1;next++) {
                if (customer[next].getTransactionAmount().compareTo(customer[next + 1].getTransactionAmount()) > 0) {
                    Transaction backup = customer[select];
                    customer[select] = customer[next];
                    customer[next] = backup;
                }
            }
        }
        for(Transaction each:customer){
            System.out.println(each.getTransactionAmount());
        }
    }


        public static void displayTransactionDetails(Transaction customer){
            System.out.println("Date: " + customer.getTransactionDate() + ", Amount: " + customer.getTransactionAmount() + ", To: " + customer.getTransactionTo() + ", Remarks: " + customer.getTransactionRemarks());
        }

}
