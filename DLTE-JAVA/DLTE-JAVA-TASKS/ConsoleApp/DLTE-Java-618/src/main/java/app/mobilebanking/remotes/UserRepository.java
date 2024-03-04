package app.mobilebanking.remotes;

import app.mobilebanking.entity.Account;

import java.util.List;

public interface UserRepository {
    List<Account> findALL();
    boolean verifyPassword(String username,String password);
    void withdraw(String username,String password,double withdrawAmount);
    double balance(String username);
    void addTransactions();
}
