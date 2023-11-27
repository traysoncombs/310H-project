package utils;

import chess.Cell;
import chess.ChessBoard;
import chess.Knight;


public class Utils {
    /**
     * Verifies that a board contains a valid tour.
     */
    public static boolean verify(ChessBoard board, Cell start) {
        boolean hasValidIndex = false;
        for (int i = 2; i <= board.n*board.m; i++) {
            for (Cell move : Knight.getValidMovesFrom(board, start)) {
                if (move.val == i) {
                    hasValidIndex = true;
                    start = move;
                    break;
                }
            }
            if (!hasValidIndex) {
                return false;
            } else {
                hasValidIndex = false;
            }
        }
        return true;
    }
}
