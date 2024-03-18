package app.mobilebanking.services;

import org.example.entity.Account;
import org.example.remotes.StorageTarget;
import org.example.remotes.UserRepository;

import java.util.List;

/**
 * Function call for the methods in userRepository
 */
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
    public double callWithdraw(String username,String password, double withdrawAmount){
        try {
             return userRepository.withdraw(username,password,withdrawAmount);
        }catch (Exception e){
            return 0;
        }
    }
//    public void callAddTransactions(){
//        try {
//            userRepository.addTransactions();
//        }
//        catch (Exception e){
//            return;
//        }
//    }
//    public List<Account> callFinaAll(){
//        try{
//            return userRepository.findALL();
//        }
//        catch (Exception e){
//            return null;
//        }
//    }

}
