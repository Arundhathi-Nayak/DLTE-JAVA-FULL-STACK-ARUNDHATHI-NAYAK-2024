package app.mobilebanking.remotes;

import org.example.entity.Account;
import org.example.entity.Transaction;

import java.sql.Date;
import java.util.List;

/**
 * Abstract methods that services needed and we are implementing it in UserFileRepository
 */
public interface UserRepository {
    boolean verifyPassword(String username, String password);
    double withdraw(String username, String password, double withdrawAmount);
    double balance(String username);
    void addTransactions(Account account);
    List<Transaction> findALL();
    List<Transaction> findAllUser(String user);
    List<Transaction> findAllByDate(Date date, String user);
    Account findUserByUsername(String username);
}
