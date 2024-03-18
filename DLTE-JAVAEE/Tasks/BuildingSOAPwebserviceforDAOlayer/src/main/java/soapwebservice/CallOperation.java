package soapwebservice;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CallOperation {

    AccountService service;
    public CallOperation() {
        service = new AccountService(new DatabaseTarget());
    }

    @WebResult(name = "Account")
    public Account findByUser(String username){
        Account account = service.findUserByUsername(username);
        //List<Transaction> accounts = service.callFindAllUser(username);
        return account;
    }

    @WebResult(name = "Create")
    public void createAccount( long accountNumber,long customerId,String email,String name,double balance,String username,String password){
        service.callAddTransactions(new Account(accountNumber,customerId,email,name,balance,username,password));

    }

    @WebResult(name = "Double")
    public void withdraw(String username, String password, double withdrawAmount){
        service.callWithdraw(username,password,withdrawAmount);
    }

}
