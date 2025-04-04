package formatnumberformat;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FormatLocaleTest {

    FormatLocale formatLocale = new FormatLocale(new Locale("hu", "HU"));

    @Test
    void nullLocaleCurrencyShouldThrowException() throws NullPointerException {
        Exception ex = assertThrows(NullPointerException.class, () -> formatLocale.formatCurrency(10.52, null));
        assertEquals("Locale must not be null", ex.getMessage());
    }

    @Test
    void nullLocalePercentageShouldThrowException() throws NullPointerException {
        Exception ex = assertThrows(NullPointerException.class, () -> formatLocale.formatPercentage(10.52, null));
        assertEquals("Locale must not be null", ex.getMessage());
    }

    @Test
    void incorrectArgumentShouldThrowException() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> formatLocale.formatCurrencyByLanguage(10.25, "", ""));
        assertEquals("Incorrect arguments!", ex.getMessage());
    }

    @Test
    void nonSupportedLocaleShouldThrowException() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> formatLocale.formatPercentageByLanguage(95, "aa", "AA"));
        assertEquals("Incorrect arguments!", ex.getMessage());
    }

    @Test
    void testFormatCurrency() {
        assertEquals("10,25\u00A0Ft", formatLocale.formatCurrency(10.25));
    }

    @Test
    void testFormatCurrencyByLocale() {
        assertEquals("$10.25", formatLocale.formatCurrency(10.25, Locale.US));
    }

    @Test
    void testFormatCurrencyByLanguage() {
        assertEquals("$10.25", formatLocale.formatCurrencyByLanguage(10.25, "en", "US"));
        assertEquals("10,25\u00A0Ft", formatLocale.formatCurrencyByLanguage(10.25, "hu", "HU"));
    }


    @Test
    void testFormatPercentage() {
        assertEquals("55%", formatLocale.formatPercentage(0.545));
    }

    @Test
    void testFormatPercentageByLocale() {
        assertEquals("55%", formatLocale.formatPercentage(0.545, Locale.US));
    }

    @Test
    void testFormatPercentageByLanguage() {
        assertEquals("55%", formatLocale.formatPercentageByLanguage(0.545, "en", "US"));
        assertEquals("55%", formatLocale.formatPercentageByLanguage(0.545, "hu", "HU"));
    }

    @Test
    void testNumberFormatting() {
        assertEquals("546,389.35", formatLocale.formatNumber(546389.3456, Locale.US));
    }
}
