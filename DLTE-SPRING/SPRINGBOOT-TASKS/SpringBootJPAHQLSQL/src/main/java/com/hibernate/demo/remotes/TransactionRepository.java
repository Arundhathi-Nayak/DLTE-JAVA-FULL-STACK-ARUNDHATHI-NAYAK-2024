package com.hibernate.demo.remotes;

import com.hibernate.demo.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Transaction save(Transaction transaction);
    @Query(value = "SELECT * FROM user_transaction WHERE user_id = :userId AND type = :type", nativeQuery = true)
    List<Transaction> findAllByUserAndType(@Param("userId") Long userId, @Param("type") String type);

    @Query("SELECT t FROM Transaction t WHERE t.amount >= :minAmount AND t.amount <= :maxAmount")
    List<Transaction> findAllByRangeOfTransactionAmount(@Param("minAmount") double minAmount, @Param("maxAmount") double maxAmount);
}
