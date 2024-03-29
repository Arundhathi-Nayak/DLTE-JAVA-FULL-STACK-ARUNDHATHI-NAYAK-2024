package com.inversionbean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class App {
    public static void main( String[] args )
    {
        BeanFactory beanFactory=new XmlBeanFactory(new FileSystemResource("spring-dispatcher.xml"));
        Branch outBranch=beanFactory.getBean("branch3", Branch.class);
        System.out.println(outBranch.getBranchContact()+" "+outBranch.getBranchName());
        Branch elroyBranch=beanFactory.getBean("branch5",Branch.class);
        System.out.println(elroyBranch);
    }
}
