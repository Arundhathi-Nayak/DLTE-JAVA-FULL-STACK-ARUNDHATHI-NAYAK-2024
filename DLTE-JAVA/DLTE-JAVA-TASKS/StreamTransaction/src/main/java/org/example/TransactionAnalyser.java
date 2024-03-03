package org.example;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class TransactionAnalyser {
    private List<Transaction> transactions;
    public TransactionAnalyser(){
        this.transactions=new ArrayList<>();
    }
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }
    public List<Transaction> filterByDateRange(String startDate, String endDate){
        return transactions.stream().filter(transaction->transaction.getTransactionDate().compareTo(startDate)>=0&&transaction.getTransactionDate().compareTo(endDate)<=0).collect(Collectors.toList());
    }
    public Transaction findMinimumAmountTransaction(){
        return transactions.stream().min(Comparator.comparing(Transaction::getTransactionAmount)).orElse(null);
    }
    public Transaction findMaximumAmountTransaction(){
        return transactions.stream().max(Comparator.comparing(Transaction::getTransactionAmount)).orElse(null);
    }
    public List<Transaction> sortTransaction(Comparator<Transaction> comparator){
        return transactions.stream().sorted(comparator).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        TransactionAnalyser analyser=new TransactionAnalyser();
        analyser.addTransaction(new Transaction("2024-12-11",1000,"Aru","Family"));
        analyser.addTransaction( new Transaction("2024-11-08",2000,"Divija","Education"));
        analyser.addTransaction(new Transaction("2024-08-06",1500,"Akshira","Emergency"));
        analyser.addTransaction(new Transaction("2024-05-06",1200,"Eeksha","Bills"));
         analyser.addTransaction(new Transaction("2024-12-01",9000,"Avinash","Bills"));
        Scanner scanner=new Scanner(System.in);
        int choice;
        do{

            System.out.println("---------Welcome to transaction Section---------");
            System.out.println("1.Filter transaction by the range of Dates");
            System.out.println("2.Find Transaction with least Amount");
            System.out.println("3.Find Transaction with Maximum Amount");
            System.out.println("4.Sort the Transaction");
            System.out.println("5.Exit");
            System.out.println("Enter Your Choice :");
            choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter the start date for filtering(YYYY-MM-DD)");
                    String startDate=scanner.nextLine();
                    System.out.println("Enter the end date for filtering(YYYY-MM-DD)");
                    String endDate=scanner.nextLine();
                    List<Transaction> filterTransaction=analyser.filterByDateRange(startDate,endDate);
                    System.out.println("Filtered Transaction:");
                    filterTransaction.forEach(System.out::println);
                    break;
                case 2: Transaction minTransaction=analyser.findMinimumAmountTransaction();
                    System.out.println("Minimum amount Transaction: "+minTransaction);break;
                case 3:Transaction maxTransaction=analyser.findMaximumAmountTransaction();
                    System.out.println("Maximum amount Transaction: "+maxTransaction);break;
                case 4:
                    System.out.println("Enter the property to sort(date,amount,to,remarks):");
                    String property=scanner.nextLine();
                    System.out.println("Enter the order(asc/des):");
                    String sortOrder=scanner.nextLine();
                    Comparator<Transaction> comparator=null;
                    switch (property){
                        case "date": case "Date":comparator=Comparator.comparing(Transaction::getTransactionDate);
                                       break;
                        case "Amount":case "amount" : comparator=Comparator.comparing(Transaction::getTransactionAmount);
                        break;
                        case "to": case "To":comparator=Comparator.comparing(Transaction::getTransactionTo);
                        break;
                        case "Remarks": case "remarks":comparator=Comparator.comparing(Transaction::getTransactionRemarks);
                        break;
                        default:System.out.println("Invalid property specified for sorting.");
                        break;
                    }
                    if(comparator!=null){
                        if(sortOrder.equalsIgnoreCase("desc")){
                            comparator=comparator.reversed();
                        }
                        List<Transaction> sortTransaction=analyser.sortTransaction(comparator);
                        System.out.println("Sorted Transaction:");
                        sortTransaction.forEach(System.out::println);
                    }
                    break;
                case 5:exit(0);
                default:
                    System.out.println("Invalid choice");
            }

        }while (choice!=5);
    }
}
