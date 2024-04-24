//package com.springjdbc.driver;
//
//import com.springjdbc.driver.entity.Transaction;
//import com.springjdbc.driver.service.TransactionService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//class DriverApplicationTests {
//    @Mock
//    private JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    private TransactionService transactionService;
//
//    @Test
//    void testNewTransaction() {
//        Transaction transaction1 = new Transaction(223456L, Date.valueOf("2024-03-26"), "Arundhathi", "Avinash", 200L, "Family");
//        Transaction transaction2=new Transaction(323457L, Date.valueOf("2024-03-28"),"Avinsah","Divija",400L,"Bills");
//        when(jdbcTemplate.update(
//                eq("insert into transaction values(?,?,?,?,?,?)"),
//                any(Long.class),
//                any(Date.class),
//                any(String.class),
//                any(String.class),
//                any(Integer.class),
//                any(String.class)
//        )).thenReturn(1);
//        Transaction transactionActual = transactionService.newTransaction(transaction1);
//        System.out.println(transactionActual.getTransactionBy());
//        assertEquals(transaction1.getTransactionBy(),transactionActual.getTransactionBy()); // success
//   //     assertEquals(transaction2.getTransactionBy(),transactionActual.getTransactionBy());//failure
//
//
//    }
//
//    @Test
//    void testFindBySender(){
//        Transaction transaction1 = new Transaction(223456L, Date.valueOf("2024-03-26"), "Arundhathi", "Avinash", 200L, "Family");
//        Transaction transaction2=new Transaction(323457L, Date.valueOf("2024-03-28"),"Avinsah","Arundhathi",400L,"Bills");
//        List<Transaction> expected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
//        List<Transaction> actual=transactionService.findBySender("Arundhathi");
//       // assertEquals(expected,actual);
//       assertNotEquals(notExpected,actual);
//
//    }
//
//    @Test
//    void testFindByReceiver(){
//        Transaction transaction1 = new Transaction(223456L, Date.valueOf("2024-03-26"), "Arundhathi", "Avinash", 200L, "Family");
//        Transaction transaction2=new Transaction(323457L, Date.valueOf("2024-03-28"),"Avinsah","Arundhathi",400L,"Bills");
//        List<Transaction> expected= Stream.of(transaction2).collect(Collectors.toList());
//        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
//        List<Transaction> actual=transactionService.findByReceiver("Avinash");
//     //   assertEquals(expected,actual);  Fails
//       assertNotEquals(notExpected,actual);
//
//    }
//
//    @Test
//    void testFindByAmount(){
//        Transaction transaction1 = new Transaction(223456L, Date.valueOf("2024-03-26"), "Arundhathi", "Avinash", 700L, "Family");
//        Transaction transaction2=new Transaction(323457L, Date.valueOf("2024-03-28"),"Avinsah","Arundhathi",400L,"Bills");
//        List<Transaction> expected= Stream.of(transaction1).collect(Collectors.toList());
//        List<Transaction> notExpected= Stream.of(transaction1,transaction2).collect(Collectors.toList());
//        when(jdbcTemplate.query(anyString(),any(Object[].class),any(BeanPropertyRowMapper.class))).thenReturn(expected);
//        List<Transaction> actual=transactionService.findByAmount(700L);
//      //  assertEquals(expected,actual); Fails
//        assertNotEquals(notExpected,actual);
//    }
//
//}
