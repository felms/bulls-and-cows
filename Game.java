import java.util.Random;
public class Game {
    private int size;
    private int numberOfPossibleSymbols;
    private String secretCode;
    private Random random;
    private String possibleSymbols = "0123456789abcdefghijklmnopqrstuvwxyz";

    public Game(int difficultyLevel, int numberOfPossibleSymbols) {
        this.size = difficultyLevel;
        this.numberOfPossibleSymbols = numberOfPossibleSymbols;
        this.random = new Random();
        this.secretCode = generateSecretCode();
    }

    // Avalia o palpite
    public String gradeGuess(String guess) {
        int bullsCount = bulls(guess);
        int cowsCount = cows(guess);

        String grade = bullsCount == 0 && cowsCount == 0 ? "None"
                : cowsCount == 0 ? String.format("%s bulls", bullsCount)
                : String.format("%s bulls and %s cow", bullsCount, cowsCount);

        return "Grade: " + grade + ".";
    }

    // Testa se o palpite foi certeiro
    public boolean codeGuessed(String guess) {
        return this.secretCode.equals(guess);
    }
    
    // Gera o código secreto
    private String generateSecretCode() {

        StringBuilder code;

        // Gera números até conseguir um que atenda os requisitos
        // de não possuir digitos repetidos e nem começar com o
        // digito 0.
        do {
            code = new StringBuilder();
            while (code.length() < this.size) {
                int pos = random.nextInt(this.numberOfPossibleSymbols);
                code.append(possibleSymbols.charAt(pos)); 
            }
        } while (!checkSecretCode(code.toString()));

        String ast = "";
        for (int i = 0; i < this.size; i++) {
            ast += "*";
        }

        String ps = "";
        if (this.numberOfPossibleSymbols <= 10) {
            ps = "(0-" + possibleSymbols.charAt(this.numberOfPossibleSymbols - 1) + ")"; 
        } else if (this.numberOfPossibleSymbols == 11) {
            ps = "(0-9), a";
        } else if (this.numberOfPossibleSymbols > 11) {
            ps = "(0-9), (a-" + possibleSymbols.charAt(this.numberOfPossibleSymbols - 1) + ")"; 
        }
        System.out.printf("The secret is prepared: %s %s.\n", ast, ps);

        return code.toString();
    }

    // Checa se o número gerado é um código válido
    private boolean checkSecretCode(String code) {

        char[] codeArray = code.toCharArray();

        // Checa por digitos repetidos.
        for (int i = 0; i < codeArray.length; i++) {
            char c0 = codeArray[i];
            for (int j = i + 1; j < codeArray.length; j++) {
                char c1 = codeArray[j];
                if (c0 == c1) {
                    return false;
                }
            }
        }

        return true;
    }

    // Conta o número de 'bulls' (digitos corretos e na posição correta)
    private int bulls(String guess) {
        int count = 0;

        char[] sCode = this.secretCode.toCharArray();
        char[] guessString = guess.toCharArray();

        for (int i = 0; i < sCode.length; i++) {
            if (sCode[i] == guessString[i]) {
                count++;
            }
        }

        return count;
    }

    // Conta o número de 'cows' (digitos corretos mas fora de posição)
    public int cows(String guess) {
        int count = 0;

        char[] sCode = this.secretCode.toCharArray();
        char[] guessString = guess.toCharArray();

        for (int i = 0; i < sCode.length; i++) {
            for (int j = 0; j < guessString.length; j++) {
                if (sCode[i] == guessString[j] && i != j) {
                    count++;
                }
            }
        }

        return count;
    }
}
