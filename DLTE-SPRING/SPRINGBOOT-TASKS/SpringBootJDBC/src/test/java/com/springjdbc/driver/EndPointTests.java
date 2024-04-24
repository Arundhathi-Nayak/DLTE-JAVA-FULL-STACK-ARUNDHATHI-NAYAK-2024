//package com.springjdbc.driver;
//
//import com.springjdbc.driver.controller.TransactionController;
//import com.springjdbc.driver.entity.Transaction;
//import com.springjdbc.driver.service.TransactionService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc
//@WebMvcTest(TransactionController.class)
//public class EndPointTests {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TransactionService transactionService;
//    @Test
//    public void testNewTransaction() throws Exception {
//        Transaction transaction = new Transaction(1L, new Date(2024, 0, 12), "Arundhathi", "Annapoo", 500L, "Friend");
//        when(transactionService.newTransaction(any(Transaction.class))).thenReturn(transaction);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/add/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"transactionId\":1,\"transactionDate\":\"2024-01-12T00:00:00\",\"transactionTo\":\"Arundhathi\",\"transactionBy\":\"Annapoo\",\"transactionAmount\":500,\"transactionRemarks\":\"Friend\"}")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void testFindBySender() throws Exception {
//        Transaction transaction = new Transaction(1L, new Date(2024, 0, 12), "Arundhathi", "Annapoo", 500L, "Friend");
//        List<Transaction> transactions = Arrays.asList(transaction);
//        when(transactionService.findBySender("Annapoo")).thenReturn(transactions);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/sender/Annapoo")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//       //         .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Annapoo")); //success
//               .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionBy").value("Arundhathi")); //failure
//    }
//
//    @Test
//    public void testFindByReceiver() throws Exception {
//        Transaction transaction = new Transaction(1L, new Date(2024, 0, 12), "Arundhathi", "Annapoo", 500L, "Friend");
//        List<Transaction> transactions = Arrays.asList(transaction);
//        when(transactionService.findByReceiver("Arundhathi")).thenReturn(transactions);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/receiver/Arundhathi")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//             //   .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionTo").value("Arundhathi")); //success
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionTo").value("Avinash"));//Failure
//    }
//
//    @Test
//    public void testFindByAmount() throws Exception {
//        Transaction transaction = new Transaction(1L, new Date(2024, 0, 12), "Arundhathi", "Annapoo", 500L, "Friend");
//        List<Transaction> transactions = Arrays.asList(transaction);
//        when(transactionService.findByAmount(500L)).thenReturn(transactions);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/transactions/amount/500")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//              //  .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionAmount").value(500)); //success
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].transactionAmount").value(100)); //failure-
//    }
//}
