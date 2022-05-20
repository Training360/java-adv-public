package formatlocaleregexp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegexpValidatorTest {


    @Test
    public void emptyParameterStringShouldThrowException() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new RegexpValidator().validateEmail("    "));
        assertEquals("Empty string passed!", ex.getMessage());
    }

    @Test
    public void nullStringShouldThrowExceptionInYearStringValidation() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new RegexpValidator().validateYear(null));
        assertEquals("Empty string passed!", ex.getMessage());
    }

    @Test
    public void testValidateEmail() {

        assertFalse(new RegexpValidator().validateEmail("email&email.com"));
        assertTrue(new RegexpValidator().validateEmail("email@email.com"));
        assertTrue(new RegexpValidator().validateEmail("e_m.a.I_l@email.com"));
        assertTrue(new RegexpValidator().validateEmail("email@email.email.hu.eu.com"));
    }

    @Test
    public void testValidateAcademicYear() {

        assertTrue(new RegexpValidator().validateAcademicYear("2014-2015"));
        assertFalse(new RegexpValidator().validateAcademicYear("2014/2015"));
        assertFalse(new RegexpValidator().validateAcademicYear("946-947"));
        assertFalse(new RegexpValidator().validateAcademicYear("2114-2115"));
    }

    @Test
    public void testValidateYear() {

        assertTrue(new RegexpValidator().validateYear("2014"));
        assertFalse(new RegexpValidator().validateYear("3014"));
        assertFalse(new RegexpValidator().validateYear("896"));
    }
}
