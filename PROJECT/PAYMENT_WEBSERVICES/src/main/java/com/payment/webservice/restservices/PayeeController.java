package com.payment.webservice.restservices;

import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.payee.Payee;

import java.util.ResourceBundle;

@RestController
@ComponentScan("com.paymentdao.payment")
@RequestMapping("/payees")
public class PayeeController {
    org.slf4j.Logger logger= LoggerFactory.getLogger(PayeeController.class);
    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");
    @Autowired
    private PaymentTransferRepository paymentTransferImplementation;
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePayee(@RequestBody Payee payee) {
        try {
            paymentTransferImplementation.deletePayee(payee.getPayeeId(), payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());
            logger.info(resourceBundle.getString("delete.success"));
            return ResponseEntity.ok(resourceBundle.getString("payee.add")+ payee.getPayeeName() +resourceBundle.getString("delete.success"));
        } catch (PayeeException payeeException) {
            logger.warn(resourceBundle.getString("Payee.not.found"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payeeException.getMessage());
        }

    }

}
