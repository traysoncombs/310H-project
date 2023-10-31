package chess;

import java.util.ArrayList;

public class Knight {
    public static final int[] xOffsets =
            {1, 2, 2, 1, -1, -2, -2, -1};
    public static final int[] yOffsets =
            {2, 1, -1, -2, -2, -1, 1, 2};

    /**
     * Creates an array of VALID positions that can be reached from `cell`
     * based on the provided `board`
     */
    public static Cell[] getValidMovesFrom(ChessBoard board, Cell cell) {
        Cell[] moves = new Cell[8];

        for (int i = 0; i < 8; i++) {
            Cell newPos = new Cell(cell.x + xOffsets[i], cell.y + yOffsets[i]);
            if(board.isValid(newPos)) moves[i] = newPos;
        }

        return moves;
    }

    /**
     * Creates an array of OPEN positions that can be reached from `cell`
     * based on the provided `board`
     */
    public static Cell[] getOpenMovesFrom(ChessBoard board, Cell cell) {
        ArrayList<Cell> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Cell newPos = new Cell(cell.x + xOffsets[i], cell.y + yOffsets[i]);
            if(board.isOpen(newPos)) moves.add(newPos);
        }

        return moves.toArray(new Cell[0]);
    }
}
