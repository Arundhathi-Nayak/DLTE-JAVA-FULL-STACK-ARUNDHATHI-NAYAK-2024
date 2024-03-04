package org.example.collection;

import java.util.Iterator;
import java.util.LinkedList;

public class CollectionLinkedList {
    public static void main(String args[]){
        LinkedList<String> al=new LinkedList<String>();
        al.add("Ravi");
        al.add("Vijay");
        al.add("Ravi");
        al.add("Ajay");
        //Iterator<String> itr=al.iterator(); // one more ListIterator
        al.forEach((i)-> System.out.println(i));

    }
}
