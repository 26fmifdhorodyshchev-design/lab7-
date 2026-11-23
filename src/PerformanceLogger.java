import java.io.*;

/** Task 3: Performance Logger */
public class PerformanceLogger {
    private static final int LINES = 10_000;

    public static void main(String[] args) throws IOException {
        // 1. Простий FileWriter
        long start = System.currentTimeMillis();
        try (FileWriter fw = new FileWriter("log.txt")) {
            for (int i = 1; i <= LINES; i++) {
                fw.write("Log entry #" + i + System.lineSeparator());
            }
        }
        long plain = System.currentTimeMillis() - start;

        // 2. BufferedWriter + FileWriter
        start = System.currentTimeMillis();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt"))) {
            for (int i = 1; i <= LINES; i++) {
                bw.write("Log entry #" + i);
                bw.newLine();
            }
        }
        long buffered = System.currentTimeMillis() - start;

        System.out.println("FileWriter:       " + plain + " ms");
        System.out.println("BufferedWriter:   " + buffered + " ms");
        System.out.println("Різниця:          " + (plain - buffered) + " ms");
        // Для 10 000 рядків різниця може бути малою (кеш ОС). Збільште LINES до 1_000_000,
        // щоб побачити виразний ефект буферизації.
    }
}
