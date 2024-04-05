//package com.paymentdao.payment.controller;
//
//import com.paymentdao.payment.entity.Payee;
//import com.paymentdao.payment.service.PaymentTransferImplementation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.sql.SQLSyntaxErrorException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/payment")
//public class PaymentTansaferController {
//  @Autowired
//    PaymentTransferImplementation paymentTransferImplementation;
//    @GetMapping("/accountNumber/{number}")
//
//    public List<Payee> findBySender(@PathVariable("number") Long number) throws SQLSyntaxErrorException {
//        return paymentTransferImplementation.findAllPayee(number);
//    }
//}
//
////@ComponentScan("com.paymentdao.payment")
