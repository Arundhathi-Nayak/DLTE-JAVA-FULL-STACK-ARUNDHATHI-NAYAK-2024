package com.paymentdao.payment;

import com.paymentdao.payment.entity.Account;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


// spring validation testing
public class AccountTest {
    @Test
    public void testAccountValidation() {
        Account account = new Account();
        account.setAccountId(1);
        account.setCustomerId(1);
        account.setAccountType("Checking");
    //    account.setAccountType(""); // failure
        account.setAccountNumber(123456789012L);
        account.setAccountStatus("Active");
        account.setAccountBalance(1000);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Account>> accounts = validator.validate(account);

        assertTrue(accounts.isEmpty(), "There should be no violations");

    }
}
