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
        ArrayList<Cell> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Cell newPos = new Cell(cell.x + xOffsets[i], cell.y + yOffsets[i]);
            if(board.isValid(newPos)) {
                newPos.val = board.getValue(newPos);
                moves.add(newPos);
            }
        }

        return moves.toArray(new Cell[0]);
    }

    /**
     * Creates an array of OPEN positions that can be reached from `cell`
     * based on the provided `board`
     */
    public static Cell[] getOpenMovesFrom(ChessBoard board, Cell cell) {
        ArrayList<Cell> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Cell newPos = new Cell(cell.x + xOffsets[i], cell.y + yOffsets[i]);
            if(board.isOpen(newPos)) {
                newPos.val = board.getValue(newPos);
                moves.add(newPos);
            }
        }

        return moves.toArray(new Cell[0]);
    }

    public static Cell[] getOpenMovesWarnBack(ChessBoard board, Cell cell, int idx) {
        ArrayList<Cell> moves = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Cell newPos = new Cell(cell.x + xOffsets[i], cell.y + yOffsets[i]);
            if(board.isOpenWarnBack(newPos, idx)) {
                newPos.val = board.getValue(newPos);
                moves.add(newPos);
            }
        }

        return moves.toArray(new Cell[0]);
    }

    public static boolean isValidKnightMove(Cell from, Cell to) {
        int x_off = to.x - from.x;
        int y_off = to.y - from.y;
        for (int i = 0; i < 8; i++)  {
            if (x_off == xOffsets[i] && y_off == yOffsets[i]) return true;
        }
        return false;
    }
}
