import java.util.Scanner;

public class Hangman {
    private static int gameCounter = 0;
    private int gameId;
    private String word;
    private StringBuilder guessedWord;
    private StringBuilder lettersToTry;
    private int guessesLeft;
    private boolean completed;

    public Hangman() {
        gameId = ++gameCounter;
        lettersToTry = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        guessesLeft = 10;
        completed = false;
    }

    public void initializeGame_collectWord(Scanner keyIn) {
        System.out.println("Welcome to HANGMAN " + gameId);
        System.out.println("OK Guessing Player... turn around, while your friend enters the word to guess!");
        System.out.print("Other Player - Enter your word (up to 10 letters only, not case sensitive): ");
        word = keyIn.nextLine().toUpperCase();
        guessedWord = new StringBuilder(word.replaceAll(".", "*"));
    }

    public void playAGuess(Scanner keyIn) {
        if (completed) {
            return;
        }

        System.out.println("GameID " + gameId + ": Word to date: " + guessedWord + " (" + guessesLeft + " guess(es) left)");
        System.out.print("GameID " + gameId + ": Want to solve the puzzle? Enter \"y\" to solve the puzzle, or \"N\" to guess a character: ");
        String choice = keyIn.nextLine().toUpperCase();

        if (choice.equals("Y")) {
            System.out.print("GameID " + gameId + ": Enter the word: ");
            String fullGuess = keyIn.nextLine().toUpperCase();

            if (fullGuess.equals(word)) {
                System.out.println("GameID " + gameId + ": Congratulations!!!");
                System.out.println("You guessed the mystery word \"" + word + "\" in " + (10 - guessesLeft) + " guesses!");
            } else {
                System.out.println("GameID " + gameId + ": Sorry, wrong guess!");
                guessesLeft--;
            }
        } else {
            System.out.println("GameID " + gameId + ": Letters to try: " + lettersToTry);
            System.out.print("GameID " + gameId + ": Which letter should I check for? ");
            char letter = keyIn.nextLine().toUpperCase().charAt(0);

            int index = lettersToTry.indexOf(Character.toString(letter));
            if (index != -1) {
                lettersToTry.setCharAt(index, '*');
                boolean found = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        guessedWord.setCharAt(i, letter);
                        found = true;
                    }
                }

                if (found) {
                    System.out.println("GameID " + gameId + ": great guess!");
                } else {
                    System.out.println("GameID " + gameId + ": Sorry, wrong guess!");
                    guessesLeft--;
                }
            } else {
                System.out.println("GameID " + gameId + ": You've already tried that letter.");
            }
        }

        if (guessedWord.toString().equals(word)) {
            System.out.println("GameID " + gameId + ": Congratulations!!!");
            System.out.println("You guessed the mystery word \"" + word + "\" in " + (10 - guessesLeft) + " guesses!");
            completed = true;
        } else if (guessesLeft == 0) {
            System.out.println("GameID " + gameId + ": You have no more guesses left.");
            System.out.println("The mystery word was \"" + word + "\". Better luck next time!");
            completed = true;
        }
    }
        
    public void playGame(Scanner keyIn) {
        if (completed) {
            return;
        }

        while (!completed) {
            playAGuess(keyIn);
        }
    }
}

