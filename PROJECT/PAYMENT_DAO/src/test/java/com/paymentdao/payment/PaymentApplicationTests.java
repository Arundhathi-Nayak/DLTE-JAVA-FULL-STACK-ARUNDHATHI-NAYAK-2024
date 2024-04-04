package com.paymentdao.payment;

import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.service.PaymentTransferImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLSyntaxErrorException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class PaymentApplicationTests {

    @Mock
    JdbcTemplate jdbcTemplate;
    @InjectMocks
    PaymentTransferImplementation paymentTransferImplementation;
    @Test
    void testFindAll() throws SQLSyntaxErrorException {
        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
        Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
        Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
        Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
        List<Payee> payees= Stream.of(payee1,payee3).collect(Collectors.toList());
        given(jdbcTemplate.query(eq("select payee_account_number,payee_name from MYBANK_APP_Payee where sender_account_number=?"),
                eq(new Object[]{213456789654L}),
                any(PaymentTransferImplementation.PayeeMapper.class))).willReturn(payees);

        // Call the method under test
        List<Payee> actualList = paymentTransferImplementation.findAllPayee(213456789654L);

        // Assert that the actual list contains the expected payee account number
        assertSame(payees.size(),actualList.size());
        assertEquals(543212345678L, actualList.get(0).getPayeeAccountNumber()); //success
        //assertEquals(643212345678L, actualList.get(0).getPayeeAccountNumber()); //failure
    }


}
