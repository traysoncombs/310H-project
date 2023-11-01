package algos;

import chess.Cell;
import chess.ChessBoard;
import chess.Knight;

public class Backtrack extends Algo {


    public void backTrack(ChessBoard board, Cell cell) {
        Cell[] moves = Knight.getOpenMovesFrom(board, cell);
        for (Cell move : moves) {

        }
    }

    @Override
    public void run(int n, int m) {

    }
}
