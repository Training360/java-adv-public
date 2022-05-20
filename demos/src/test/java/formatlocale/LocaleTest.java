package formatlocale;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocaleTest {

    @Test
    public void testLocale() {
        Locale locale = new Locale("hu", "HU");
        assertEquals("hu", locale.getLanguage());
        assertEquals("HU", locale.getCountry());
    }
}
