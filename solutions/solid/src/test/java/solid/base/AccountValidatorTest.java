package solid.base;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountValidatorTest {
    @Test
    public void testIsValidWithShortUsername() {
        AccountValidator accountValidator = new AccountValidator();

        assertFalse(accountValidator.isValid("12345"));
    }

    @Test
    public void testIsValidWithWrongUsername() {
        AccountValidator accountValidator = new AccountValidator();

        assertFalse(accountValidator.isValid("12 34 5"));
    }

    @Test
    public void testIsValidWitGoodUsername() {
        AccountValidator accountValidator = new AccountValidator();

        assertTrue(accountValidator.isValid("123456"));
    }

    @Test
    public void testIsValidWithNull() {
        AccountValidator accountValidator = new AccountValidator();

        assertFalse(accountValidator.isValid(null));
    }

    @Test
    public void testIsValidWithEmptyString() {
        AccountValidator accountValidator = new AccountValidator();

        assertFalse(accountValidator.isValid(""));
    }
}