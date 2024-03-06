package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class BankApp implements MyBank {

    public List<Loan> loans=new ArrayList<>();
    Scanner scanner=new Scanner(System.in);
    Scanner scanner1=new Scanner(System.in);
    static BankApp myBank=new BankApp();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner scanner=new Scanner(System.in);
        int choice;
        do {
            System.out.println("--------Loan Application-------");
            System.out.println("1.Add new Loan");
            System.out.println("2.Check Available Loans");
            System.out.println("3. Check Closed Loans");
            System.out.println("4.Exit");
            System.out.println("Enter your Choice :");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    myBank.addNewLoan();
                    break;
                case 2:
                    myBank.read();
                    break;
                case 3:
                    //displayloans("Closed loans ", bank.checkClosedLoans());
                    break;
                case 4:
                    exit(0);
            }
        }while(choice!=4);
        scanner.close();
    }


    @Override
    public void addNewLoan() throws IOException, ClassNotFoundException {
        Loan loan=new Loan();
        List<Loan> loanslist=new ArrayList<>();
        System.out.println("Enter the loan number: ");
        loan.setLoanNumber(scanner.nextInt());
        System.out.println("Enter the Loan amount:");
        loan.setLoanAmount(scanner.nextDouble());
        System.out.println("Enter the Loan date: ");
        loan.setLoanDate(scanner.next());
        System.out.println("Enter the Loan Status: ");
        loan.setLoanStatus(scanner.next());
        System.out.println("Enter the borrower name:");
        loan.setBorrowerName(scanner.next());
        System.out.println("Enter the Borrower Contact:");
        loan.setBorrowerContact(scanner.next());
        loanslist.add(loan);//add new data into existing file
        myBank.create(loanslist);

    }
    public void create(List<Loan> loanList) throws IOException, ClassNotFoundException {
        File file=new File("OutputFile.txt");
        if(file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            loans= (List<Loan>) objectInputStream.readObject();//re read file
            loans.addAll(loanList);//add new data into existing file
        }else{
            loans=loanList;
        }
        FileOutputStream fileOutputStream=new FileOutputStream("OutputFile.txt");
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(loans);
        objectOutputStream.close();
        fileOutputStream.close();
    }
    public void read()  throws IOException, ClassNotFoundException {

        FileInputStream fileInputStream=new FileInputStream("OutputFile.txt");
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        List<Loan> loanCard= (List<Loan>) objectInputStream.readObject();
        loanCard.forEach(System.out::println);
//        CreditCard creditCard1=(CreditCard) objectInputStream.readObject();
//        System.out.println(creditCard1);
        objectInputStream.close();
        fileInputStream.close();
    }
    @Override
    public Loan[] checkAvailableLoans() {
        return new Loan[0];
    }

    @Override
    public Loan[] checkClosedLoans() {
        return new Loan[0];
    }
}
