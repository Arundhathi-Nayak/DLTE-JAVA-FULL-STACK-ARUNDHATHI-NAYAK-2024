package com.payment.webservice;

import com.payment.webservice.soapconfig.SoapPhase;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import services.payee.*;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


// testing for request and responses
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EndPointTesting {
    @Mock
    private PaymentTransferRepository paymentTransferRepository;

    @InjectMocks
    private SoapPhase soapPhase;

    @Mock
    MyBankOfficialsService myBankOfficialsService;


    // find all by account number
    @Test
    public void testListPayeeBasedOnAccountNumberFail()  {
        List<Payee> payees = new ArrayList<>();
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(987456789L);
        payee.setPayeeName("Arururu");
        payees.add(payee);
        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");


        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(12);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(12)).thenReturn(Collections.singletonList(123456789L));


        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(123456789L)).thenReturn(payees);

       FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        request.setSenderAccount(123456789L);


     FindAllPayeeBasedOnAccountNumberResponse response=soapPhase.listPayeeBasedOnAccountNumber(request);

        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
        assertEquals("Payee details for account number123456789", response.getServiceStatus().getMessage());
      //  assertEquals(1, response.getPayee().size());
        assertNotEquals(2, response.getPayee().size());   //fail
        assertEquals("Arururu", response.getPayee().get(0).getPayeeName());
    }
    @Test
    public void testListPayeeBasedOnAccountNumber()  {
        List<Payee> payees = new ArrayList<>();
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(987456789L);
        payee.setPayeeName("Arururu");
        payees.add(payee);

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");


        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(12);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(12)).thenReturn(Collections.singletonList(123456789L));


        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(123456789L)).thenReturn(payees);

        FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
        request.setSenderAccount(123456789L);


        FindAllPayeeBasedOnAccountNumberResponse response=soapPhase.listPayeeBasedOnAccountNumber(request);

        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
        assertEquals("Payee details for account number123456789", response.getServiceStatus().getMessage());
        assertEquals(1, response.getPayee().size());
        assertEquals("Arururu", response.getPayee().get(0).getPayeeName());
    }


}
