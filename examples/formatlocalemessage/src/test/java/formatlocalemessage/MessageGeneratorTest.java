package formatlocalemessage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageGeneratorTest {

    @Test
    public void testGenerateForecastText() {
        assertEquals("Tomorrow expect light rain with 12  \u00B0C temperature in Budapest.",
                new MessageGenerator().generateForecastText("light rain", 12, "Budapest"));

    }

    @Test
    public void testGenerateLotteryAnnouncement() {
        //Then
        assertEquals("Yesterday John Smith won a staggering sum of 500 M",
                new MessageGenerator().generateLotteryAnnouncement("John Smith"));

    }
}
