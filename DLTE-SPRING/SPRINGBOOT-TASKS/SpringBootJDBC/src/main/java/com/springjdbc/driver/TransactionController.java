package com.springjdbc.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("add/")
    //http://localhost:8082/transactions/add/
    public Transaction newTransaction(@RequestBody Transaction transaction) {
        return transactionService.newTransaction(transaction);
    }
    @GetMapping("/sender/{sender}")
    //http://localhost:8082/transactions/sender/Arundhathi
    public List<Transaction> findBySender(@PathVariable String sender) {
        return transactionService.findBySender(sender);
    }
    @GetMapping("/receiver/{receiver}")
    //http://localhost:8082/transactions/receiver/Eeksha
    public List<Transaction> findByReceiver(@PathVariable String receiver) {
        return transactionService.findByReceiver(receiver);
    }

    @GetMapping("/amount/{amount}")
    //http://localhost:8082/transactions/amount/700
    public List<Transaction> findByAmount(@PathVariable Long amount) {
        return transactionService.findByAmount(amount);
    }
}
