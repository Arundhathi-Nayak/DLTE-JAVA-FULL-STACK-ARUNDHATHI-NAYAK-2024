package org.example;

public class CreditCardActivity implements Activity<CreditCard> {
    public CreditCardActivity(int capacity) {
        this.database = new MyBankDatabase<>(capacity);
    }

    private MyBankDatabase<CreditCard> database;
    public void create(CreditCard card){
        database.create(card);
    }
    public CreditCard read(int index){
        return database.read(index);
    }


    public void update(int index,CreditCard newCard){
        database.update(index,newCard);
    }
    public void delete(int index){
        database.delete(index);
    }
}
