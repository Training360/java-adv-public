package datedaylight;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZoneTest {

    @Test
    public void testZoneId() {
        ZoneId zoneId = ZoneId.of("Europe/Budapest");
        assertTrue(ZoneId.getAvailableZoneIds().contains(zoneId.toString()));
    }

    @Test
    public void testZonedDateTime() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 12, 16, 0);
        ZonedDateTime zonedDateTime =
                ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Budapest"));
        assertEquals("2018-01-12T16:00+01:00[Europe/Budapest]", zonedDateTime.toString());
    }

    @Test
    public void testChange() {
        LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 12, 16, 0);
        ZonedDateTime zonedDateTime =
                ZonedDateTime.of(localDateTime, ZoneId.of("Europe/Budapest"));
        ZonedDateTime zonedDateTime1 =
                zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        assertEquals("2018-01-12T15:00Z[UTC]", zonedDateTime1.toString());
    }

    @Test
    public void testDaylightSaving() {
        ZonedDateTime zonedDateTime =
                ZonedDateTime.of(LocalDateTime.of(2017, 3, 26, 1, 59),
                        ZoneId.of("Europe/Budapest"));
        ZonedDateTime zonedDateTime1 = zonedDateTime.plusMinutes(1);
        assertEquals("2017-03-26T03:00+02:00[Europe/Budapest]", zonedDateTime1.toString());


    }
}
