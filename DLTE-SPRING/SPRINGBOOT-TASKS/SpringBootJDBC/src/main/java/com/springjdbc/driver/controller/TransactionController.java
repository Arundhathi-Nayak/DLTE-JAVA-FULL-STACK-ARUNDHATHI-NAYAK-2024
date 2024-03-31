package com.springjdbc.driver.controller;

import com.springjdbc.driver.entity.Transaction;
import com.springjdbc.driver.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/transactions")
// username : aru password : nayak  go to link   http://localhost:8082/profile/register   post
//{
//        "name":"Arundhathi",
//        "username":"aru",
//        "password":"nayak",
//        "email":"nayak@123",
//        "contact": 9876543212,
//        "aadhaar":123456789987
//        }

// give authentication and run url
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("add/")
    //http://localhost:8082/transactions/add/
    public Transaction newTransaction(@RequestBody Transaction transaction){
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

    @PutMapping("/remarks/{remarks}")
    public List<Transaction> updateTransaction(@PathVariable String remarks) {
        return transactionService.updateTransaction(remarks);
    }

    @DeleteMapping("/dates/startDate/endDate")
    public List<Transaction> deleteTransaction(@PathVariable Date startDate, Date endDate) {
        return transactionService.deleteTransaction(startDate,endDate);
    }
}
