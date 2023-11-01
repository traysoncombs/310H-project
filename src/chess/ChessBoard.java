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
     * Returns true if (x, y) is a valid open cell.
     */
    public boolean isOpen(Cell cell) {
        return isValid(cell) && board[cell.x][cell.y] == 0;
    }

    public boolean isValid(Cell cell) {
        return (cell.x >= 0 && cell.y >= 0) && (cell.x < this.n && cell.y < this.m);
    }

    public int getValue(Cell cell) {
        return board[cell.x][cell.y];
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