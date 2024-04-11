package com.payment.webservice.soapconfig;

import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.payee.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

@Endpoint
@ComponentScan("com.paymentdao.payment")

//implementation of request and responses

//http://localhost:8082/payeerepo/payee.wsdl
public class SoapPhase {
    private final String url="http://payee.services";
    @Autowired
    private PaymentTransferRepository paymentTransferImplementation;

    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");

    org.slf4j.Logger logger= LoggerFactory.getLogger(SoapPhase.class);

    //display all details
    @PayloadRoot(namespace = url,localPart = "findAllPayeeBasedOnAccountNumberRequest")
    @ResponsePayload
    public FindAllPayeeBasedOnAccountNumberResponse listPayeeBasedOnAccountNumber(@RequestPayload FindAllPayeeBasedOnAccountNumberRequest findAllPayeeBasedOnAccountNumberRequest){
        FindAllPayeeBasedOnAccountNumberResponse findAllPayeeBasedOnAccountNumberResponse=new FindAllPayeeBasedOnAccountNumberResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<Payee> payees=new ArrayList<>();

//        List<com.paymentdao.payment.entity.Payee> daoPayee=paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());
//
//        daoPayee.forEach(each->{
//            Payee currentPayee=new Payee();
//            BeanUtils.copyProperties(each,currentPayee);
//            payees.add(currentPayee);
//        });
//        serviceStatus.setStatus(resourceBundle.getString("success.payee"));
//        serviceStatus.setMessage(resourceBundle.getString("payee.details")+findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());

        try {
            List<com.paymentdao.payment.entity.Payee> daoPayee = paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());

            daoPayee.forEach(each -> {
                Payee currentPayee = new Payee();
                BeanUtils.copyProperties(each, currentPayee);
                payees.add(currentPayee);
            });
          //  serviceStatus.setStatus(resourceBundle.getString("success.payee"));
            serviceStatus.setStatus(HttpServletResponse.SC_OK);
            findAllPayeeBasedOnAccountNumberResponse.getPayee().addAll(payees);
            serviceStatus.setMessage(resourceBundle.getString("payee.details") + findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());
            logger.info(resourceBundle.getString("payee.details") + findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());

        } catch (PayeeException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            logger.warn(resourceBundle.getString("no.payee")+ findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());
            serviceStatus.setMessage(e.getMessage());
        }
        findAllPayeeBasedOnAccountNumberResponse.setServiceStatus(serviceStatus);

        return findAllPayeeBasedOnAccountNumberResponse;
    }

    // finding all payee
    @PayloadRoot(namespace = url,localPart = "findAllPayeeReqRequest")
    @ResponsePayload
    public FindAllPayeeReqResponse listAllPayee(@RequestPayload FindAllPayeeReqRequest findAllPayeeRequest) {
        FindAllPayeeReqResponse findAllPayeeResponse=new FindAllPayeeReqResponse();
        ServiceStatus serviceStatus=new ServiceStatus();
        List<PayeeRequired> payees =new ArrayList<>();
        try {
            List<com.paymentdao.payment.entity.Payee> daoPayee = paymentTransferImplementation.findAllPayee();
            daoPayee.forEach(each -> {
                PayeeRequired currentPayee = new PayeeRequired();
                BeanUtils.copyProperties(each, currentPayee);
                payees.add(currentPayee);
            });
           // serviceStatus.setStatus(resourceBundle.getString("success.payee") + " " + HttpStatus.OK.value());
            serviceStatus.setStatus( HttpServletResponse.SC_OK);
            serviceStatus.setMessage(resourceBundle.getString("Payee.fetched"));
            logger.info(resourceBundle.getString("Payee.fetched"));
        }catch (PayeeException e) {
            serviceStatus.setStatus(HttpServletResponse.SC_NO_CONTENT);
            logger.warn(resourceBundle.getString("payee.notfound"));
            serviceStatus.setMessage(e.getMessage());
        }
        findAllPayeeResponse.setServiceStatus(serviceStatus);
        findAllPayeeResponse.getPayeeRequired().addAll(payees);
        return findAllPayeeResponse;
    }

}
