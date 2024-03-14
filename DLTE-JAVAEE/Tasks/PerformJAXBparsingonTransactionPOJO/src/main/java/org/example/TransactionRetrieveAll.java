package org.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class TransactionRetrieveAll {
    public static void main(String[] args) {
        try {
            File file = new File("transactions.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(TransactionCreation.TransactionsWrapper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            TransactionCreation.TransactionsWrapper transactionsWrapper = (TransactionCreation.TransactionsWrapper) jaxbUnmarshaller.unmarshal(file);

            List<Transaction> transactions = transactionsWrapper.getTransactions();
            for (Transaction transaction : transactions) {
                System.out.println("User: " + transaction.getUser());
                System.out.println("Date: " + transaction.getDate());
                System.out.println("Amount: " + transaction.getAmount());
                System.out.println();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
