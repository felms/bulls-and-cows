import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        Random random = new Random();
//        int secretCode = random.nextInt(10000);
//        Scanner scanner = new Scanner(System.in);
//        int guess = scanner.nextInt();
//
//        int bullsCount = bulls(secretCode, guess);
//        int cowsCount = cows(secretCode, guess);
//        String grade = bullsCount == 0 && cowsCount == 0 ? "None"
//                : String.format("%s bull(s) and %s cow(s)", bullsCount, cowsCount);
//
//        System.out.println("Grade: " + grade +
//                ". The secret code is " + String.format("%04d", secretCode) + ".");
//
//        scanner.close();

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        if (input > 10) {
            System.out.println("Error: can't generate a secret number with a " +
                    "length of " + input + " because there aren't enough unique digits.");

        } else {
            long r = generateSecretCode(input);
            System.out.println("The random secret number is " + r + ".");
        }

        scanner.close();
    }

    // Gera o código secreto
    public static long generateSecretCode(int size) {

        long pseudoRandomNumber = System.nanoTime();
        int number = (int)Math.pow(10, size);
        pseudoRandomNumber = pseudoRandomNumber % number; // Ajusta o número para o tamanho do código.
        
        // Gera números até conseguir um que atenda os requisitos
        // de não possuir digitos repetidos e nem começar com o 
        // digito 0.
        while (!checkSecretCode(pseudoRandomNumber, size)) {
            pseudoRandomNumber = System.nanoTime();
            pseudoRandomNumber = pseudoRandomNumber % number;
        }
      
        return pseudoRandomNumber;
    }


    // Checa se o número gerado é um código válido
    public static boolean checkSecretCode(long code, int size) {

        //String formattingString = "%0" + size + "d";
        char[] codeString = String.valueOf(code).toCharArray();

        // O código não pode ser um número iniciado 
        // pelo digito 0.
        if (codeString.length != size) {
            return false;
        }

        
        // Checa por digitos repetidos.
        for (int i = 0; i < codeString.length; i++) {
            char c0 = codeString[i];
            for (int j = i + 1; j < codeString.length; j++) {
                char c1 = codeString[j];
                if (c0 == c1) {
                    return false;
                }
            }
        }        

        return true;
    }

    // Conta o número de 'bulls' (digitos corretos e na posição correta)
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

    // Conta o número de 'cows' (digitos corretos mas fora de posição)
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
