import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Please, enter the secret code's length:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        while (size > 10) {
            System.out.println("Error: can't generate a secret number with a " +
                    "length of " + size + " because there aren't enough unique digits.");
            size = scanner.nextInt();

        }
        Game game = new Game(size);
        System.out.println("Okay, let's start a game!");

        int turn = 1;
        long guess = 0;
        while (!game.codeGuessed(guess)) {
            System.out.println("Turn " + turn + ":");
            guess = scanner.nextLong();
            String grade = game.gradeGuess(guess);
            System.out.println(grade);
        }

        System.out.println("Congratulations! You guessed the secret code.");

        scanner.close();
    }
    
}
