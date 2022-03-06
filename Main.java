import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Input the length of the secret code:");
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        while (size > 36) {
            System.out.println("Error: can't generate a secret number with a " +
                    "length of " + size + " because there aren't enough unique digits.");
            size = scanner.nextInt();

        }

        System.out.println("Input the number of possible symbols in the code:");
        int possibleSymbols = scanner.nextInt();
        while (possibleSymbols > 36) {
            System.out.println("Error: can't generate a secret number with "
                    + possibleSymbols + " possible symbols because there aren't enough unique digits.");
            possibleSymbols = scanner.nextInt();

        }
        Game game = new Game(size, possibleSymbols);
        System.out.println("Okay, let's start a game!");

        int turn = 1;
        String guess = "";
        do {
            System.out.println("Turn " + turn + ":");
            guess = scanner.next();
            String grade = game.gradeGuess(guess);
            System.out.println(grade);
            turn++;
        }while (!game.codeGuessed(guess));

        System.out.println("Congratulations! You guessed the secret code.");

        scanner.close();
    }
    
}
