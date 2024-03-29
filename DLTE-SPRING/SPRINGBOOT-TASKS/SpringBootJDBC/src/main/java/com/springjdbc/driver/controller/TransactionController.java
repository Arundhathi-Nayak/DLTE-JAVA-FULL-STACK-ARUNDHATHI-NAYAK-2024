package com.springjdbc.driver.controller;

import com.springjdbc.driver.entity.Transaction;
import com.springjdbc.driver.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
// username : aru password : nayak  go to link   http://localhost:8082/profile/register

// give authentication and run url
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
