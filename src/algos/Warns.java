package algos;

import chess.Cell;
import chess.ChessBoard;
import chess.Knight;

import java.util.concurrent.ThreadLocalRandom;

public class Warns extends Algo {

    /**
     * Finds the number of open cells that are reachable from `cell`
     */
    public int getDegree(ChessBoard board, Cell cell) {
        return Knight.getOpenMovesFrom(board, cell).length;
    }

    /**
     * Uses Warnsdorff's heuristic to find the best move.
     */
    public Cell nextMove(ChessBoard board, Cell currentPos) {
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

        board.setValue(min_cell, currentPos.val + 1);
        min_cell.val = currentPos.val + 1;
        return min_cell;
    }

    public boolean findRoute(ChessBoard board, Cell start) {
        board.setValue(start, 1);
        for (int i = 0; i < board.n*board.m-1; i++) {
            start = nextMove(board, start);
            if (start == null) return false;
        }
        return true;
    }

    @Override
    public void run(int n, int m) {
        Cell start = new Cell(1, 1, 1);
        while (true) {
            ChessBoard board = new ChessBoard(n, m);
            boolean found = findRoute(board, start);
            if (found) {
                System.out.println(board);
                break;
            }
        }
    }

    public static void main(String[] args) {
        Warns w = new Warns();
        w.run(10, 20);
    }
}
