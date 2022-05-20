package statespace;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EightQueenStateTest {

    @Test
    void testInvalid() {
        assertThrows(IllegalArgumentException.class,
                () -> new EightQueenState(0, 1, 2, 3, 4, 5, 6, 7, 8));
    }

    @Test
    void testNumber() {
        EightQueenState eightQueenState = new EightQueenState();
        assertEquals(0, eightQueenState.numberOfQueens());

        eightQueenState = new EightQueenState(0, 2, 4);
        assertEquals(3, eightQueenState.numberOfQueens());
    }
}
