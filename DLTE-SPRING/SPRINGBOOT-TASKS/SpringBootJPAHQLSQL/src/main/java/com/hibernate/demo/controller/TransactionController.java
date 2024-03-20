package com.hibernate.demo.controller;

import com.hibernate.demo.model.Transaction;
import com.hibernate.demo.remotes.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionRepository.save(transaction);
        return ResponseEntity.ok(savedTransaction);
    }

    @GetMapping("/user/{userId}/type/{type}")
    public ResponseEntity<List<Transaction>> findAllByUserAndType(@PathVariable Long userId, @PathVariable String type) {
        List<Transaction> transactions = transactionRepository.findAllByUserAndType(userId, type);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/amount-range")
    public ResponseEntity<List<Transaction>> findAllByRangeOfTransactionAmount(@RequestParam double minAmount, @RequestParam double maxAmount) {
        List<Transaction> transactions = transactionRepository.findAllByRangeOfTransactionAmount(minAmount, maxAmount);
        return ResponseEntity.ok(transactions);
    }
}
