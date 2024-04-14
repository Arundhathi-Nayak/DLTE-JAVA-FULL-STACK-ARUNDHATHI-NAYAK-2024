package com.payment.webservice.restservices;

import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import services.payee.Payee;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@RestController
@ComponentScan("com.paymentdao.payment")
@RequestMapping("/payees")

//http://localhost:8082/payees/delete
public class PayeeController {
    org.slf4j.Logger logger= LoggerFactory.getLogger(PayeeController.class);
    ResourceBundle resourceBundle= ResourceBundle.getBundle("account");
    @Autowired
    private PaymentTransferRepository paymentTransferImplementation;
    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePayee(@Valid  @RequestBody Payee payee) {
        try {
            paymentTransferImplementation.deletePayee(payee.getPayeeId(), payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());
            logger.info(resourceBundle.getString("delete.success"));
            return ResponseEntity.ok(resourceBundle.getString("payee.add")+ payee.getPayeeName()+" " +resourceBundle.getString("delete.success"));
        } catch (PayeeException payeeException) {
            logger.warn(resourceBundle.getString("Payee.not.found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payeeException.getMessage());
        }

    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
