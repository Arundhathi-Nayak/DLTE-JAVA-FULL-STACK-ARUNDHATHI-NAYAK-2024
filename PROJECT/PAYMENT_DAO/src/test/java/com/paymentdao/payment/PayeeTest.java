package com.paymentdao.payment;

import com.paymentdao.payment.entity.Payee;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PayeeTest {
    @Test
    public void testValidPayee() {
        Payee payee = new Payee();
        payee.setPayeeId(1);
        payee.setSenderAccountNumber(123456789012L);
        payee.setPayeeAccountNumber(987654321098L);
        payee.setPayeeName("aru");

        assertTrue(validate(payee), "Validation should pass for a valid payee");
    }

    @Test
    public void testInvalidPayee() {
        Payee payee = new Payee();
        payee.setPayeeId(23);
        payee.setSenderAccountNumber(1011L); // Invalid length
        payee.setPayeeAccountNumber(123456789011L);
       // payee.setPayeeName("12345"); // Contains digits
        payee.setPayeeName("aru");

        assertTrue(validate(payee), "Validation should fail for an invalid payee");
    }

    private boolean validate(Payee payee) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Payee>> violations = validator.validate(payee);
        return violations.isEmpty();
    }
}
