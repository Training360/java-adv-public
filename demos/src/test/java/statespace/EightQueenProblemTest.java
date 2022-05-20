package statespace;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EightQueenProblemTest {

    @Test
    void testStart() {
        assertEquals(0, new EightQueenProblem().start().numberOfQueens());
    }

    @Test
    void testTerminalState() {
        EightQueenState eightQueenState = new EightQueenState();
        assertFalse(new EightQueenProblem().isTerminal(eightQueenState));

        eightQueenState = new EightQueenState(0, 4, 7, 5, 2, 6, 1, 3);
        assertTrue(new EightQueenProblem().isTerminal(eightQueenState));
    }
}
