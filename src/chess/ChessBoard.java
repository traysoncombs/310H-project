package chess;

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
        return isValid(cell) && board[cell.x][cell.y] < 0;
    }

    public boolean isValid(Cell cell) {
        return (cell.x >= 0 && cell.y >= 0) && (cell.y < this.n && cell.x < this.m);
    }

    public int getValue(Cell cell) {
        return board[cell.x][cell.y];
    }

    public void setValue(Cell cell, int val) {
        board[cell.x][cell.y] = val;
    }
}