import java.util.Scanner;

public class Hangman {
    private static String wordToGuess = "JAVA";
    private static String hiddenWord;
    private static int attemptsLeft = 6;
    private static StringBuilder guessedLetters = new StringBuilder();
    private static boolean isGameOver = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeHiddenWord();

        System.out.println("Welcome to Hangman!");

        while (!isGameOver) {
            displayGameStatus();
            System.out.print("Enter a letter: ");
            String guess = scanner.nextLine().toUpperCase();

            if (guess.length() != 1) {
                System.out.println("Please enter only one letter.");
                continue;
            }

            if (guessedLetters.toString().contains(guess)) {
                System.out.println("You already guessed that letter.");
                continue;
            }

            guessedLetters.append(guess);
            
            if (wordToGuess.contains(guess)) {
                System.out.println("Good guess!");
                updateHiddenWord(guess);
            } else {
                System.out.println("Wrong guess!");
                attemptsLeft--;
            }

            checkGameOver();
        }

        if (attemptsLeft == 0) {
            System.out.println("Game Over! The word was: " + wordToGuess);
        }

        scanner.close();
    }

    private static void initializeHiddenWord() {
        hiddenWord = "_".repeat(wordToGuess.length());
    }

    private static void displayGameStatus() {
        System.out.println("\nWord to guess: " + hiddenWord);
        System.out.println("Guessed letters: " + guessedLetters);
        System.out.println("Attempts left: " + attemptsLeft);
    }

    private static void updateHiddenWord(String guess) {
        StringBuilder updatedHiddenWord = new StringBuilder(hiddenWord);
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == guess.charAt(0)) {
                updatedHiddenWord.setCharAt(i, guess.charAt(0));
            }
        }
        hiddenWord = updatedHiddenWord.toString();
    }

    private static void checkGameOver() {
        if (hiddenWord.equals(wordToGuess)) {
            isGameOver = true;
            System.out.println("Congratulations! You guessed the word: " + wordToGuess);
        } else if (attemptsLeft == 0) {
            isGameOver = true;
        }
    }
}
