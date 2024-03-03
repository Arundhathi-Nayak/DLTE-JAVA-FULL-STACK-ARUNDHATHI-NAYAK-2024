package org.example;

import java.util.Date;

public class GenericMain {
    public static void main(String[] args) {
        CreditCardActivity creditCardActivity=new CreditCardActivity(10);
        TransactionActivity transactionActivity=new TransactionActivity(10);
         // credit card object
        CreditCard card1= new CreditCard(23567876543L,"Annapoo",new Date(2024,1,20),8674,42000,new Date(2024,03,11),new Date(2024,03,8),1234);
       // CreditCard card2=new CreditCard(33567876543L,"Akshira",new Date(2024,12,5),5674,50000,new Date(2024,06,4),new Date(2024,01,11),2234);

        creditCardActivity.create(card1);
        // creating transaction object

        Transaction transaction1= new Transaction(new Date(2024,12,11),1000,"Aru","Family");
        Transaction transaction2 = new Transaction(new Date(2024,11,8),2000,"Divija","Education");
        transactionActivity.create(transaction1);
        //Reading and printing creditCard and Transaction
        System.out.println("Credit card : "+creditCardActivity.read(0).toString());
        System.out.println("Transaction : "+transactionActivity.read(0).toString());
        // updating creditCard and Transaction

        CreditCard newCard=new CreditCard(33567876543L,"Akshira",new Date(2024,12,5),5674,50000,new Date(2024,06,4),new Date(2024,01,11),2234);
        creditCardActivity.update(0,newCard);
        Transaction newTransaction=  new Transaction(new Date(2024,11,8),2000,"Divija","Education");
        transactionActivity.update(0,newTransaction);
        // reading and printing the creditcard and transaction

        System.out.println("Updated Credit Card : "+creditCardActivity.read(0).toString());
        System.out.println("Updated Transaction : "+transactionActivity.read(0).toString());
        //Deleting credit and transaction
        creditCardActivity.delete(0);
        transactionActivity.delete(0);
        //Reading and printing after deletion
        System.out.println("After deletion, Credit Card: "+creditCardActivity.read(0));
        System.out.println("After deletion Transaction: "+transactionActivity.read(0));


    }
}
