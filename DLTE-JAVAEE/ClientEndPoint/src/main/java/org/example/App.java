package org.example;

import soap.MySource;
import soap.MySourceService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MySourceService service=new MySourceService();
        MySource source=service.getMySourcePort();
        String acknowledge = source.addDefaulter("Sinchana");
        System.out.println(acknowledge);
    }
}
