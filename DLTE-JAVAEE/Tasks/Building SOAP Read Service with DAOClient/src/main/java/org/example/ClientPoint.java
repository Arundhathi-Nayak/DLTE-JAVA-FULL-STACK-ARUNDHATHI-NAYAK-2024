package org.example;

import org.example.entity.Account;
import org.example.services.AccountService;
import readservice.Transaction;
import readservice.Transactions;
import readservice.TransactionsService;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

public class ClientPoint {
    public static void main(String[] args) {
        TransactionsService transactionsService = new TransactionsService();
        Transactions transaction = transactionsService.getTransactionsPort();
        List<Transaction> transactions = (List<Transaction>) transaction.findAllByUserDate("2023-02-12", "shreyas12");
        for (Transaction each : transactions) {
            System.out.println(each);
        }
    }

}
