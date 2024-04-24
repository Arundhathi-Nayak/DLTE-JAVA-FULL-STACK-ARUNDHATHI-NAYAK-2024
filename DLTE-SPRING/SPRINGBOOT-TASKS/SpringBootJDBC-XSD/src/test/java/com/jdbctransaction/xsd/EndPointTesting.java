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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testAddNewTransaction() throws DatatypeConfigurationException {
        Transaction transaction1 = new Transaction(14355484L, new Date(2024, 05, 02), "Shantha", "Pinki", 50000, "Friend");
        when(transactionService.newTransaction(any(Transaction.class))).thenReturn(transaction1);

        NewTransactionRequest newTransaction = new NewTransactionRequest();
        services.transaction.Transaction transaction = new services.transaction.Transaction();
        transaction.setTransactionId(14355484L);
        LocalDate date = LocalDate.of(2024, 05, 02);
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
        transaction.setTransactionDate(xmlGregorianCalendar);
        transaction.setTransactionBy("Pinki");
        transaction.setTransactionTo("Shantha");
        transaction.setTransactionAmount(50000);
        transaction.setTransactionRemarks("Friend");
        newTransaction.setTransaction(transaction);
        NewTransactionResponse response = soapPhase.addNewTransaction(newTransaction);
        assertTrue(transaction1.getTransactionId().equals(response.getTransaction().getTransactionId()));
    }

    @Test
    public void testFilterSender() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(14355484L, new Date(2024, 05, 02), "Shantha", "Pinki", 50000, "Friend"));
        when(transactionService.findBySender("Pinki")).thenReturn(mockTransactions);
        FilterBySenderRequest request = new FilterBySenderRequest();
        request.setSender("Pinki");
        FilterBySenderResponse response = soapPhase.filterBySender(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transaction by sender " + request.getSender() + " is fetched", response.getServiceStatus().getMessage());
    }

    @Test
    public void testFilterReciever() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(14355484L, new Date(2024, 05, 02), "Shantha", "Pinki", 50000, "Friend"));
        when(transactionService.findByReceiver("Shantha")).thenReturn(mockTransactions);
        FilterByReceiverRequest request = new FilterByReceiverRequest();
        request.setReceiver("Shantha");
        FilterByReceiverResponse response = soapPhase.filterByReceiver(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transaction by receiver " + request.getReceiver() + " is fetched", response.getServiceStatus().getMessage());
    }

    @Test
    public void testFilterAmount() {
        List<Transaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new Transaction(14355484L, new Date(2024, 05, 02), "Shantha", "Pinki", 50000, "Friend"));
        when(transactionService.findByAmount(5000)).thenReturn(mockTransactions);
        FilterByAmountRequest request = new FilterByAmountRequest();
        request.setAmount(50000);
        FilterByAmountResponse response = soapPhase.filterByAmount(request);
        assertEquals("SUCCESS", response.getServiceStatus().getStatus());
        assertEquals("Transaction by amount " + request.getAmount() + " is fetched", response.getServiceStatus().getMessage());
        assertEquals(0, response.getTransaction().size());
    }


    @Test
    public void testUpdateTransaction() throws DatatypeConfigurationException {
        Transaction updateTransaction = new Transaction();
        updateTransaction.setTransactionId(15000L);
        updateTransaction.setTransactionDate(new Date(2024, 05, 02));
        updateTransaction.setTransactionBy("Avinash");
        updateTransaction.setTransactionTo("Avinu");
        updateTransaction.setTransactionAmount(1000);
        updateTransaction.setTransactionRemarks("Bills");
        when(transactionService.updateTransaction(any(Transaction.class))).thenReturn(updateTransaction);
        UpdateByRemarksRequest request = new UpdateByRemarksRequest();
        services.transaction.Transaction transaction = new services.transaction.Transaction();
        transaction.setTransactionId(15000L);
        LocalDate date = LocalDate.of(2024, 05, 02);
        XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(date.toString());
        transaction.setTransactionDate(xmlGregorianCalendar);
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
    public void testRemoveTransactionBetweenDates() throws DatatypeConfigurationException {
        LocalDate date1 = LocalDate.of(2024, 05, 02);
        LocalDate date2 = LocalDate.of(2024, 05, 05);
        XMLGregorianCalendar xmlGregorianCalendar1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(date1.toString());
        XMLGregorianCalendar xmlGregorianCalendar2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(date2.toString());
        //   when(transactionService.deleteTransaction(date1, date1)).thenReturn("remove");
        DeleteByRangeOfDatesRequest request = new DeleteByRangeOfDatesRequest();
        Date start = new Date(2024, 05, 02);
        Date end = new Date(2024, 05, 05);
        request.setStartDate(xmlGregorianCalendar1);
        request.setEndDate(xmlGregorianCalendar2);
        DeleteByRangeOfDatesResponse response = soapPhase.deleteBasedOnDates(request);
        assertEquals("deleted", response.getServiceStatus().getStatus());
        assertEquals("deleted", response.getServiceStatus().getMessage());
    }
}
