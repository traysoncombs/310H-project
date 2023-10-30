package algos;

import chess.Cell;
import chess.ChessBoard;
import chess.Knight;
import java.util.concurrent.ThreadLocalRandom;

public class Warns extends Algo {
    int n;

    int m;

    public Warns(int n, int m) {
        this.n = n;
        this.m = m;
    }

    /**
     * Finds the number of open cells that are reachable from `cell`
     */
    public int getDegree(ChessBoard board, Cell cell) {
        return Knight.getOpenMovesFrom(board, cell).length;
    }

    /**
     * Uses Warnsdorff's heuristic to find the best move.
     */
    public boolean nextMove(ChessBoard board, Cell currentPos) {
        int min_degree = 8;
        Cell min_cell = null;
        Cell[] moves = Knight.getOpenMovesFrom(board, currentPos);

        // Start from random move index, so we aren't trying to take the same path every time
        int random = ThreadLocalRandom.current().nextInt(100) % 8;

        for (int i = 0; i < 8; i++) {
            int mov_idx = (random + i) % 8;
            int degree = getDegree(board, moves[mov_idx]);
            if (min_degree > degree) {
                min_degree = degree;
                min_cell = moves[mov_idx];
            }
        }

        if (min_cell == null) return false;

        board.setValue(min_cell, currentPos.val + 1);

        return true;
    }

    public boolean findRoute() {
        return true;
    }

    @Override
    public void run() {

    }
}
