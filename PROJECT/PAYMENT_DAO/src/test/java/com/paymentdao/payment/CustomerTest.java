package com.paymentdao.payment;

import com.paymentdao.payment.entity.Customer;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {
    @Test
    public void testInvalidCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(null);
        customer.setCustomerName("Aru");
        customer.setCustomerAddress("123 Main St");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(1234567890L);
        customer.setUsername("aru123");
        customer.setPassword("Password@123");

        assertTrue(validate(customer), "valid true ");
    }

    @Test
    public void testValidCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(1);
        customer.setCustomerName("aru");
        customer.setCustomerAddress("123 Main St");
        customer.setCustomerStatus("Active");
        customer.setCustomerContact(12345678901L);
        customer.setUsername("aru");
        customer.setPassword("password123");

        assertFalse(validate(customer), " valid if true");
    }

    private boolean validate(Customer customer) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
        return violations.isEmpty();
    }
}
