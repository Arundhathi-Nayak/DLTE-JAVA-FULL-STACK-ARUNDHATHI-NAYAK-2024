package com.jdbctransaction.xsd.services;

import com.jdbctransaction.xsd.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServices {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Transaction newTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction (transaction_id, transaction_date, transaction_to, transaction_by, transaction_amount,transaction_remarks) VALUES (?, ?, ?, ?, ?,?)";
        jdbcTemplate.update(sql, transaction.getTransactionId(), transaction.getTransactionDate(), transaction.getTransactionTo(), transaction.getTransactionBy(), transaction.getTransactionAmount(), transaction.getTransactionRemarks());
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

    public List<Transaction> findByAmount(Integer amount) {
        String sql = "SELECT * FROM transaction WHERE transaction_amount = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Transaction.class), amount);
    }

    public Transaction updateTransaction(Transaction transaction) {
        int acknowledge = jdbcTemplate.update("update transaction set transaction_remarks=? where transaction_id=?",
                new Object[]{transaction.getTransactionRemarks(), transaction.getTransactionId()}
        );
        if (acknowledge != 0) return transaction;
        else return null;
    }

    public String deleteTransaction(Date startDate, Date endDate) {
        int acknowledge = jdbcTemplate.update("delete from transaction where transaction_date between ? and ?",
                new Object[]{startDate, endDate}
        );
        if (acknowledge != 0) return "Transaction deleted";
        else return "Failed to delete transaction";
    }

}
