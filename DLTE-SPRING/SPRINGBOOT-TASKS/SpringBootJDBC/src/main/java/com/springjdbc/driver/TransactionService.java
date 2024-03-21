package com.springjdbc.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction newTransaction(Transaction transaction){
        String sql = "INSERT INTO transaction (transaction_id, transaction_date, transaction_to, transaction_by, transaction_amount,transaction_remarks) VALUES (?, ?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, transaction.getTransactionId(), transaction.getTransactionDate(), transaction.getTransactionTo(), transaction.getTransactionBy(), transaction.getTransactionAmount(),transaction.getTransactionRemarks());
        return transaction;
    }
    public List<Transaction> findBySender(String sender) {
        String sql = "SELECT * FROM transaction WHERE transaction_by = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class), sender);
    }
    public List<Transaction> findByReceiver(String receiver) {
        String sql = "SELECT * FROM transaction WHERE transaction_to = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class), receiver);
    }
    public List<Transaction> findByAmount(Long amount) {
        String sql = "SELECT * FROM transaction WHERE transaction_amount = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class), amount);
    }
}
