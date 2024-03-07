package org.example;

import java.util.Date;

import static org.example.MyBank.loans;

public class MyBankMain {

    public static void main(String[] args) {
        loans.add(new Loan(123,3655,new Date("2/1/2024"),"Open","Aru","13456789"));
        loans.add(new Loan(153,345,new Date("3/1/2024"),"Open","Eekshaa","23456789"));
        loans.add(new Loan(223,645,new Date("2/5/2024"),"Open","Annapoo","33456789"));
        MyBank myBank=((start,end)->{
            for(Loan loan:loans){
                if(loan.getLoanDate().before(end) && loan.getLoanDate().after(start)){
                    System.out.println(loan);
                }
            }
        });
        myBank.filterBasedOnDate(new Date("1/1/2024"),new Date("3/3/2024"));
    }

//myBank.filterBasedDates(new Date("2/13/2024"),new Date("3/25/2024"));

}
