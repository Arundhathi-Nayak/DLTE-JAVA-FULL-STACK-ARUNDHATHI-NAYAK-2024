package com.bean.lazy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class AccountMain {
    public static void main(String[] args) {

//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        // Bank bean is not yet initialized
//        System.out.println("Bank bean not yet initialized");
//
//        // Get the Bank bean (this triggers initialization)
//        Bank bank = context.getBean(Bank.class);
//
//        // Use the Bank bean
//        System.out.println("Bank bean initialized: " + bank);
//
//        ((AnnotationConfigApplicationContext) context).close();

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        for(String s:context.getBeanDefinitionNames()) {

            System.out.println(s);
        }

//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        // Bank bean is initialized eagerly
//        System.out.println("Bank bean initialized eagerly: " + context.getBean(Bank.class));
//
//        ((AnnotationConfigApplicationContext) context).close();




















//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        Bank bank = context.getBean(Bank.class);
//
//        // Accounts list is not yet initialized
//        System.out.println("Accounts list not initialized");
//
//        // Get the accounts list (this triggers initialization)
//        List<Account> accounts = bank.getAccounts();
//
//        // Use the accounts list
//        System.out.println("Accounts list initialized with " + accounts.size() + " accounts:");
//        for (Account account : accounts) {
//            System.out.println("Account Number: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
//        }
//
//        ((AnnotationConfigApplicationContext) context).close();
    }
}
