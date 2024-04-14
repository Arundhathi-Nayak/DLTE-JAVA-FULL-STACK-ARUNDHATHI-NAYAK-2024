package com.payment.webservice;

import com.payment.webservice.restservices.PayeeController;

import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.payee.Payee;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class PayeeControllerTest {
    @Mock
    private PaymentTransferRepository paymentTransferImplementation;

    @InjectMocks
    private PayeeController payeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDeletePayee() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("account");
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(987654321L);
        payee.setPayeeName("Arundhathi");

        // Mocking a void method
        doNothing().when(paymentTransferImplementation).deletePayee(123, 123456789L, 987654321L, "Arundhathi");

        ResponseEntity<String> responseEntity = payeeController.deletePayee(payee);

        verify(paymentTransferImplementation, times(1)).deletePayee(123, 123456789L, 987654321L, "Arundhathi");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(resourceBundle.getString("payee.add") + "Arundhathi" +" "+ resourceBundle.getString("delete.success"),
                responseEntity.getBody());
    }

   // @Test
    public void testDeletePayee_NotFound() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("account");
        Payee payee = new Payee();
        payee.setPayeeId(123);
        payee.setSenderAccountNumber(123456789L);
        payee.setPayeeAccountNumber(987654321L);
        payee.setPayeeName("Arundhathi");

        // Mocking a void method with exception
        doThrow(new PayeeException(resourceBundle.getString("Payee.not.found"))).when(paymentTransferImplementation)
                .deletePayee(123, 123456789L, 987654321L, "Arundhathi");

        ResponseEntity<String> responseEntity = payeeController.deletePayee(payee);

        verify(paymentTransferImplementation, times(1)).deletePayee(123, 123456789L, 987654321L, "Eeskha");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(resourceBundle.getString("Payee.not.found"), responseEntity.getBody());
    }

}
