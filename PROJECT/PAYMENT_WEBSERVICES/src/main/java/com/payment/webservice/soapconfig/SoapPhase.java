package com.payment.webservice.soapconfig;

import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import services.payee.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Endpoint
@ComponentScan("com.paymentdao.payment")

//implementation of request and responses

//http://localhost:8082/payeerepo/payee.wsdl

//http://localhost:7001/webservice-0.0.1-SNAPSHOT/payeerepo/payee.wsdl

//http://localhost:8082/v3/api-docs

//http://localhost:7001/webservice-0.0.1-SNAPSHOT/v3/api-docs    // change war file name while deploying



public class SoapPhase {
    private final String url="http://payee.services";
    @Autowired
    private PaymentTransferRepository paymentTransferImplementation;

    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");

    org.slf4j.Logger logger= LoggerFactory.getLogger(SoapPhase.class);

    @Autowired
    MyBankOfficialsService service;

    //display all details based on particular user account number
    @PayloadRoot(namespace = url,localPart = "findAllPayeeBasedOnAccountNumberRequest")
    @ResponsePayload
    public FindAllPayeeBasedOnAccountNumberResponse listPayeeBasedOnAccountNumber(@Valid @RequestPayload FindAllPayeeBasedOnAccountNumberRequest findAllPayeeBasedOnAccountNumberRequest) {
        FindAllPayeeBasedOnAccountNumberResponse findAllPayeeBasedOnAccountNumberResponse = new FindAllPayeeBasedOnAccountNumberResponse();
        ServiceStatus serviceStatus = new ServiceStatus();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MyBankOfficials customer = service.findByCustomer(username);
        List<Long> senderAccountNumber = service.getAccountNumbersByCustomerId(customer.getCustomerId());


        if (senderAccountNumber.contains(findAllPayeeBasedOnAccountNumberRequest.getSenderAccount())) {
            List<Payee> payees = new ArrayList<>();

            try {
                List<com.paymentdao.payment.entity.Payee> bakendPayee = paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());

                bakendPayee.forEach(each -> {
                    Payee currentPayee = new Payee();
                    BeanUtils.copyProperties(each, currentPayee);
                    payees.add(currentPayee);
                });
                serviceStatus.setStatus(HttpServletResponse.SC_OK);
                findAllPayeeBasedOnAccountNumberResponse.getPayee().addAll(payees);
                if (!payees.isEmpty()) {
                    for (Payee payee : payees) {
                        logger.info("Current Username-"+username+"Payee details: PayeeId - " + payee.getPayeeId() +
                                ", Sender Account Number - " + payee.getSenderAccountNumber() +
                                ", Payee Account Number - " + payee.getPayeeAccountNumber() +
                                ", Payee Name - " + payee.getPayeeName());
                    }
                }
                serviceStatus.setMessage(resourceBundle.getString("payee.details") + findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());
                logger.info(resourceBundle.getString("payee.details") + findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());

            } catch (PayeeException e) {
                serviceStatus.setStatus(HttpServletResponse.SC_OK);  // 204 no content
                logger.warn(resourceBundle.getString("payee.error.one")+e.getMessage());
                serviceStatus.setMessage(resourceBundle.getString("payee.error.one")+e.getMessage());
            }
            findAllPayeeBasedOnAccountNumberResponse.setServiceStatus(serviceStatus);
            return findAllPayeeBasedOnAccountNumberResponse;
        } else {
            serviceStatus.setStatus(HttpStatus.OK.value());
            serviceStatus.setMessage(resourceBundle.getString("payee.error.three")+ resourceBundle.getString("no.account") + " " + findAllPayeeBasedOnAccountNumberRequest.getSenderAccount());
            findAllPayeeBasedOnAccountNumberResponse.setServiceStatus(serviceStatus);
            return findAllPayeeBasedOnAccountNumberResponse;
        }
    }
}

