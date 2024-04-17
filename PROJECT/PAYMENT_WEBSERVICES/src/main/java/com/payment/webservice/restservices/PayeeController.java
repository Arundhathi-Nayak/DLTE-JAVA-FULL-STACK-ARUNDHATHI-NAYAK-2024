package com.payment.webservice.restservices;

import com.paymentdao.payment.entity.Customer;
import com.paymentdao.payment.entity.Payee;
import com.paymentdao.payment.exception.PayeeException;
import com.paymentdao.payment.remote.DeletePayeeRepository;
import com.paymentdao.payment.remote.PaymentTransferRepository;
import com.paymentdao.payment.security.MyBankOfficials;
import com.paymentdao.payment.security.MyBankOfficialsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;



@RestController
@ComponentScan("com.paymentdao.payment")
@RequestMapping("/payees")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payee added successfully"),
        @ApiResponse(responseCode = "404", description = "Payee not found"),
})

public class PayeeController {
    org.slf4j.Logger logger = LoggerFactory.getLogger(PayeeController.class);
    ResourceBundle resourceBundle = ResourceBundle.getBundle("account");
    @Autowired
    private DeletePayeeRepository deletePayee;
    @Autowired
    MyBankOfficialsService service;

    @Autowired
    private PaymentTransferRepository paymentTransferImplementation;

    @DeleteMapping("/delete/payee")
    public ResponseEntity<String> deletePayeeNew(@Valid @RequestBody Payee payee) {

        try {
            paymentTransferImplementation.deletePayee(payee.getPayeeId(), payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());
            logger.info(resourceBundle.getString("delete.success"));
            return ResponseEntity.ok(resourceBundle.getString("payee.add") + payee.getPayeeName() + " " + resourceBundle.getString("delete.success"));
        } catch (PayeeException payeeException) {
            logger.warn(resourceBundle.getString("Payee.not.found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payeeException.getMessage());
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deletePayee(@Valid @RequestBody Payee payee) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MyBankOfficials customer = service.findByUsername(username);
        List<Long> senderAccountNumber = service.getAccountNumbersByCustomerId(customer.getCustomerId());

        if (senderAccountNumber.contains(payee.getSenderAccountNumber())) {
            try {
                deletePayee.deletePayee(payee.getPayeeId(), payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());
                logger.info(resourceBundle.getString("delete.success"));
                return ResponseEntity.ok(resourceBundle.getString("payee.add") + payee.getPayeeName() + " " + resourceBundle.getString("delete.success"));
            } catch (PayeeException payeeException) {
                logger.warn(resourceBundle.getString("Payee.not.found"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payeeException.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("no.account"));
        }

    }

    @DeleteMapping("/delete/New")
    public ResponseEntity<String> deletePayeeValid(@Valid @RequestBody Payee payee) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MyBankOfficials customer = service.findByUsername(username);
        List<Long> senderAccountNumber = service.getAccountNumbersByCustomerId(customer.getCustomerId());

        if (senderAccountNumber.contains(payee.getSenderAccountNumber())) {
            try {
                deletePayee.deletePayeeAdded(payee.getPayeeId(), payee.getSenderAccountNumber(), payee.getPayeeAccountNumber(), payee.getPayeeName());
                logger.info(resourceBundle.getString("delete.success"));
                return ResponseEntity.ok(resourceBundle.getString("payee.add") + payee.getPayeeName() + " " + resourceBundle.getString("delete.success"));
            } catch (PayeeException payeeException) {
                logger.warn(resourceBundle.getString("payee.notExists"));
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(payeeException.getMessage());
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resourceBundle.getString("no.account"));
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
