package algos;

import chess.Cell;
import chess.ChessBoard;
import chess.Knight;

import java.util.concurrent.ThreadLocalRandom;

public class Warns  {

    /**
     * Finds the number of open cells that are reachable from `cell`
     */
    public static int getDegree(ChessBoard board, Cell cell) {
        return Knight.getOpenMovesFrom(board, cell).length;
    }

    /**
     * Uses Warnsdorff's heuristic to find the best move.
     */
    public static Cell nextMove(ChessBoard board, Cell currentPos) {
        int min_degree = 8;
        Cell min_cell = null;
        Cell[] moves = Knight.getOpenMovesFrom(board, currentPos);
        if (moves.length == 0) return null;

        // Start from random move index, so we aren't trying to take the same path every time
        int random = ThreadLocalRandom.current().nextInt(100) % moves.length;

        for (int i = 0; i < moves.length; i++) {
            int mov_idx = (random + i) % moves.length;
            int degree = getDegree(board, moves[mov_idx]);
            if (min_degree > degree) {
                min_degree = degree;
                min_cell = moves[mov_idx];
            }
        }

        if (min_cell == null) return null;
        // need to set value on the board, and the value of the cell to be used by the next function call
        // kind of a confusing way of doing this but it works.
        board.setValue(min_cell, currentPos.val + 1);
        min_cell.val = currentPos.val + 1;

        return min_cell;
    }

    public static boolean findRoute(ChessBoard board, Cell start) {
        board.setValue(start, 1);
        for (int i = 0; i < board.n * board.m - 1; i++) {
            start = nextMove(board, start);
            if (start == null) return false;
        }
        return true;
    }

    public static ChessBoard run(int n, int m) {
        Cell start = new Cell(1, 1, 1);
        while (true) {
            ChessBoard board = new ChessBoard(n, m);
            boolean found = findRoute(board, start);
            if (found) {
                return board;
            }
        }
    }
}
