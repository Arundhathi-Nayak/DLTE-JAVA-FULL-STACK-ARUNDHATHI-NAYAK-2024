package com.paymentdao.payment;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
   // @Test
    void testDeletePayeeNotFound() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "Arundhathi";
        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));

        PayeeException exception = assertThrows(PayeeException.class, () -> {
            deletePayee.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        });

        assertEquals("No payee found", exception.getMessage());
    }
  //  @Test
    void testPayeeNotFound() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987651L;
        String payeeName = "Arundhathi";

        doThrow(new DataAccessException("ORA-20001") {}).when(jdbcTemplate).update(anyString(), eq(payeeId), eq(senderAccountNumber), eq(payeeAccountNumber), eq(payeeName));

        PayeeException exception = assertThrows(PayeeException.class, () -> {
            deletePayee.deletePayeeAdded(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);
        });

        assertEquals("Failed to delete the payee", exception.getMessage());
    }
}

