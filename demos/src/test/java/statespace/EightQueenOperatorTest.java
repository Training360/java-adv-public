package statespace;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EightQueenOperatorTest {

    @Test
    void testCreateOperators() {
        List<EightQueenOperator> operators = EightQueenOperator.createOperators();
        assertEquals(EightQueenState.NUMBER_OF_QUEENS, operators.size());
        assertEquals(3, operators.get(3).getPosition());
        assertEquals(7, operators.get(7).getPosition());
    }

    @Test
    void testIsExecutable() {
        assertTrue(new EightQueenOperator(0).isExecutable(new EightQueenState()));
        assertFalse(new EightQueenOperator(1).isExecutable(new EightQueenState(0)));
        assertTrue(new EightQueenOperator(2).isExecutable(new EightQueenState(0)));

    }

    @Test
    void execute() {
        EightQueenState result = new EightQueenOperator(0).execute(new EightQueenState());
        assertEquals(1, result.numberOfQueens());
        assertEquals(0, result.getQueenPositionByRows()[0]);

        result = new EightQueenOperator(4).execute(new EightQueenState(0));
        assertEquals(2, result.numberOfQueens());
        assertEquals(0, result.getQueenPositionByRows()[0]);
        assertEquals(4, result.getQueenPositionByRows()[1]);

    }
}
