import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Lê o tamanho do código e testa a validade
        // do input
        System.out.println("Input the length of the secret code:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        int size;
        try {
            size = Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            System.out.println("Error: \"" + s + "\" isn't a valid number.");
            scanner.close();
            return;
        }

        if (size > 36 || size < 1) {
            System.out.println("Error: can't generate a secret number with a " +
                    "length of " + size + " because there aren't enough unique digits.");
            scanner.close();
            return;

        }


        // Lê o número de símbolos e testa a validade
        // do input
        System.out.println("Input the number of possible symbols in the code:");
        s = scanner.nextLine();
        int possibleSymbols;
        try {
            possibleSymbols = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("Error: \"" + s + "\" isn't a valid number.");
            scanner.close();
            return;
        }

        if (possibleSymbols < size) {
            System.out.println("Error: it's not possible to generate a "
                    + "code with a length of " + size
                    + " with " + possibleSymbols + "unique symbols.");
            scanner.close();
            return;
        }

        if (possibleSymbols > 36 || possibleSymbols < 1) {
            System.out.println("Error: can't generate a secret number with "
                                + possibleSymbols + " digits.");
            scanner.close();
            return;

        }

        // Cria o codigo e executa o jogo
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
