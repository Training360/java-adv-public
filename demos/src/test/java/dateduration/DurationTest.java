package dateduration;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DurationTest {

    @Test
    public void testDuration() {
        Duration duration = Duration.ofHours(2);
        assertEquals("PT2H", duration.toString());
    }

    @Test
    public void testBetween() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 12, 16, 0);
        LocalDateTime localDateTime1 = LocalDateTime.of(2018, 1, 12, 18, 0);
        Duration duration = Duration.between(localDateTime, localDateTime1);
        assertEquals("PT2H", duration.toString());
    }

    @Test
    public void testParse() {
        Duration duration = Duration.parse("PT2H");
        assertEquals("PT2H", duration.toString());
    }

    @Test
    public void testPlus() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 12, 16, 0);
        LocalDateTime localDateTime1 = localDateTime.plus(Duration.ofHours(2));
        assertEquals(LocalDateTime.of(2018, 1, 12, 18, 0), localDateTime1);

    }

    @Test
    public void testDurationPlus() {
        Duration duration = Duration.ofHours(2).plusMinutes(3);
        assertEquals(Duration.ofHours(3).plusMinutes(3).plusHours(-1), duration);
    }

    @Test
    public void testNormalize() {
        Duration duration = Duration.ofMinutes(250);
        assertEquals("PT4H10M", duration.toString());
    }
}
