package readservice;

import org.example.middleware.DatabaseTarget;
import org.example.services.AccountService;

import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.Date;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Transactions {
    TransactionGroup groupOfTransactions = new TransactionGroup();
    AccountService service;
    public Transactions() {
        service = new AccountService(new DatabaseTarget());
    }

    @WebResult(name = "GroupOfTransaction")
    public TransactionGroup findAllByUserDate( String username, String date){
        groupOfTransactions.setTransactionsList(service.callFindAllDate(Date.valueOf(date),username));
        return  groupOfTransactions;
    }
}
