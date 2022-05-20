package formatnumberformat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceConverterTest {

    private PriceConverter priceConverter = new PriceConverter();

    @Test
    public void testToString() {
        assertEquals("10,12", priceConverter.toString(10.12));
        assertEquals("10,00", priceConverter.toString(10));
        assertEquals("10\u00A0000,12", priceConverter.toString(10_000.12));
        assertEquals("10,12", priceConverter.toString(10.1234));

        assertEquals("10,12", priceConverter.toString(10.125));
        assertEquals("10,13", priceConverter.toString(10.135));
    }

    @Test
    public void testToCurrencyString() {
        assertEquals("10,12\u00A0Ft", priceConverter.toCurrencyString(10.12));
        assertEquals("10,12\u00A0Ft", priceConverter.toCurrencyString(10.1234));
        assertEquals("10,00\u00A0Ft", priceConverter.toCurrencyString(10));
    }

    @Test
    public void testToDouble() {
        assertEquals(10D, priceConverter.toDouble("10"));
        assertEquals(10.12345, priceConverter.toDouble("10,12345"), 0.000005);
        assertEquals(10_000.12345, priceConverter.toDouble("10\u00A0000,12345"), 0.000005);
    }
}
