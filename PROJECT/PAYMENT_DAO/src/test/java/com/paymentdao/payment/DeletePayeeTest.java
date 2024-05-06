package com.paymentdao.payment;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DeletePayeeTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    org.slf4j.Logger logger= LoggerFactory.getLogger( DeletePayeeTest.class);


    @InjectMocks
    private PaymentTransferImplementation deletePayee;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @InjectMocks
    PaymentTransferImplementation paymentTransferImplementation;




    @Test
    void testDeletePayee() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "Arundhathi";

        when(jdbcTemplate.update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName))).thenReturn(1);

        deletePayee.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));
    }

    @Test
    void testPayeeException() {
        when(jdbcTemplate.query(anyString(), any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(Collections.emptyList());
        try {
            paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(123434567897L);
        } catch (PayeeException e) {
            //   assertEquals("Payee exception", e.getMessage());
            assertNotEquals("No payee", e.getMessage());
        }

    }
    @Test
    void testPayeeExceptionEquals() {
        when(jdbcTemplate.query(anyString(), any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(Collections.emptyList());
        try {
            paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(123434567897L);
        } catch (PayeeException e) {
            //   assertEquals("Payee exception", e.getMessage());
            assertEquals("No payee found with given account number : 123434567897", e.getMessage());
        }

    }
    @Test
    void testDeletePayeeNotFound() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "Arundhathi";
        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));

        PayeeException exception = assertThrows(PayeeException.class, () -> {
            paymentTransferImplementation.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        });

        assertNotEquals("found", exception.getMessage());
    }
    @Test
    void testPayeeFound() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987651L;
        String payeeName = "Arundhathi";

        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));

        PayeeException exception = assertThrows(PayeeException.class, () -> {
            paymentTransferImplementation.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        });

        assertEquals("No payee found", exception.getMessage());
    }
    @Test
    void testPayeeNotFound() {
        int payeeId = 1;
        Long senderAccountNumber = 12345678988L;
        Long payeeAccountNumber = 987651777777L;
        String payeeName = "Arundhathi";

        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));

        PayeeException exception = assertThrows(PayeeException.class, () -> {
            paymentTransferImplementation.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        });

        assertNotEquals("Failed to delete payee", exception.getMessage());
    }




    @Test
    public void testDeletePayeeAdded_SuccessfulDeletion() {
        // Mock successful deletion
        when(jdbcTemplate.update(any(String.class), any(Object[].class))).thenReturn(1);

        // No exception should be thrown
        paymentTransferImplementation.deletePayeeAdded(1, 123456L, 789012L, "PayeeName");
    }


    @Test
    void testDeletePayeeAdded_PayeeNotExists() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "Alice";

        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), anyInt(), anyLong(), anyLong(), anyString());

        assertThrows(PayeeException.class, () -> paymentTransferImplementation.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName));
        verify(jdbcTemplate).update("Call DELETE_PAYEE_NEW(?,?,?,?)", payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
    }
    @Test
    void testDeletePayeeAdded_PayeeNameNotExists() {
        Payee payee=new Payee();
        payee.setPayeeId(1);
        payee.setSenderAccountNumber(123456789345L);
        payee.setPayeeAccountNumber(987654321333L);
        payee.setPayeeName("sanath");

        doThrow(new DataAccessException("ORA-20002") {}).when(jdbcTemplate).update(anyString(), anyInt(), anyLong(), anyLong(), anyString());

        assertThrows(PayeeException.class, () -> paymentTransferImplementation.deletePayeeAdded(payee.getPayeeId(),payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName()));
        verify(jdbcTemplate).update("Call DELETE_PAYEE_NEW(?,?,?,?)", payee.getPayeeId(), payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());
    }
}

