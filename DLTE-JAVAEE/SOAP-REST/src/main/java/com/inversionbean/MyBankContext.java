package com.inversionbean;

import com.inversionbean.Branch;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyBankContext {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("mybank-branches.xml");
        Branch newBranch=applicationContext.getBean("branch3", Branch.class);// object creation
        System.out.println(newBranch.getIfsCode()+" "+newBranch.getBranchName());
    }
}
