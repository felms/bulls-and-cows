public class Game {
    private int size;
    private long secretCode;

    public Game(int difficultyLevel) {
        this.size = difficultyLevel;
        this.secretCode = generateSecretCode(this.size);
    }

    // Avalia o palpite
    public String gradeGuess(long guess) {
        int bullsCount = bulls(guess);
        int cowsCount = cows(guess);

        String grade = bullsCount == 0 && cowsCount == 0 ? "None"
                : cowsCount == 0 ? String.format("%s bulls", bullsCount)
                : String.format("%s bulls and %s cow", bullsCount, cowsCount);

        return "Grade: " + grade + ".";
    }

    // Testa se o palpite foi certeiro
    public boolean codeGuessed(long guess) {
        return this.secretCode == guess;
    }
    
    // Gera o código secreto
    private long generateSecretCode(int size) {

        long pseudoRandomNumber = System.nanoTime();
        int number = (int) Math.pow(10, size);
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
    private boolean checkSecretCode(long code, int size) {

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
    private int bulls(long guess) {
        int count = 0;

        char[] sCode = String.valueOf(this.secretCode).toCharArray();
        String formattingString = "%0" + this.size + "d";
        char[] guessString = String.format(formattingString, guess).toCharArray();

        for (int i = 0; i < sCode.length; i++) {
            if (sCode[i] == guessString[i]) {
                count++;
            }
        }

        return count;
    }

    // Conta o número de 'cows' (digitos corretos mas fora de posição)
    public int cows(long guess) {
        int count = 0;

        char[] sCode = String.valueOf(this.secretCode).toCharArray();
        String formattingString = "%0" + this.size + "d";
        char[] guessString = String.format(formattingString, guess).toCharArray();

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
