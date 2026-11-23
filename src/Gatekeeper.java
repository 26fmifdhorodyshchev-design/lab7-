import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

/** Task 1: Secure Login System */
public class Gatekeeper {
    public static void main(String[] args) {
        String user;
        char[] pass;

        Console console = System.console();
        if (console != null) {
            user = console.readLine("Username: ");
            pass = console.readPassword("Password: ");
        } else {
            // IntelliJ / Eclipse: консолі немає -> запасний варіант через Scanner
            System.out.println("(Console недоступна, використовується Scanner)");
            Scanner sc = new Scanner(System.in);
            System.out.print("Username: ");
            user = sc.nextLine();
            System.out.print("Password: ");
            pass = sc.nextLine().toCharArray();
        }

        if ("admin".equals(user) && Arrays.equals(pass, "java123".toCharArray())) {
            System.out.println("Access Granted");
        } else {
            System.err.println("Access Denied");
        }
        Arrays.fill(pass, ' '); // очищення пароля з пам'яті
    }
}
