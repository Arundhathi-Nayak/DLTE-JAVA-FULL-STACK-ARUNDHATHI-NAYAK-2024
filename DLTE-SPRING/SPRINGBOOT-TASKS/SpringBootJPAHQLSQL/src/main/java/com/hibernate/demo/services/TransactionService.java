package com.hibernate.demo.services;

import com.hibernate.demo.model.Transaction;
import com.hibernate.demo.remotes.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
        public Transaction callSave(Transaction transaction){
            return transactionRepository.save(transaction);
        }
       public List<Transaction> callFindAllByRangeOfTransactionAmount(double minAmount,double maxAmount){
            return  transactionRepository.findAllByRangeOfTransactionAmount(minAmount, maxAmount);
       }
      public  List<Transaction> callFindAllByUserAndType( Long userId,  String type){
            return  transactionRepository.findAllByUserAndType(userId, type);
      }

}
