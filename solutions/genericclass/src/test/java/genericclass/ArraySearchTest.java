package genericclass;

import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.NumberUp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArraySearchTest {


    @Test
    public void shouldThrowExceptionIfNull() {
        assertThrows(NullPointerException.class, () -> new ArraySearch().getFirstAndLastWord(null));
    }

    @Test
    public void shouldThrowExceptionIfEmptyArgument() {
        assertThrows(IllegalArgumentException.class, () -> new ArraySearch().getFirstAndLastWord(new String[]{}));
    }

    @Test
    public void testReturnedDataPairObject() {

        //Given
        String[] words = new String[]{"First word", "Middle word", "Last word"};

        //When
        DataPair<String> firstAndLast = new ArraySearch().getFirstAndLastWord(words);

        //Then
        assertEquals("First word", firstAndLast.getFirstObject());
        assertEquals("Last word", firstAndLast.getSecondObject());
    }

    @Test
    public void testReturnedDataPairObjectWithSingleObject() {

        //Given
        String[] words = new String[]{"First word"};

        //When
        DataPair<String> firstAndLast = new ArraySearch().getFirstAndLastWord(words);

        //Then
        assertEquals("First word", firstAndLast.getFirstObject());
        assertEquals("First word", firstAndLast.getSecondObject());
    }
}
