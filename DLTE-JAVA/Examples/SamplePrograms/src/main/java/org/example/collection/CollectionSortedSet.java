package org.example.collection;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class CollectionSortedSet {
    public static void main(String[] args) {

        SortedSet set = new TreeSet();

        set.add("Bob");

        set.add("Sean");

        set.add("Jennifer");

        Iterator i = set.iterator();

        while (i.hasNext()) {

            Object element = i.next();

            System.out.println(element.toString());

        }

    }
}
