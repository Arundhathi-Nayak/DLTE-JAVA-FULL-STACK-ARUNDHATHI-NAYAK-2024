package com.jdbctransaction.xsd.configuration;

import com.jdbctransaction.xsd.entity.Transaction;
import com.jdbctransaction.xsd.services.TransactionServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transaction.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url = "http://transaction.services";
    @Autowired
    private TransactionServices transactionServices;

    @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "newTransaction")
    @ResponsePayload
    public NewTransactionResponse addNewTransaction(@RequestPayload NewTransactionRequest newTransactionRequest) {
        NewTransactionResponse newTransactionResponse = new NewTransactionResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        services.transaction.Transaction actualTransaction = newTransactionRequest.getTransaction();
        Date date = newTransactionRequest.getTransaction().getTransactionDate().toGregorianCalendar().getTime();
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(date);/// yyyy-mm-dd
        BeanUtils.copyProperties(actualTransaction, transaction);

        transaction = transactionServices.newTransaction(transaction);

        if (transaction != null) {
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(transaction.getTransactionId() + "is inserted");
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(transaction.getTransactionId() + "not inserted");
        }
        newTransactionResponse.setServiceStatus(serviceStatus);
        BeanUtils.copyProperties(transaction, actualTransaction);
        newTransactionResponse.setTransaction(actualTransaction);

        return newTransactionResponse;
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "filterBySenderRequest")
    @ResponsePayload
    public FilterBySenderResponse filterBySender(@RequestPayload FilterBySenderRequest filterBySenderRequest) {
        FilterBySenderResponse filterBySenderResponse = new FilterBySenderResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> transactions = new ArrayList<>();
        List<Transaction> transaction = transactionServices.findBySender(filterBySenderRequest.getSender());
        Iterator<Transaction> iterator = transaction.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransaction = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by sender " + filterBySenderRequest.getSender() + " is fetched");

        filterBySenderResponse.setServiceStatus(serviceStatus);
        filterBySenderResponse.getTransaction().addAll(transactions);
        return filterBySenderResponse;
    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "filterByReceiverRequest")
    @ResponsePayload
    public FilterByReceiverResponse filterByReceiver(@RequestPayload FilterByReceiverRequest filterByReceiverRequest) {
        FilterByReceiverResponse filterByReceiverResponse = new FilterByReceiverResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> transactions = new ArrayList<>();
        List<Transaction> transaction = transactionServices.findByReceiver(filterByReceiverRequest.getReceiver());
        Iterator<Transaction> iterator = transaction.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransaction = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by receiver " + filterByReceiverRequest.getReceiver() + " is fetched");

        filterByReceiverResponse.setServiceStatus(serviceStatus);
        filterByReceiverResponse.getTransaction().addAll(transactions);
        return filterByReceiverResponse;

    }

    @PreAuthorize("hasAnyAuthority('customer')")
    @PayloadRoot(namespace = url, localPart = "filterByAmountRequest")
    @ResponsePayload
    public FilterByAmountResponse filterByAmount(@RequestPayload FilterByAmountRequest filterByAmountRequest) {
        FilterByAmountResponse filterByAmountResponse = new FilterByAmountResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        List<services.transaction.Transaction> transactions = new ArrayList<>();
        List<Transaction> transaction = transactionServices.findByAmount(filterByAmountRequest.getAmount());
        Iterator<Transaction> iterator = transaction.iterator();

        while (iterator.hasNext()) {
            services.transaction.Transaction currentTransaction = new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(), currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by amount " + filterByAmountRequest.getAmount() + " is fetched");

        filterByAmountResponse.setServiceStatus(serviceStatus);
        filterByAmountResponse.getTransaction().addAll(transactions);
        return filterByAmountResponse;
    }

    @PreAuthorize("hasAnyAuthority('admin','manager')")
    @PayloadRoot(namespace = url, localPart = "updateByRemarksRequest")
    @ResponsePayload
    public UpdateByRemarksResponse updateByRemarks(@RequestPayload UpdateByRemarksRequest updateByRemarksRequest) {
        UpdateByRemarksResponse updateByRemarksResponse = new UpdateByRemarksResponse();
        ServiceStatus serviceStatus = new ServiceStatus();
        services.transaction.Transaction transaction = new services.transaction.Transaction();

        Transaction daoTransaction = new Transaction();
        BeanUtils.copyProperties(updateByRemarksRequest.getTransaction(), daoTransaction);
        daoTransaction = transactionServices.updateTransaction(daoTransaction);

        if (daoTransaction != null) {
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("Transaction updated");
        } else {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Transaction update failed");
        }

        BeanUtils.copyProperties(daoTransaction, transaction);
        updateByRemarksResponse.setServiceStatus(serviceStatus);
        updateByRemarksResponse.setTransaction(transaction);
        return updateByRemarksResponse;
    }

    @PreAuthorize("hasAnyAuthority('admin')")
    @PayloadRoot(namespace = url, localPart = "deleteByRangeOfDatesRequest")
    @ResponsePayload
    public DeleteByRangeOfDatesResponse deleteBasedOnDates(@RequestPayload DeleteByRangeOfDatesRequest deleteByRangeOfDatesRequest) {
        DeleteByRangeOfDatesResponse deleteByRangeOfDatesResponse = new DeleteByRangeOfDatesResponse();
        Date startDate = deleteByRangeOfDatesRequest.getStartDate().toGregorianCalendar().getTime();
        Date endDate = deleteByRangeOfDatesRequest.getEndDate().toGregorianCalendar().getTime();
        ServiceStatus serviceStatus = new ServiceStatus();
        String deleteTransaction = transactionServices.deleteTransaction(startDate, endDate);
        if (deleteTransaction.contains("deleted")) {
            serviceStatus.setStatus("SUCCESS");
        } else
            serviceStatus.setStatus("FAILURE");
        serviceStatus.setMessage(deleteTransaction);
        deleteByRangeOfDatesResponse.setServiceStatus(serviceStatus);
        return deleteByRangeOfDatesResponse;
    }

}
