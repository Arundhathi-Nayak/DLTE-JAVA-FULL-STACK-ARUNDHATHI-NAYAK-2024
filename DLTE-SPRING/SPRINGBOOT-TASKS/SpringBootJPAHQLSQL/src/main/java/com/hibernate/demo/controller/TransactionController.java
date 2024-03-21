package com.hibernate.demo.controller;

import com.hibernate.demo.model.Transaction;
import com.hibernate.demo.remotes.TransactionRepository;
import com.hibernate.demo.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
  private TransactionService transactionService;
    // http://localhost:8082/transactions/
    @PostMapping(value="/",consumes = "application/xml")
    //    <List>
//    <item>
//        <transactionID>1237</transactionID>
//        <transactionDate>2024-03-15</transactionDate>
//        <name>deposit</name>
//        <transactionAmount>210.0</transactionAmount>
//        <balance>20000.0</balance>
//    </item>
//</List>
//    <Transaction>
//        <transactionDate>2024-03-15</transactionDate>
//        <name>deposit</name>
//        <transactionAmount>280.0</transactionAmount>
//        <balance>2000.0</balance>
//    </Transaction>
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.callSave(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/user/{userId}/{type}")

    // http://localhost:8082/transactions/user/6/deposit

    public List<Transaction> findAllByUserAndType(@PathVariable Long userId, @PathVariable String type) {
        List<Transaction> transactions = transactionService.callFindAllByUserAndType(userId, type);
        return transactions;
    }

    //http://localhost:8082/transactions/amount/200/280
    @GetMapping("/amount/{minAmount}/{maxAmount}")
   //@GetMapping(value="/amount/{minAmount}/{maxAmount}",produces = "application/xml")
    public ResponseEntity<List<Transaction>> findAllByRangeOfTransactionAmount(@PathVariable double minAmount, @PathVariable double maxAmount) {
        List<Transaction> transactions = transactionService.callFindAllByRangeOfTransactionAmount(minAmount, maxAmount);
        return ResponseEntity.ok(transactions);
    }
}
//[
//        {
//        "transactionID": 1237,
//        "transactionDate": "2024-03-15",
//        "name": "deposit",
//        "transactionAmount": 210.0,
//        "balance": 20000.0
//        },
//        {
//        "transactionID": 6,
//        "transactionDate": "2024-03-15",
//        "name": "deposit",
//        "transactionAmount": 280.0,
//        "balance": 2000.0
//        }
//        ]