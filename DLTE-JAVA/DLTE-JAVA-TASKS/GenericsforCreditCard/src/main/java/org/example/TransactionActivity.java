package org.example;

public class TransactionActivity implements Activity<Transaction> {
    private MyBankDatabase<Transaction> database;
    public TransactionActivity(int capacity){
        this.database=new MyBankDatabase<>(capacity);
    }
    @Override
    public void create(Transaction index) {
        database.create(index);
    }

    @Override
    public Transaction read(int index) {
        return database.read(index);
    }

    @Override
    public void update(int index, Transaction newItem) {
          database.update(index,newItem);
    }

    @Override
    public void delete(int index) {
         database.delete(index);
    }
}
