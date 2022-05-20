package solid.security;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsInjectionValidatorTest {
    @Test
    public void testNotValidWithNull() throws Exception {
        JsInjectionValidator jsInjectionValidator = new JsInjectionValidator();

        assertFalse(jsInjectionValidator.isValid(null));
    }

    @Test
    public void testIsValidWithEmptyString() throws Exception {
        JsInjectionValidator jsInjectionValidator = new JsInjectionValidator();

        assertTrue(jsInjectionValidator.isValid(""));
    }

    @Test
    public void testIsValidWithEvilInput() throws Exception {
        JsInjectionValidator jsInjectionValidator = new JsInjectionValidator();

        assertFalse(jsInjectionValidator.isValid("sdfasdf<script>"));
    }

    @Test
    public void testIsValidWithValidInput() throws Exception {
        JsInjectionValidator jsInjectionValidator = new JsInjectionValidator();

        assertTrue(jsInjectionValidator.isValid("asdfasdf<adfg>sdfasdf"));
    }
}