package com.jdbctransaction.xsd;

import com.jdbctransaction.xsd.configuration.SoapPhase;
import com.jdbctransaction.xsd.entity.Transaction;
import com.jdbctransaction.xsd.services.TransactionServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import services.transaction.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {
    @MockBean
    private TransactionServices transactionService;

    @InjectMocks
    private SoapPhase soapPhase;

    @Test
    public void testFilterSender() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(14355484L,new Date(2024,05,02),"Shantha","Pinki",50000,"Friend"));
        when(transactionService.findBySender("Pinki")).thenReturn(mockTransactions);
        FilterBySenderRequest request = new FilterBySenderRequest();
        request.setSender("Pinki");
        FilterBySenderResponse response= soapPhase.filterBySender(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transaction by sender "+request.getSender()+" is fetched", response.getServiceStatus().getMessage());
    }

    @Test
    public void testFilterReciever() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(14355484L,new Date(2024,05,02),"Shantha","Pinki",50000,"Friend"));
        when(transactionService.findByReceiver("Shantha")).thenReturn(mockTransactions);
        FilterByReceiverRequest request = new FilterByReceiverRequest();
        request.setReceiver("Shantha");
        FilterByReceiverResponse response = soapPhase.filterByReceiver(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transaction by receiver "+request.getReceiver()+" is fetched", response.getServiceStatus().getMessage());
    }

    @Test
    public void testFilterAmount() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(14355484L,new Date(2024,05,02),"Shantha","Pinki",50000,"Friend"));
        when(transactionService.findByAmount(5000)).thenReturn(mockTransactions);
        FilterByAmountRequest request = new FilterByAmountRequest();
        request.setAmount(50000);
        FilterByAmountResponse response = soapPhase.filterByAmount(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transaction by amount "+request.getAmount()+" is fetched", response.getServiceStatus().getMessage());
        assertEquals(0, response.getTransaction().size());
    }


    @Test
    public void testUpdateTransaction() {
        Transaction updateTransaction = new Transaction();
        updateTransaction.setTransactionId(15000L);
        updateTransaction.setTransactionDate(new Date(2024,05,02));
        updateTransaction.setTransactionBy("Avinash");
        updateTransaction.setTransactionTo("Avinu");
        updateTransaction.setTransactionAmount(1000);
        updateTransaction.setTransactionRemarks("Bills");
        when(transactionService.updateTransaction(any(Transaction.class))).thenReturn(updateTransaction);
        UpdateByRemarksRequest request = new UpdateByRemarksRequest();
        services.transaction.Transaction transaction = new services.transaction.Transaction();
        transaction.setTransactionId(15000L);
        transaction.setTransactionDate(new Date(2024,05,02));
        updateTransaction.setTransactionBy("Avinash");
        updateTransaction.setTransactionTo("Avinu");
        updateTransaction.setTransactionAmount(1000);
        updateTransaction.setTransactionRemarks("Friend");
        request.setTransaction(transaction);
        UpdateByRemarksResponse response = soapPhase.updateByRemarks(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transaction updated", response.getServiceStatus().getMessage());

    }

    @Test
    public void testRemoveTransactionBetweenDates() {
        Date startDate =new Date(2024,05,02);
        Date endDate =new Date(2024,05,06);
        when(transactionService.deleteTransaction(startDate, endDate)).thenReturn("remove");
        DeleteByRangeOfDatesRequest request = new DeleteByRangeOfDatesRequest();
        Date start =new Date(2024,05,02);
        Date end =new Date(2024,05,05);
        request.setStartDate(start);
        request.setEndDate(end);
        DeleteByRangeOfDatesResponse response = soapPhase.deleteBasedOnDates(request);
        assertEquals("deleted", response.getServiceStatus().getStatus());
        assertEquals("deleted", response.getServiceStatus().getMessage());
    }



}
