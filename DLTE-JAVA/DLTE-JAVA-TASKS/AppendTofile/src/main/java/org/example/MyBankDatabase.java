package org.example;

import java.io.*;
import java.util.ArrayList;

public class MyBankDatabase implements Activity<CreditCard> {
    private ArrayList<CreditCard> database;
    public MyBankDatabase(){
        this.database=new ArrayList<>();
    }
    @Override
    public void create(CreditCard item) throws IOException {
        database.add(item);
        writeToFile(item);
    }

    private void writeToFile(CreditCard card) throws IOException {
        File file=new File("debit.doc");
        FileOutputStream fileOutputStream=new FileOutputStream(file,true);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
//        for(CreditCard card: database){
//
//        objectOutputStream.writeObject(card);
//        }
        objectOutputStream.writeObject(card);
        objectOutputStream.close();
        fileOutputStream.close();

        }

}
