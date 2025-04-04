package dateduration;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class WorkHoursCalculatorTest {

    WorkHoursCalculator whc = new WorkHoursCalculator();

    @Test
    void testAddWorkTimeAsDuration() {
        whc.addWorkTime(Duration.ofHours(6));
        assertEquals(360L, whc.getWorkDuration().toMinutes());
    }

    @Test
    void testAddWorkTimeAsIntegers() {
        whc.addWorkTime(1, 2, 30);
        whc.addWorkTime(0, 2, 0);
        assertEquals(1710L, whc.getWorkDuration().toMinutes());
    }

    @Test
    void emptyParameterShouldThrowException() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new WorkHoursCalculator().addWorkTime(""));
        assertEquals("Parameter must not be empty!", ex.getMessage());
    }

    @Test
    void invalidParameterShouldThrowException() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new WorkHoursCalculator().addWorkTime("01:02:30"));
        assertEquals("Parameter must match PnDTnHnM pattern, but found: 01:02:30", ex.getMessage());
    }

    @Test
    void testAddWorkTimeAsString() {
        whc.addWorkTime("P1DT2H30M");
        whc.addWorkTime("P0DT2H0M");
        assertEquals(1710L, whc.getWorkDuration().toMinutes());
    }

    @Test
    void testCalculateWorkHours() {
        whc.addWorkTime("P1DT2H30M");
        whc.addWorkTime("P0DT2H0M");
        assertEquals(28, whc.calculateWorkHours());
    }

    @Test
    void testToString() {
        whc.addWorkTime("P1DT2H30M");
        assertEquals("Days: 1, hours: 2, minutes: 30", whc.toString());
    }
}
