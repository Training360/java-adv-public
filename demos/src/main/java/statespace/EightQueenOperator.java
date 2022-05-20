package statespace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EightQueenOperator {

    private int position;

    public EightQueenOperator(int position) {
        this.position = position;
    }

    public boolean isExecutable(EightQueenState eightQueenState) {
        int numberOfQueens = eightQueenState.numberOfQueens();
        for (int i = 0; i < numberOfQueens; i++) {
            if (underAttack(i, eightQueenState.getQueenPositionByRows()[i], numberOfQueens, position)) {
                return false;
            }
        }
        return true;
    }

    private boolean underAttack(int x1, int y1, int x2, int y2) {
        return x1 == x2 || y1 == y2 ||
                x1 + y2 == x2 + y1 ||
                x1 + y1 == x2 + y2;
    }

    public EightQueenState execute(EightQueenState input) {
        int[] positions = Arrays.copyOf(input.getQueenPositionByRows(), input.numberOfQueens() + 1);
        positions[input.numberOfQueens()] = position;
        return new EightQueenState(positions);
    }

    public static List<EightQueenOperator> createOperators() {
        List<EightQueenOperator> operators = new ArrayList<>();
        for (int i = 0; i < EightQueenState.NUMBER_OF_QUEENS; i++) {
            operators.add(new EightQueenOperator(i));
        }
        return operators;
    }

    public int getPosition() {
        return position;
    }
}
