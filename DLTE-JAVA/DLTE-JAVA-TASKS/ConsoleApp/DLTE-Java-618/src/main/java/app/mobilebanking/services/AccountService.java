package app.mobilebanking.services;

import app.mobilebanking.entity.Account;
import app.mobilebanking.remotes.StorageTarget;
import app.mobilebanking.remotes.UserRepository;

import java.util.List;

public class AccountService {
    UserRepository userRepository;

    public AccountService(StorageTarget storageTarget) {
        userRepository = storageTarget.getUserRepository();
    }

    public boolean callVerifyPassword(String username,String password){
        try {
           return userRepository.verifyPassword(username,password);
        }
        catch (Exception e){
            return false;
        }
    }
    public void callWithdraw(String username,String password, double withdrawAmount){
        try {
             userRepository.withdraw(username,password,withdrawAmount);
        }catch (Exception e){
            return;
        }
    }
    public void callAddTransactions(){
        try {
            userRepository.addTransactions();
        }
        catch (Exception e){
            return;
        }
    }
    public List<Account> callFinaAll(){
        try{
            return userRepository.findALL();
        }
        catch (Exception e){
            return null;
        }
    }

}
