import algos.Backtrack;
import algos.WarnBack;
import algos.Warns;
import chess.Cell;
import chess.ChessBoard;
import utils.Utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Cell start = new Cell(0, 0, 1);
        ChessBoard solution = WarnBack.run(300, 300, start);
        System.out.println(Utils.verify(solution, start));

    }

    public static String testTime(int n, int m, Cell start, int iters) {
        float warn_time = Main.getAverageRunningTimeWarn(n, m, start, iters);
        float warnback_time = Main.getAverageRunningTimeWarnback(n, m, start, iters);
        // CSV has the format n, m, start_x, start_y, time
        return String.format("%d, %d, %d, %d, %f, %f\n", n, m, start.x, start.y, warn_time / 1000000f, warnback_time / 1000000f);
    }

    public static float getAverageRunningTimeWarn(int n, int m, Cell start, int iterations) {
        long start_time, warn_time;
        float[] times = new float[iterations];
        for (int i = 0; i < iterations; i++) {
            start_time = System.nanoTime();
            warn_time = Warns.run(n, m, start) != null ? System.nanoTime() - start_time : -1;
            times[i] = warn_time;
        }

        float average = 0;
        for (float time : times) {
            if (time < 0) continue;
            average += time;
        }
        return average == 0 ? -1 : average/iterations;
    }

    public static float getAverageRunningTimeWarnback(int n, int m, Cell start, int iterations) {
        long start_time, warn_time;
        float[] times = new float[iterations];
        for (int i = 0; i < iterations; i++) {
            start_time = System.nanoTime();
            warn_time = WarnBack.run(n, m, start) != null ? System.nanoTime() - start_time : -1;
            times[i] = warn_time;
        }

        float average = 0;
        for (float time : times) {
            if (time < 0) continue;
            average += time;
        }
        return average == 0 ? -1 : average/iterations;
    }

    public static float getAverageRunningBacktrack(int n, int m, Cell start, int iterations) {
        long start_time, warn_time;
        float[] times = new float[iterations];
        for (int i = 0; i < iterations; i++) {
            start_time = System.nanoTime();
            warn_time = Backtrack.run(n, m, start) != null ? System.nanoTime() - start_time : -1;
            times[i] = warn_time;
        }

        float average = 0;
        for (float time : times) {
            if (time < 0) continue;
            average += time;
        }
        return average == 0 ? -1 : average/iterations;
    }
}
