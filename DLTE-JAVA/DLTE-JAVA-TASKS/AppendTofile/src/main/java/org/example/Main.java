package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        MyBankDatabase myBankDatabase=new MyBankDatabase();
        CreditCard card1= new CreditCard(23567876543L,"Annapoo",new Date(2024,1,20),8674,42000,new Date(2024,03,11),new Date(2024,03,8),1234);
        CreditCard card2=new CreditCard(33567876543L,"Akshira",new Date(2024,12,5),5674,50000,new Date(2024,06,4),new Date(2024,01,11),2234);
        CreditCard card3=new CreditCard(43567876543L,"Eeksha",new Date(2024,12,5),5674,50000,new Date(2024,06,4),new Date(2024,01,11),2234);
        myBankDatabase.create(card1);
        myBankDatabase.create(card2);
        myBankDatabase.create(card3);
        System.out.println("Content of File :");
        displayFileContent();
    }
    public static void displayFileContent() throws IOException, ClassNotFoundException {
        File file=new File("debit.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ArrayList<CreditCard> card= (ArrayList<CreditCard>) objectInputStream.readObject();
        card.forEach(System.out::println);
        objectInputStream.close();
        fileInputStream.close();

    }
}

