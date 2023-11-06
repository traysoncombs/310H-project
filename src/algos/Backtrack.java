package algos;

import chess.Cell;
import chess.ChessBoard;
import chess.Knight;


public class Backtrack {
    public static ChessBoard backTrack(ChessBoard board, Cell cell, int idx) {
        if (idx == board.n * board.m - 1) return board;
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

    public static ChessBoard run(int n, int m) {
        ChessBoard board = new ChessBoard(n, m);
        Cell start = new Cell(1, 1, 1);
        board.setValue(start, 1);
        return backTrack(board, start, 2);
    }
}
