import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/** Частина 1: Drills 1-8 */
public class Drills {

    public static void main(String[] args) throws IOException {
        drill1();
        drill2();
        drill3();
        drill4();
        drill5();
        drill6();
        drill7();
        drill8();
    }

    // 1. BufferedWriter обгортає FileWriter
    static void drill1() throws IOException {
        System.out.println("=== Drill 1 ===");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("drill_data.txt"))) {
            bw.write("Рядок 1");
            bw.newLine();
            bw.write("Рядок 2");
            bw.newLine();
            bw.write("Рядок 3");
            bw.newLine();
            bw.write("Рядок 4");
            bw.newLine();
        }
        System.out.println("Файл drill_data.txt створено.");
        // Чому краще: FileWriter при кожному write() звертається до диска (системний виклик).
        // BufferedWriter накопичує дані у буфері в пам'яті (8192 символи) і записує великими
        // порціями -> значно менше повільних операцій з диском. Також додає newLine().
    }

    // 2. Читання рівно перших трьох рядків
    static void drill2() throws IOException {
        System.out.println("\n=== Drill 2 ===");
        try (BufferedReader br = new BufferedReader(new FileReader("drill_data.txt"))) {
            for (int i = 0; i < 3; i++) {
                String line = br.readLine();
                if (line == null) break; // у файлі менше 3 рядків
                System.out.println(line);
            }
        }
    }

    // 3. System.lineSeparator()
    static void drill3() throws IOException {
        System.out.println("\n=== Drill 3 ===");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("separator.txt"))) {
            bw.write("Перший рядок" + System.lineSeparator());
            bw.write("Другий рядок" + System.lineSeparator());
        }
        System.out.println("Файл separator.txt створено.");
        // Чому краще, ніж \n: lineSeparator() повертає роздільник поточної ОС
        // (Windows: \r\n, Linux/macOS: \n). Файл з жорстким \n у Windows-блокноті
        // може відобразитися як один довгий рядок -> lineSeparator() робить код переносимим.
    }

    // 4. Scanner з рядка
    static void drill4() {
        System.out.println("\n=== Drill 4 ===");
        Scanner sc = new Scanner("Apples 5.50 10").useLocale(Locale.US);
        String name = sc.next();
        double price = sc.nextDouble();
        int qty = sc.nextInt();
        System.out.println("Назва: " + name + ", ціна: " + price + ", кількість: " + qty);
        sc.close();
    }

    // 5. Власний роздільник
    static void drill5() {
        System.out.println("\n=== Drill 5 ===");
        Scanner sc = new Scanner("Java;Python;C++;Ruby").useDelimiter(";");
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }
        sc.close();
    }

    // 6. Locale.GERMANY і "5,33"
    static void drill6() {
        System.out.println("\n=== Drill 6 ===");
        Scanner de = new Scanner("5,33").useLocale(Locale.GERMANY);
        System.out.println("GERMANY: " + de.nextDouble()); // 5.33 - працює
        de.close();

        try {
            Scanner us = new Scanner("5,33").useLocale(Locale.US);
            us.nextDouble();
        } catch (java.util.InputMismatchException e) {
            System.out.println("US: InputMismatchException (кома не є десятковим роздільником)");
        }
        // Працює, бо в Німеччині десятковий роздільник - кома.
    }

    // 7. System.err
    static void drill7() {
        System.out.println("\n=== Drill 7 ===");
        try {
            throw new IllegalStateException("Навмисна помилка!");
        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }

    // 8. Міст System.in -> InputStreamReader -> BufferedReader
    static void drill8() throws IOException {
        System.out.println("\n=== Drill 8 ===");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введіть рядок: ");
        String line = br.readLine();
        System.out.println("Ви ввели: " + line);
    }
}
