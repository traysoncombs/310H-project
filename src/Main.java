import algos.Backtrack;
import algos.WarnBack;
import algos.Warns;
import chess.Cell;
import chess.ChessBoard;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] ns = IntStream.rangeClosed(8, 100).toArray();
        int[] ms = IntStream.rangeClosed(8, 100).toArray();

        /**for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cell start = new Cell(i, j, 1);
                System.out.println(testTime(10, 10, start));
            }
        }**/
        BufferedWriter writer = new BufferedWriter(new FileWriter("data.csv", true));

        Cell start = new Cell(0, 0, 1);

        for (int i = 8; i < 100; i++) {
            for (int j = i; j < 100; j++) {
                // Iterate over possible starting positions
                String time = testTime(i, j, start);
                writer.write(time);
                System.out.println(time);

            }
        }
    }

    public static String testTime(int n, int m, Cell start) {
        float warn_time = Main.getAverageRunningTimeWarn(n, m, start, 100);
        float warnback_time = Main.getAverageRunningTimeWarnback(n, m, start, 100);
        // CSV has the format n, m, start_x, start_y, time
        return String.format("%d, %d, %d, %d, %f, %f", n, m, start.x, start.y, warn_time / 1000000f, warnback_time / 1000000f);
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
}
