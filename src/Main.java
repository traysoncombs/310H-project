import algos.Backtrack;
import algos.Warns;
import chess.ChessBoard;

public class Main {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ChessBoard sol = Backtrack.run(11, 11);
        long end = System.nanoTime();
        System.out.println(sol);
        System.out.println("Execution took: " + (end-start) / 1000000 + "ms");
    }
}
