package com.paymentdao.payment;

import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class DeletePayeeTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private SimpleJdbcCall simpleJdbcCall;

    @InjectMocks
    private PaymentTransferImplementation paymentTransferImplementation;

    @Test
    public void testDeletePayee() {
        int payeeId = 1;
        Long senderAccountNumber = 123456789L;
        Long payeeAccountNumber = 987654321L;
        String payeeName = "John Doe";

        SqlParameterSource inParams = new MapSqlParameterSource()
                .addValue("p_payee_id", payeeId)
                .addValue("p_sender_account_number", senderAccountNumber)
                .addValue("p_payee_account_number", payeeAccountNumber)
                .addValue("p_payee_name", payeeName);

        Mockito.when(new SimpleJdbcCall(jdbcTemplate).withProcedureName(anyString())).thenReturn(simpleJdbcCall);
        Mockito.when(simpleJdbcCall.execute(any(SqlParameterSource.class))).thenReturn(null);

        paymentTransferImplementation.deletePayee(payeeId, senderAccountNumber, payeeAccountNumber, payeeName);

        verify(simpleJdbcCall).execute(inParams);
    }
}

