package algos;

import chess.Cell;
import chess.ChessBoard;
import chess.Knight;
import utils.Utils;


public class Backtrack {

    /**
     * Implementation of a backtracking solution to the knights tour.
     */
    public static ChessBoard backTrack(ChessBoard board, Cell cell, int idx) {
        if (idx == board.n * board.m + 1) return board;
        Cell[] moves = Knight.getOpenMovesFrom(board, cell);

        for (Cell move : moves) {
            ChessBoard boardCopy = new ChessBoard(board);

            boardCopy.setValue(move, idx);
            move.val = idx;

            ChessBoard sol = backTrack(boardCopy, move, idx + 1);
            if (sol == null) continue;
            return sol;
        }
        return null;
    }

    public static ChessBoard run(int n, int m, Cell start) {
        start.val = 1;
        ChessBoard board = new ChessBoard(n, m);
        board.setValue(start);
        return backTrack(board, start, 2);
    }
}