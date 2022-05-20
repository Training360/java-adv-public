package genericmethod;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ArrayMiddleObjectFinderTest {


    @Test
    public void shouldThrowExceptionIfNull() {
        assertThrows(NullPointerException.class, () -> new ArrayMiddleObjectFinder().findMiddleObject(null));
    }

    @Test
    public void shouldThrowExceptionIfEmptyArgument() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayMiddleObjectFinder().findMiddleObject());
    }


    @Test
    public void evenLengthArrayShouldThrowException() throws IllegalArgumentException {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new ArrayMiddleObjectFinder().findMiddleObject("", ""));
        assertEquals("Even number of elements, no middle object can be found!", ex.getMessage());
    }


    @Test
    public void testReturnedObject() {

        //Given
        String[] words = new String[]{"First word", "Middle word", "Last word"};
        LocalDate[] birthDates = new LocalDate[]{LocalDate.of(2000, 12, 12), LocalDate.of(1996, 1, 12), LocalDate.of(1987, 10, 17)};

        //When
        ArrayMiddleObjectFinder finder = new ArrayMiddleObjectFinder();

        //Then
        assertEquals("Middle word", finder.findMiddleObject(words));
        assertEquals("1996-01-12", finder.findMiddleObject(birthDates).toString());
    }
}
