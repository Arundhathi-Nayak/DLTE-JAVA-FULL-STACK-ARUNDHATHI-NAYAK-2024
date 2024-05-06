package com.payment.webservice;

import com.payment.webservice.restservices.PayeeController;
import com.payment.webservice.soapconfig.SoapPhase;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import services.payee.FindAllPayeeBasedOnAccountNumberRequest;
import services.payee.FindAllPayeeBasedOnAccountNumberResponse;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PaymentControllerTest {

    @MockBean
    private PaymentTransferRepository paymentTransferRepository;

    @InjectMocks
    private PayeeController paymentRestController;

//    private MockMvc mockMvc;

    @Mock
    MyBankOfficialsService myBankOfficialsService;

    @InjectMocks
    private SoapPhase soapPhase;

    @Test
    public void testDeletePayee() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("account");

        // Mock data
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789123L);
        payee.setPayeeAccountNumber(987654321123L);
        payee.setPayeeName("Arundhathi");

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");


        // Mock service behavior
        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(123);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
       when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
       when(myBankOfficialsService.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(123456789123L));
       // Mock the deletePayeeImplementation behavior

       doNothing().when(paymentTransferRepository).deletePayeeAdded(123, 123456789123L, 987654321123L, "Arundhathi");

        ResponseEntity<String> responseEntity = paymentRestController.deletePayeeValid(payee);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(resourceBundle.getString("payee.add") + "Arundhathi" + " " + resourceBundle.getString("delete.success"),
                responseEntity.getBody());
    }
    @Test
    public void testDeletePayeeFailed() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("account");

        // Mock data
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789123L);
        payee.setPayeeAccountNumber(987654321123L);
        payee.setPayeeName("Arundhathi");

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");


        // Mock service behavior
        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(123);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(123456789123L));
        // Mock the deletePayeeImplementation behavior

        doNothing().when(paymentTransferRepository).deletePayeeAdded(123, 123456789123L, 987654321123L, "Arundhathi");

        ResponseEntity<String> responseEntity = paymentRestController.deletePayeeValid(payee);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotEquals(resourceBundle.getString("payee.add") ,
                responseEntity.getBody());
    }

    @Test
    public void testDeletePayeeFailedException() {
        // Mock data
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789123L);
        payee.setPayeeAccountNumber(987654321123L);
        payee.setPayeeName("Arundhathi");

        // Mock authentication
        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn("testUser");

        // Mock service behavior
        MyBankOfficials customer = new MyBankOfficials();
        customer.setCustomerId(123);
        customer.setCustomerName("Sanatah");
        customer.setCustomerAddress("karkala");
        customer.setCustomerStatus("active");
        customer.setCustomerContact(8765432345L);
        customer.setUsername("testUser");
        customer.setPassword("12233");
        customer.setAttempts(1);
        when(myBankOfficialsService.findByCustomer("testUser")).thenReturn(customer);
        when(myBankOfficialsService.getAccountNumbersByCustomerId(123)).thenReturn(Collections.singletonList(123456789123L));

        // Mock the deletePayeeImplementation behavior to throw an exception
        doThrow(new PayeeException("Failed to delete payee")).when(paymentTransferRepository).deletePayeeAdded(123, 123456789123L, 987654321123L, "Arundhathi");

        ResponseEntity<String> responseEntity = paymentRestController.deletePayeeValid(payee);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("EXC002 :Failed to delete payee", responseEntity.getBody());
    }

}
