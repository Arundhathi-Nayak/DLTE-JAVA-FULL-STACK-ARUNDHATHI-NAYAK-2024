//package com.payment.webservice;
//
//import com.payment.webservice.soapconfig.SoapPhase;
//import com.paymentdao.payment.entity.Payee;
//import com.paymentdao.payment.remote.PaymentTransferRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import services.payee.*;
//
//import java.sql.SQLSyntaxErrorException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//
//// testing for request and responses
//@SpringBootTest
//@ExtendWith(MockitoExtension.class)
//public class EndPointTesting {
//    @Mock
//private PaymentTransferRepository paymentTransferRepository;
//
//    @InjectMocks
//    private SoapPhase soapPhase;
//
//    // find all by account number
//    @Test
//    public void testListPayeeBasedOnAccountNumber() throws SQLSyntaxErrorException {
//        List<Payee> payees = new ArrayList<>();
//        Payee payee = new Payee();
//        payee.setPayeeId(120);
//        payee.setSenderAccountNumber(123456789L);
//        payee.setPayeeAccountNumber(987456789L);
//        payee.setPayeeName("Arururu");
//        payees.add(payee);
//
//        when(paymentTransferRepository.findAllPayeeBasedOnAccountNumber(123456789L)).thenReturn(payees);
//
//       FindAllPayeeBasedOnAccountNumberRequest request = new FindAllPayeeBasedOnAccountNumberRequest();
//        request.setSenderAccount(123456789L);
//
//
//     FindAllPayeeBasedOnAccountNumberResponse response=soapPhase.listPayeeBasedOnAccountNumber(request);
//
//        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
//        assertEquals("Payee details for account number 123456789", response.getServiceStatus().getMessage());
//      //  assertEquals(1, response.getPayee().size());
//        assertEquals(123, response.getPayee().size());   //fail
//        assertEquals("Arururu", response.getPayee().get(0).getPayeeName());
//    }
//
//    // display all payee
//    @Test
//    public void testDisplayAllPayee() throws SQLSyntaxErrorException {
//        List<Payee> payees = new ArrayList<>();
//        Payee payee1 = new Payee();
//        payee1.setPayeeId(120);
//        payee1.setSenderAccountNumber(123456789L);
//        payee1.setPayeeAccountNumber(987456789L);
//        payee1.setPayeeName("Arururu");
//
//        Payee payee2 = new Payee();
//        payee2.setPayeeId(121);
//        payee2.setSenderAccountNumber(123456789L);
//        payee2.setPayeeAccountNumber(987654321L);
//        payee2.setPayeeName("Sanananana");
//
//        payees.add(payee1);
//        payees.add(payee2);
//
//        when(paymentTransferRepository.findAllPayee()).thenReturn(payees);
//
//        FindAllPayeeReqRequest request = new FindAllPayeeReqRequest();
//
//        FindAllPayeeReqResponse response = soapPhase.listAllPayee(request);
//
//        assertEquals(HttpStatus.OK.value(), response.getServiceStatus().getStatus());
//        assertEquals("Payee fetched Successfully", response.getServiceStatus().getMessage());
//        assertEquals(2, response.getPayeeRequired().size());
//
//        PayeeRequired responsePayee1 = response.getPayeeRequired().get(0);
//        assertEquals("Arururu", responsePayee1.getPayeeName());
//
//        PayeeRequired responsePayee2 = response.getPayeeRequired().get(1);
//        assertEquals("Sanananana", responsePayee2.getPayeeName());
//    }
//}
