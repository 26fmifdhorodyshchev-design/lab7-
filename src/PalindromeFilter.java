import java.io.*;

/** Task 4: Palindrome Stream Filter */
public class PalindromeFilter {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вводьте рядки (QUIT - вихід):");

        String line;
        while ((line = br.readLine()) != null) {
            if (line.equals("QUIT")) {
                break;
            }
            String reversed = new StringBuilder(line).reverse().toString();
            if (line.equals(reversed)) {
                System.err.println(line);   // паліндром -> stderr
            } else {
                System.out.println(line);   // не паліндром -> stdout
            }
        }
    }
}
