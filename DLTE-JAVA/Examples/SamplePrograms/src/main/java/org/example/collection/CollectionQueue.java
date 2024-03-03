package org.example.collection;

import java.util.LinkedList;
import java.util.Queue;

public class CollectionQueue {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();

        queue.add("Apple");

        queue.add("Mango");

        queue.add("Grapes");

        queue.add("Banana");

        System.out.println(queue);

        queue.remove("Grapes");

        System.out.println(queue);

        System.out.println("Queue total Size: " + queue.size());



    }

}
