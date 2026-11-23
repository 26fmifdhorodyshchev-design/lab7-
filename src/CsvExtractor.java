import java.io.*;
import java.util.Locale;
import java.util.Scanner;

/** Task 2: CSV Data Extractor */
public class CsvExtractor {
    public static void main(String[] args) throws IOException {
        // Створюємо students.csv у корені проєкту
        try (FileWriter fw = new FileWriter("students.csv")) {
            fw.write("Alice, 88.5, 92; Bob, 70.0, 65; Charlie, 95.0, 98");
        }

        try (Scanner sc = new Scanner(new File("students.csv"))) {
            // Шаблон з завдання + \R, щоб кінцевий перенос рядка не ламав останнє число
            sc.useDelimiter("(, )|(; )|\\R").useLocale(Locale.US);
            while (sc.hasNext()) {
                String name = sc.next();
                double midterm = sc.nextDouble();
                double fin = sc.nextDouble();
                double avg = (midterm + fin) / 2;
                System.out.format(Locale.US, "Student: %s | Average: %.2f%n", name, avg);
            }
        }
    }
}
