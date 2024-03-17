package readservice;

import org.example.entity.Transaction;

import java.util.List;

public class TransactionGroup {

    private List<Transaction> transactionsList;

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
    }

    @Override
    public String toString() {
        return "GroupOfTransactions{" +
                "transactionsList=" + transactionsList +
                '}';
    }
}
