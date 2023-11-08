package chess;

import java.util.Arrays;

public class ChessBoard {
    /**
     * Holds the n x m Chess board.
     */
    public int[][] board;

    public int n;

    public int m;

    public ChessBoard(int n, int m) {
        this.n = n;
        this.m = m;
        this.board = new int[n][m];
    }

    /**
     * Constructor to create a copy of a board
     */
    public ChessBoard(ChessBoard board) {
        // This just copies an array (found on SO)
        this.board = Arrays.stream(board.board)
                .map(cell ->  Arrays.copyOf(cell, cell.length))
                .toArray(int[][]::new);
        this.n = board.n;
        this.m = board.m;
    }

    /**
     * Returns true if (x, y) is a valid open cell.
     */
    public boolean isOpen(Cell cell) {
        return isValid(cell) && board[cell.x][cell.y] == 0;
    }

    /**
     * Determines whether a move is valid for the warnback algorithm.
     * Basically squares with a negative value are only open if the difference
     * in value is greater than one. This prevents the algorithm from backtracking
     * to a square it was just at.
     */
    public boolean isOpenWarnBack(Cell cell, int idx) {
        // Need to ensure it's a valid square before attempting to index the board.
        if (!isValid(cell)) return false;

        boolean valid_warnback_index = false;
        if (board[cell.x][cell.y] < 0) {
            valid_warnback_index = Math.abs(board[cell.x][cell.y] * -1 - idx) > 1;
        }
        return (board[cell.x][cell.y] == 0 || valid_warnback_index);
    }

    public boolean isValid(Cell cell) {
        return (cell.x >= 0 && cell.y >= 0) && (cell.x < this.n && cell.y < this.m);
    }

    public int getValue(Cell cell) {
        return board[cell.x][cell.y];
    }

    public void setValue(Cell cell) {
        board[cell.x][cell.y] = cell.val;
    }

    public void setValue(Cell cell, int val) {
        board[cell.x][cell.y] = val;
    }

    @Override
    public String toString() {
        int max_chars = Math.round((float) Math.log10(n * m)) + 1;
        StringBuilder ret = new StringBuilder();
        ret.append("-".repeat((m*(max_chars +2 ))+2)).append("\n");
        for (int[] row : this.board) {
            ret.append("|");
            for (int cell : row) {
                ret.append(String.format(" %"+max_chars+"s ", cell));
            }
            ret.append("|\n");
        }
        ret.append("-".repeat((m*(max_chars +2 ))+2)).append("\n");
        return ret.toString();
    }
}