package statespace;

import java.util.Arrays;

public class EightQueenState {

    public static final int NUMBER_OF_QUEENS = 8;

    private int[] queenPositionByRows = null;

    public EightQueenState() {
        queenPositionByRows = new int[0];
    }

    public EightQueenState(int... queenPositionByRows) {
        if (queenPositionByRows.length > NUMBER_OF_QUEENS) {
            throw new IllegalArgumentException("More queen than " + NUMBER_OF_QUEENS);
        }
        this.queenPositionByRows = Arrays.copyOf(queenPositionByRows, queenPositionByRows.length);
    }

    public int numberOfQueens() {
        return queenPositionByRows.length;
    }

    public int[] getQueenPositionByRows() {
        return queenPositionByRows;
    }
}
