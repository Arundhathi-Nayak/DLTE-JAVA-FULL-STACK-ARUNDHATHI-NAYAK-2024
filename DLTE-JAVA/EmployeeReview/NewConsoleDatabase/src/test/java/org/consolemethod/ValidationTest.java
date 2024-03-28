package org.consolemethod;

import org.junit.Test;
import org.validation.Validation;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ValidationTest {
    private Validation validation = new Validation();

    @Test
    public void testIsValidEmail() {
        assertTrue(validation.isValidEmail("aru123@gmail.com"));
        assertFalse(validation.isValidEmail("invalid-email"));
        assertFalse(validation.isValidEmail(""));
    }

    @Test
    public void testIsValidPhoneNumber() {
        assertTrue(validation.isValidPhoneNumber(1234567890L));
        assertFalse(validation.isValidPhoneNumber(0L));
        assertFalse(validation.isValidPhoneNumber(-1234567890L));
    }

    @Test
    public void testIsValidPin() {
        assertTrue(validation.isValidPin(123456));
        assertFalse(validation.isValidPin(0));
        assertFalse(validation.isValidPin(-123456));
    }
}
