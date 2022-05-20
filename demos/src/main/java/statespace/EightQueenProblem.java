package statespace;

import static statespace.EightQueenState.NUMBER_OF_QUEENS;

public class EightQueenProblem {

    public EightQueenState start() {
        return new EightQueenState();
    }

    public boolean isTerminal(EightQueenState eightQueenState) {
        return eightQueenState.numberOfQueens() == NUMBER_OF_QUEENS;
    }


}
