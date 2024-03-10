package org.example;

import java.util.Date;
import java.util.Scanner;

public class MultiThreadMain implements Runnable{
    Transaction transactions[] =
            {
                    new Transaction(new Date(2024, 05, 26), 3000, "Aru", "Friend"),
                    new Transaction(new Date(2024, 04, 18), 1500, "Annapoo", "Education"),
                    new Transaction(new Date(2024, 03, 04), 6000, "Akshatha", "Bill"),
                    new Transaction(new Date(2024, 02, 25), 100, "Divija", "Friend")

            };
    public  void run(){
        System.out.println("-------------------------------Welcome-------------------------");
        System.out.println("****Menu*****");
        Scanner scanner = new Scanner(System.in);
        MultiThreadMain multiThread=new MultiThreadMain();
        while (true) {
            System.out.println("---------Welcome to transaction Section---------");
            System.out.println("1.Filter transaction by the range of Dates");
            System.out.println("2.Find Transaction with least Amount");
            System.out.println("3.Find Transaction with Maximum Amount");
            System.out.println("4.Get the number of Transaction made to a particular Beneficiary");
            System.out.println("5.Filter Transaction Based on a particular remarks");
            System.out.println("6.Display by Amount");
            System.out.println("7.Display by beneficiary Name");
            System.out.println("8.Display by Details");
            System.out.println("9.Exit");
            System.out.println("Enter Your Choice :");
            int choice=scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the start Date");
                    int startDate = scanner.nextInt();
                    System.out.println("Enter the to end date");
                    int endDate = scanner.nextInt();
                    multiThread.filterByDate(transactions, startDate, endDate);
                    break;
                case 2:
                    multiThread.minimumAmount(transactions);
                    break;
                case 3:
                    multiThread.maximumAmount(transactions);
                    break;
                case 4:
                    System.out.println("Enter the name of Beneficiary");
                    String name = scanner.nextLine();
                    multiThread.totalTransaction(transactions, name);
                    break;
                case 5:
                    System.out.println("Enter the remark(Family, Education, Emergency, Bills, Friend)");
                    String remark = scanner.nextLine();
                    for (Transaction each : transactions) {
                        if (each.getTransactionRemarks().equals(remark)) {
                            System.out.println(each.getTransactionTo() + " " + each.getTransactionAmount() + " " + each.getTransactionDate());

                        }
                    }
                    break;
                case 6:multiThread.displayTheAmount();
                    break;
                case 7:multiThread.displayByBeneficiaryNames();
                break;
                case 8:multiThread.displayTheDetails();
                break;
                default:
                    System.exit(0);
            }
        }

    }

    public void totalTransaction (Transaction[] transaction, String name){
        int transactioncount = 0;
        for (Transaction each : transaction) {
            if (each.getTransactionTo().equals(name))
                transactioncount++;
        }
        System.out.println("Number of Transaction made to " + name + " is " + transactioncount);
    }

    public void filterByDate (Transaction[] transaction,int startDate, int endDate){
        System.out.println("Transaction between the given dates " + startDate + " and " + endDate);

        for (Transaction each : transaction) {
            if (each.getTransactionDate().getDate() >= startDate && each.getTransactionDate().getDate() <= endDate) {
                System.out.println(each.getTransactionTo() + " " + each.getTransactionAmount());
            }
        }
    }


    public void minimumAmount (Transaction[] transaction){
        int amount = transaction[0].getTransactionAmount();
        for (Transaction each : transaction) {
            if (amount > each.getTransactionAmount())
                amount = each.getTransactionAmount();
        }
        System.out.println(" Minimum Amount :");
        for (Transaction each : transaction) {
            if (each.getTransactionAmount() == amount)
                System.out.println(each.getTransactionTo() + " " + amount);
        }
    }

    public void maximumAmount (Transaction[] transaction){
        int amount = transaction[0].getTransactionAmount();
        for (Transaction each : transaction) {
            if (amount < each.getTransactionAmount())
                amount = each.getTransactionAmount();
        }
        System.out.println("Maximum Amount :");
        for (Transaction each : transaction) {
            if (each.getTransactionAmount() == amount)
                System.out.println(each.getTransactionTo() + " " + amount);
        }
    }




    public void displayTheAmount() {
        System.out.println("Transaction Amount");
        for (Transaction each: transactions)
            System.out.println(each.getTransactionAmount());
        System.out.println();
    }

    public void displayByBeneficiaryNames() {
        System.out.println("Beneficiary Names");
        for (Transaction each: transactions)
            System.out.println(each.getTransactionTo());
        System.out.println();
    }

    public void displayTheDetails() {
        System.out.println("Details:");
        for (Transaction each: transactions)
            System.out.println(each.getTransactionTo()+" "+each.getTransactionDate()+" "+each.getTransactionAmount());

    }
}
