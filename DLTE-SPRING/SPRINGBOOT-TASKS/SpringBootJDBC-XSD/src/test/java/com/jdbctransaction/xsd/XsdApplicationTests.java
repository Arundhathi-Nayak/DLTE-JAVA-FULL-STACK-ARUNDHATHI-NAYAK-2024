package com.jdbctransaction.xsd;

import com.jdbctransaction.xsd.entity.Transaction;
import com.jdbctransaction.xsd.services.TransactionServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.omg.IOP.TransactionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class XsdApplicationTests {

    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private TransactionServices transactionService;

    private List<Transaction> newTransactions(){
        List<Transaction> newList=new ArrayList<>();
        Transaction transactionsOne=new Transaction(224555L,new Date(2024,02,02),"Arundhathi","Avinash",80000,"Bills");
        Transaction transactionsTwo=new Transaction(443635L,new Date(2024,03,04),"Anupama","Avinash",89000,"Friend");
        newList.add(transactionsOne);
        newList.add(transactionsTwo);
        return newList;
    }
    @Test
    void testNewTransaction(){
        Transaction transactions1=new Transaction(14355484L,new Date(2024,05,02),"Shantha","Pinki",50000,"Friend");
        Transaction transactions2=new Transaction(228746L,new Date(2024,03,12),"Uday","Avinah",69000,"Bills");
        //when(jdbcTemplate.update(String.valueOf(anyLong()), any(Date.class), anyString(), anyString(), anyLong(), anyString())).thenReturn(0);
        Transaction result = transactionService.newTransaction(transactions1);

        assertEquals(transactions1,result);
    }
    @Test
    void testBySender(){
        Transaction transactions1=new Transaction(14355484L,new Date(2024,05,02),"Arundhathi","Pinki",50000,"Friend");
        Transaction transactions2=new Transaction(228746L,new Date(2024,03,12),"Uday","Avinash",69000,"Bills");

        List<Transaction> transactionsList= Stream.of(transactions1,transactions2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);
        List<Transaction> result=transactionService.findBySender("Arundhathi");
        assertNotNull(result);
        assertEquals(transactionsList,result);
    }
    @Test
    void testByReciever(){

        Transaction transactions1=new Transaction(14355484L,new Date(2024,05,02),"Shantha","Pinki",50000,"Friend");
        Transaction transactions2=new Transaction(228746L,new Date(2024,03,12),"Uday","Avinash",69000,"Bills");

        List<Transaction> transactionsList= Stream.of(transactions1,transactions2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);

        List<Transaction> result=transactionService.findByReceiver("Pinki");
        assertNotNull(result);
        assertEquals(2,result.size());

    }


    @Test
    void testGetByAmount(){
        Transaction transactions1 = new Transaction(14355484L, new Date(2024, 05, 02), "Shantha", "Pinki", 50000, "Friend");
        Transaction transactions2 = new Transaction(228746L, new Date(2024, 03, 12), "Uday", "Avinash", 69000, "Bills");

        List<Transaction> transactionsList = Stream.of(transactions1, transactions2).collect(Collectors.toList());
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactionsList);

        List<Transaction> actual = transactionService.findByAmount(50000);
        assertEquals(transactionsList.size(), actual.size());


    }
    //fail
    @Test
    void testUpdateRemarks(){
        List<Transaction> transactions=newTransactions();
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(BeanPropertyRowMapper.class))).thenReturn(transactions);
        Transaction result=transactionService.updateTransaction(transactions.get(0));
        assertEquals(transactions.get(1).toString(),result.toString());

    }



    @Test
    void testRemoveTransactionBetweenDates() {
        Date startDate = new Date(2024, 05, 02);
        Date endDate = new Date(2024, 06, 02);
        when(jdbcTemplate.update(any(String.class), any(), any())).thenReturn(1);
        String result = transactionService.deleteTransaction(startDate, endDate);

        assertEquals("removed", result);
        assertNotEquals("removed",result);
    }




}
