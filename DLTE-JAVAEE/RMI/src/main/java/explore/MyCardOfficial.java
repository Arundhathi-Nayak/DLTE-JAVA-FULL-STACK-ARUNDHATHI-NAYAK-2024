package explore;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Hashtable;

public class MyCardOfficial {
    public static void main(String[] args) throws NamingException, RemoteException, NamingException {
        Hashtable properties=new Hashtable();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.rmi.registry.RegistryContextFactory");
        properties.put(Context.PROVIDER_URL,"rmi://localhost:3030");
        Context context=new InitialContext(properties);
        MyCardFunction myCardServer = (MyCardFunction)context.lookup("java:/card-filter");
        myCardServer.fetchOverAccountBalance().forEach(System.out::println);
    }
}
