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
        writeToFile(database);
    }

    private void writeToFile(ArrayList<CreditCard> card) throws IOException {
        File file=new File("debit.txt");
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(card);
        objectOutputStream.close();
        fileOutputStream.close();

        }

}
