package algos;

import chess.Cell;
import chess.ChessBoard;
import chess.Knight;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class WarnBack {

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
        Cell[] moves = Knight.getOpenMovesWarnBack(board, currentPos, currentPos.val);
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
        min_cell.val = currentPos.val + 1;
        board.setValue(min_cell);

        return min_cell;
    }

    public static boolean findRoute(ChessBoard board, Stack<Cell> moves) {
        board.setValue(moves.peek(), 1);
        int undo_amount = 1;
        for (int i = 1; i < board.n * board.m; i++) {
            if (moves.size() == 0)
                return false;
            Cell currMove = moves.peek();
            Cell nextMove = nextMove(board, currMove);

            if (nextMove == null) {
                undo_amount = Math.min(moves.size() - 2, undo_amount + Math.ceilDiv(moves.size(), 1000));
                board.setValue(currMove, 0);
                moves.pop();
                for (int j = 0; j < undo_amount; j++){
                    Cell prev_move = moves.pop();
                    board.setValue(prev_move, 0);
                    i -= 1;
                }
                i -= 2;
                continue;
            }
            moves.push(nextMove);
        }
        return true;
    }

    public static ChessBoard run(int n, int m, Cell start) {
        start.val = 1;
        ChessBoard board = new ChessBoard(n, m);
        board.setValue(start);
        Stack<Cell> moves = new Stack<>();
        moves.push(start);
        boolean found = findRoute(board, moves);
        if (found) {
            return board;
        }
        return null;
    }
}
