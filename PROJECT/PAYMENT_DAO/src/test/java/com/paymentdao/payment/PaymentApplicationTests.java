//package com.paymentdao.payment;
//
//import com.paymentdao.payment.entity.Payee;
//import com.paymentdao.payment.exception.PayeeException;
//import com.paymentdao.payment.service.PaymentTransferImplementation;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import java.sql.SQLSyntaxErrorException;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.junit.jupiter.api.Assertions.assertSame;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//// Dao testing find all and find by particular account number
//@SpringBootTest
//class PaymentApplicationTests {
//
//    @Mock
//    JdbcTemplate jdbcTemplate;
//    @InjectMocks
//    PaymentTransferImplementation paymentTransferImplementation;
//    @Test
//    void testFindAll()  {
//        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
//        Payee payee2=new Payee(102,765423123564L,765432345678L,"Arundhathi");
//
//        List<Payee> payees=Stream.of(payee1,payee2).collect(Collectors.toList());
//        when(jdbcTemplate.query(eq("select * from MYBANK_APP_Payee"),
//                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
//        // Call the method under test
//        List<Payee> actualList = paymentTransferImplementation.findAllPayee();
//
//        // Assert that the actual list contains the expected payee account number
//        assertEquals(543212345678L, actualList.get(0).getPayeeAccountNumber());
//    }
//    @Test
//
//    void testFindAllBasedOnAccount()  {
//        Payee payee1=new Payee(101,213456789654L,543212345678L,"Eeksha");
//        Payee payee2=new Payee(102,765423123564L,765432345678L,"Divija");
//        Payee payee3=new Payee(103,213456789654L,987654321234L,"Arundhathi");
//        Payee payee4=new Payee(104,765423123564L,543567543456L,"Anu");
//        List<Payee> payees=Stream.of(payee1,payee3).collect(Collectors.toList());
////        given(jdbcTemplate.query(eq("select * from MYBANK_APP_Payee where sender_account_number=?"),
////                eq(new Object[]{213456789654L}),
////                any(PaymentTransferImplementation.PayeeMapper.class))).willReturn(payees);
//
//        when(jdbcTemplate.query(anyString(),any(Object[].class),
//                any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(payees);
//
//        // Call the method under test
//        List<Payee> actualList = paymentTransferImplementation.findAllPayeeBasedOnAccountNumber(213456789654L);
//        // Assert that the actual list contains the expected payee account number
//        assertEquals(543212345678L, actualList.get(0).getPayeeAccountNumber());
//    }
//    @Test
//    void testPayeeException() {
//        when(jdbcTemplate.query(anyString(), any(PaymentTransferImplementation.PayeeMapper.class))).thenReturn(Collections.emptyList());
//        try {
//            paymentTransferImplementation.findAllPayee();
//        } catch (PayeeException e) {
//         //   assertEquals("Payee exception", e.getMessage());
//            assertEquals("No payee", e.getMessage());
//        }
//
//    }
//
//
//}
