import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Random random = new Random();
        int secretCode = random.nextInt(10000);
        Scanner scanner = new Scanner(System.in);
        int guess = scanner.nextInt();

        int bullsCount = bulls(secretCode, guess);
        int cowsCount = cows(secretCode, guess);
        String grade = bullsCount == 0 && cowsCount == 0 ? "None"
                : String.format("%s bull(s) and %s cow(s)", bullsCount, cowsCount);

        System.out.println("Grade: " + grade + 
                ". The secret code is " + String.format("%04d", secretCode) + ".");

        scanner.close();

    }

    public static int bulls(int secretCode, int guess) {
        int count = 0;

        char[] sCode = String.format("%04d", secretCode).toCharArray();
        char[] guessString = String.format("%04d", guess).toCharArray();

        for (int i = 0; i < sCode.length; i++) {
            if (sCode[i] == guessString[i]) {
                count++;
            }
        }

        return count;
    }

    public static int cows(int secretCode, int guess) {
        int count = 0;

        char[] sCode = String.format("%04d", secretCode).toCharArray();
        char[] guessString = String.format("%04d", guess).toCharArray();

        for (int i = 0; i < sCode.length; i++) {
            for(int j = 0; j < guessString.length; j++) {
                if (sCode[i] == guessString[j] && i != j) {
                    count++;
                }
            }
        }

        return count;
    }
}
