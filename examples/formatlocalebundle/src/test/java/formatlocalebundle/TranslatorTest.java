package formatlocalebundle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TranslatorTest {


    @Test
    public void missingKeyShouldThrowException() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Translator().englishToHungarian("door"));
        assertEquals("Key not found in bundle!", ex.getMessage());
    }

    @Test
    public void testTranslationFromEnglishToHungarian() {

        assertEquals("ablak", new Translator().englishToHungarian("window"));
    }

    @Test
    public void testTranslationFromEnglishToGerman() {

        assertEquals("Fenster", new Translator().englishToGerman("window"));
    }
}
