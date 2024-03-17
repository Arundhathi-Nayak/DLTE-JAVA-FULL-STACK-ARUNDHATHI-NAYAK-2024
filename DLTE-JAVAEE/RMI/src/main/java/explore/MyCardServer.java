package explore;

import org.example.entity.Transaction;
import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class MyCardServer extends UnicastRemoteObject implements MyCardFunction, Serializable {
 private  static Context context;
 private Registry registry;
 private AccountService services;

    public MyCardServer() throws RemoteException{
        super();
        services=new AccountService(new DatabaseTarget());
        try{
            registry= LocateRegistry.createRegistry(3030);
            Hashtable properties=new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
            properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
            context=new InitialContext(properties);
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NamingException, RemoteException {
        MyCardServer myCardServer=new MyCardServer();
        context.bind("java:/card-filter",myCardServer);
    }

    @Override
    public List<String> fetchOverAccountBalance() throws RemoteException {
      List<Transaction> transactions=services.callFindAll().stream().filter(each->each.getBalance()>=50000).collect(Collectors.toList());
      List<String> returned=new ArrayList<>();
        for(Transaction transaction: transactions){
          returned.add(transaction.getUser());
      }
        return returned;
    }
}
