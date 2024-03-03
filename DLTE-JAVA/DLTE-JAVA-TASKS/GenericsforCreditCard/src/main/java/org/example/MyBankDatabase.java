package org.example;
// defining the generic class MyBankDatabase to store objects
public class MyBankDatabase<T> {
    private Object[] database;
    private int size;
    public MyBankDatabase(int capacity){
        this.database=new Object[capacity];
        this.size=0;
    }
    public void create(T item){
        if(size<database.length){
            database[size++]=item;
        }else{
            System.out.println("databse is full");
        }
    }
    public T read(int index){
        if(index>=0 && index<size){
            T item=(T) database[index];
            return item;
        }else {
            System.out.println("Invalid access");
            return null;
        }
    }
    public void update(int index, T newItem){
        if(index>=0 && index<size){
            database[index]=newItem;
        }else{
            System.out.println("Cannot update");
        }
    }
    public void delete(int index){
        if(index>=0 && index<size){
            for(int select=index;select<size-1;select++){
                database[select]=database[select+1];
            }
            database[size-1]=null;
            size--;
        }else{
            System.out.println("Cannot delete");
        }
    }
}
