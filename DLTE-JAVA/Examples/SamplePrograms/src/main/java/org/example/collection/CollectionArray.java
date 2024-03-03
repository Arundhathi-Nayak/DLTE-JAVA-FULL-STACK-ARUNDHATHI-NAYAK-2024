package org.example.collection;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;


public class CollectionArray {

        public static void main(String args[]){
            ArrayList<String> list=new ArrayList<>();//Creating arraylist
            list.add("Ravi");//Adding object in arraylist
            list.add("Vijay");
            list.add("Ravi");
            list.add("Ajay");
//Traversing list through Iterator
//            Iterator itr=list.iterator();
//            while(itr.hasNext()){
//                System.out.println(itr.next());
//            }
            list.forEach((i)-> System.out.println(i));
        }

}
