package explore.oop.loan;

import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.System.setOut;

public class BankApp implements MyBank{
    private Loan[] loans;
    private int loanCount;
     public BankApp(){
         this.loans=new Loan[1];
         this.loanCount=0;
     }

    public static void main(String[] args) {
        BankApp bank=new BankApp();
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
                    bank.addNewLoan(getLoanFromUser(scanner));
                    break;
                case 2:
                    displayloans("Open Loans ", bank.checkAvailableLoans());
                    break;
                case 3:
                    displayloans("Closed loans ", bank.checkClosedLoans());
                    break;
                case 4:
                    exit(0);
            }
        }while(choice!=4);
     scanner.close();
    }
    private static Loan getLoanFromUser(Scanner scanner){
        System.out.println("Enter the loan number: ");
        int loanNumber=scanner.nextInt();
        System.out.println("Enter the Loan amount:");
        double loanAmount=scanner.nextDouble();
        System.out.println("Enter the Loan date: ");
        String loanDate=scanner.next();
        System.out.println("Enter the Loan Status: ");
        String loanStatus=scanner.next();
        System.out.println("Enter the borrower name:");
        String borrowerName=scanner.next();
        System.out.println("Enter the Borrower Contact:");
        String borrowerContact=scanner.next();
        return new Loan(loanNumber,loanAmount,loanDate,loanStatus,borrowerName,borrowerContact);
    }

    public void addNewLoan(Loan loan){
         if(loanCount<loans.length){
             loans[loanCount++]=loan;
             System.out.println("New Loan added successfully.");
         }else{
             System.out.println("Maximum Loan limit reached,cannot add new loans");
        }
    }
    public Loan[] checkAvailableLoans(){
         int availableCount=0;
         for(Loan loan:loans){
             if(loan!=null&& loan.getLoanStatus().equalsIgnoreCase("Open")){
                 availableCount++;
             }
         }
         Loan[] availableLoans=new Loan[availableCount];
         int index=0;
         for(Loan each:loans){
             if(each!=null && each.getLoanStatus().equalsIgnoreCase("Open")){
                 availableLoans[index++]=each;
             }
         }
         return availableLoans;
    }
    public Loan[] checkClosedLoans(){
        int closedCount=0;
        for(Loan loan:loans){
            if(loan!=null&& loan.getLoanStatus().equalsIgnoreCase("Closed")){
                closedCount++;
            }
        }
        Loan[] closedLoans=new Loan[closedCount];
        int index=0;
        for(Loan each:loans){
            if(each!=null && each.getLoanStatus().equalsIgnoreCase("Closed")){
                closedLoans[index++]=each;
            }
        }
        return closedLoans;
    }

    private static void displayloans(String title,Loan[] loanArray){
        System.out.println("\n"+title+":");
        if(loanArray.length>0){
            for(Loan each:loanArray){
                System.out.println(each);
            }
        }else{
            System.out.println("No Loans Found");
        }
    }

}
