import algos.Warns;

public class Main {
    public static void main(String[] args) {
        Warns warn = new Warns();
        long start = System.nanoTime();
        warn.run(400, 400);
        long end = System.nanoTime();
        System.out.println("Execution took: " + (end-start) / 1000000 + "ms");
    }
}
