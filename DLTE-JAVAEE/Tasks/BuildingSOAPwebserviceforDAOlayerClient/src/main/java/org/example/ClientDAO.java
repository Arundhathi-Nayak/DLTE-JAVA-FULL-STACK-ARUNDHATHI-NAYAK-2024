package org.example;

import org.example.services.AccountService;
import soapwebservice.Account;
import soapwebservice.CallOperation;
import soapwebservice.CallOperationService;
import soapwebservice.Transaction;

public class ClientDAO {
    public static void main(String[] args) {
        CallOperationService callOperationService=new CallOperationService();
        CallOperation source=callOperationService.getCallOperationPort();

 //       source.createAccount(123456789,123432111,"nayak@123","Aru",5000,"arundhathi12","arundhathi123");
  //      System.out.println();
    //  Account account = source.findByUser("arundhathi12");
   //    System.out.println("Account Number: "+account.getAccountNumber()+"\nCustomer Id: "+account.getCustomerId()+"\nName: "+account.getName()+"\nBalance: "+account.getBalance());
       source.withdraw("arundhahti12","arundhathi123",500);
        Account account = source.findByUser("arundhathi12");
       System.out.println("Account Number: "+account.getAccountNumber()+"\nCustomer Id: "+account.getCustomerId()+"\nName: "+account.getName()+"\nBalance: "+account.getBalance());
    }

}
