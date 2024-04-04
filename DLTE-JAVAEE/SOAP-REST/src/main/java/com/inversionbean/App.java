package com.inversionbean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class App {
    public static void main( String[] args )
    {
        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("spring-dispatcher.xml"));// read configuration of file here it will not create object
        Branch outBranch=beanFactory.getBean("branch3", Branch.class); //constructor,setter  here object is created
//        System.out.println(outBranch.getBranchContact()+" "+outBranch.getBranchName());
//        Branch newBranch=beanFactory.getBean("branch5",Branch.class);
//        System.out.println(newBranch);
    }
}
