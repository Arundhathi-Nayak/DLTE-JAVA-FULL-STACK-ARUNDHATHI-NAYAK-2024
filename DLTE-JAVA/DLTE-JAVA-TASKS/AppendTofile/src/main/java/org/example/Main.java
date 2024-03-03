package org.example;

import java.io.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase myBankDatabase=new MyBankDatabase();
        CreditCard card1= new CreditCard(23567876543L,"Annapoo",new Date(2024,1,20),8674,42000,new Date(2024,03,11),new Date(2024,03,8),1234);
        CreditCard card2=new CreditCard(33567876543L,"Akshira",new Date(2024,12,5),5674,50000,new Date(2024,06,4),new Date(2024,01,11),2234);
        myBankDatabase.create(card1);
        myBankDatabase.create(card2);
        System.out.println("Content of File :");
        displayFileContent();
    }



    public static void displayFileContent() throws IOException, ClassNotFoundException {
        File file=new File("debit.doc");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        CreditCard card=(CreditCard) objectInputStream.readObject();
        System.out.println(card);
        objectInputStream.close();
        fileInputStream.close();

    }
}
