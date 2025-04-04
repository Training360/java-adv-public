package datedaylight;

import org.junit.jupiter.api.Test;

import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


class WorkHoursCalculatorTest {

    public static final String PATTERN = "HHmm, dd MMM yyyy";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN, Locale.ENGLISH);

    @Test
    void testGetStartDateTime() {
        WorkHoursCalculator whc = new WorkHoursCalculator(2013, Month.MARCH, 5, 1, ZoneId.of("America/Chicago"));
        assertEquals("0100, 05 Mar 2013", FORMATTER.format(whc.getStartDateTime()));
    }

    @Test
    void testCalculateHoursInMarch() {
        Month month = Month.MARCH;
        WorkHoursCalculator whc = new WorkHoursCalculator(2013, month, 1, 1, ZoneId.of("America/Chicago"));
        assertEquals(359L, whc.calculateHours(2013, month, 16, 1));
    }

    @Test
    void testCalculateHoursInApril() {
        Month month = Month.APRIL;
        WorkHoursCalculator whc = new WorkHoursCalculator(2013, month, 1, 1, ZoneId.of("America/Chicago"));
        assertEquals(360L, whc.calculateHours(2013, month, 16, 1));
    }

    @Test
    void testCalculateHoursInNovember() {
        Month month = Month.NOVEMBER;
        WorkHoursCalculator whc = new WorkHoursCalculator(2013, month, 1, 1, ZoneId.of("America/Chicago"));
        assertEquals(361L, whc.calculateHours(2013, month, 16, 1));
    }

    @Test
    void testCalculateHoursBetweenMonths() {
        WorkHoursCalculator whc = new WorkHoursCalculator(2013, Month.MARCH, 1, 1, ZoneId.of("America/Chicago"));
        assertEquals(983L, whc.calculateHours(2013, Month.APRIL, 11, 1));
    }
}
