package explore;

import org.example.entity.Account;
import org.example.entity.Transaction;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyCardFunction extends Remote {
    List<String> fetchOverAccountBalance() throws RemoteException;
}
