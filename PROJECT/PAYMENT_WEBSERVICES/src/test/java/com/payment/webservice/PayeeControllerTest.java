package com.payment.webservice;

import com.payment.webservice.restservices.PayeeController;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.DeletePayeeRepository;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ResourceBundle;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
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

        ResponseEntity<String> responseEntity = payeeController.deletePayeeNew(payee);

        verify(paymentTransferImplementation, times(1)).deletePayee(123, 123456789L, 987654321L, "Arundhathi");
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(resourceBundle.getString("payee.add") + "Arundhathi" +" "+ resourceBundle.getString("delete.success"),
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

        ResponseEntity<String> responseEntity = payeeController.deletePayeeNew(payee);

        verify(paymentTransferImplementation, times(1)).deletePayee(123, 123456789L, 987654321L, "Eeskha");
        Assert.assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        Assert.assertEquals(resourceBundle.getString("Payee.not.found"), responseEntity.getBody());
    }
}
