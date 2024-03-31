package com.springjdbc.driver.service;

import com.springjdbc.driver.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Transaction> updateTransaction(String remarks){
       String sql="update transaction set transaction_remarks=? where transaction_id=?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class),remarks );

    }
    public List<Transaction> deleteTransaction(Date startDate, Date endDate){
        String sql="delete from transaction where transaction_date between ? and ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class),startDate,endDate);

    }
}
