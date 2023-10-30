package chess;

public class Cell {
    public int x;
    public int y;
    public int val = 0;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }


}
