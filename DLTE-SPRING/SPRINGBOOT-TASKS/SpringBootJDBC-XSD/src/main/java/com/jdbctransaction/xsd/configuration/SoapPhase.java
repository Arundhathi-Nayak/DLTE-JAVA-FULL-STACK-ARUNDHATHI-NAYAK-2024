package com.jdbctransaction.xsd.configuration;

import com.jdbctransaction.xsd.entity.Transaction;
import com.jdbctransaction.xsd.services.TransactionServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.transaction.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Endpoint
public class SoapPhase {
    private final String url="http://transaction.services";
    @Autowired
    private TransactionServices transactionServices;

    @PayloadRoot(namespace = url,localPart = "newTransaction")
    public NewTransactionResponse addNewTransaction(@RequestPayload NewTransactionRequest newTransactionRequest){
        NewTransactionResponse newTransactionResponse=new NewTransactionResponse();
        ServiceStatus serviceStatus=new ServiceStatus();

        services.transaction.Transaction actualTransaction=newTransactionRequest.getTransaction();
        Transaction daoTransaction=new Transaction();
        BeanUtils.copyProperties(actualTransaction,daoTransaction);

        daoTransaction=transactionServices.newTransaction(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage(daoTransaction.getTransactionId()+"is inserted");
        }else{
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage(daoTransaction.getTransactionId()+"not inserted");
        }
        newTransactionResponse.setServiceStatus(serviceStatus);
        BeanUtils.copyProperties(daoTransaction,actualTransaction);
        newTransactionResponse.setTransaction(actualTransaction);

        return newTransactionResponse;
    }

    @PayloadRoot(namespace = url,localPart = "filterBySender")
    @ResponsePayload
    public FilterBySenderResponse filterBySender(@RequestPayload FilterBySenderRequest filterBySenderRequest){
        FilterBySenderResponse filterBySenderResponse=new FilterBySenderResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transaction.Transaction> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.findBySender(filterBySenderRequest.getSender());
        Iterator<Transaction> iterator =daoTransaction.iterator();

        while (iterator.hasNext()){
            services.transaction.Transaction currentTransaction=new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by sender "+filterBySenderRequest.getSender()+" is fetched");

        filterBySenderResponse.setServiceStatus(serviceStatus);
        filterBySenderResponse.getTransaction().addAll(transactions);
        return filterBySenderResponse;

    }

    @PayloadRoot(namespace = url,localPart = "filterByReceiver")
    @ResponsePayload
    public FilterByReceiverResponse filterByReceiver(@RequestPayload FilterByReceiverRequest filterByReceiverRequest){
        FilterByReceiverResponse filterByReceiverResponse=new FilterByReceiverResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transaction.Transaction> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.findByReceiver(filterByReceiverRequest.getReceiver());
        Iterator<Transaction> iterator =daoTransaction.iterator();

        while (iterator.hasNext()){
            services.transaction.Transaction currentTransaction=new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by receiver "+filterByReceiverRequest.getReceiver()+" is fetched");

        filterByReceiverResponse.setServiceStatus(serviceStatus);
        filterByReceiverResponse.getTransaction().addAll(transactions);
        return filterByReceiverResponse;

    }
    @PayloadRoot(namespace = url,localPart = "filterByAmount")
    @ResponsePayload
    public FilterByAmountResponse filterByAmount(@RequestPayload FilterByAmountRequest filterByAmountRequest){
        FilterByAmountResponse filterByAmountResponse=new FilterByAmountResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<services.transaction.Transaction> transactions=new ArrayList<>();
        List<Transaction> daoTransaction=transactionServices.findByAmount(filterByAmountRequest.getAmount());
        Iterator<Transaction> iterator =daoTransaction.iterator();

        while (iterator.hasNext()){
            services.transaction.Transaction currentTransaction=new services.transaction.Transaction();
            BeanUtils.copyProperties(iterator.next(),currentTransaction);
            transactions.add(currentTransaction);
        }
        serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage("Transaction by amount "+filterByAmountRequest.getAmount()+" is fetched");

        filterByAmountResponse.setServiceStatus(serviceStatus);
        filterByAmountResponse.getTransaction().addAll(transactions);
        return filterByAmountResponse;
    }

    @PayloadRoot(namespace = url,localPart = "updateByRemarks")
    @ResponsePayload
    public UpdateByRemarksResponse updateByRemarks(@RequestPayload UpdateByRemarksRequest updateByRemarksRequest){
        UpdateByRemarksResponse updateByRemarksResponse=new UpdateByRemarksResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        services.transaction.Transaction transaction=new services.transaction.Transaction();

        Transaction daoTransaction=new Transaction();
        BeanUtils.copyProperties(updateByRemarksRequest.getTransaction(),daoTransaction);
        daoTransaction=transactionServices.updateTransaction(daoTransaction);

        if(daoTransaction!=null){
            serviceStatus.setStatus("SUCCESS");
            serviceStatus.setMessage("Transaction updated");
        }else
        {
            serviceStatus.setStatus("FAILURE");
            serviceStatus.setMessage("Transaction update failed");
        }

        BeanUtils.copyProperties(daoTransaction,transaction);
        updateByRemarksResponse.setServiceStatus(serviceStatus);
        updateByRemarksResponse.setTransaction(transaction);
        return updateByRemarksResponse;
    }


    @PayloadRoot(namespace = url,localPart = "deleteByRangeOfDates")
    @ResponsePayload
    public DeleteByRangeOfDatesResponse deleteBasedOnDates(@RequestPayload DeleteByRangeOfDatesRequest deleteByRangeOfDatesRequest){
        DeleteByRangeOfDatesResponse deleteByRangeOfDatesResponse=new DeleteByRangeOfDatesResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        String deleteTransaction=transactionServices.deleteTransaction(deleteByRangeOfDatesRequest.getStartDate(),deleteByRangeOfDatesRequest.getEndDate());
        if(deleteTransaction.contains("deleted")){
            serviceStatus.setStatus("FAILURE");
        }else
            serviceStatus.setStatus("SUCCESS");
        serviceStatus.setMessage(deleteTransaction);
        deleteByRangeOfDatesResponse.setServiceStatus(serviceStatus);
        return deleteByRangeOfDatesResponse;
    }
}