import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Hangman Game");
        System.out.println("Loading Saved Games");
        boolean savedGame = !FileOperations.isSavedGameAvailable();
        // check if savedGame is present or not.
        // if present then load from file otherwise start new game.
        if (savedGame) {
            System.out.println("No Saved Games");
            newGame(null, null, null);
        } else {
            List<String> game = FileOperations.getSavedGame();
            newGame(game.get(0), game.get(1), game.get(2));
        }
    }

    private static void newGame(String savedGameWord, String savedGameDisplayWord, String savedGuessLeft) throws IOException {
        String word = getWord(savedGameWord);
        Integer guessLeft = getGuessLeft(savedGuessLeft);
        char[] display = getDisplayWord(savedGameDisplayWord, word);
        System.out.println("Word to be guessed " + word);
        Scanner sc = new Scanner(System.in);
        while (guessLeft > 0) {
            System.out.println(new String(display));
            System.out.println("Guesses: " + guessLeft);
            System.out.println("Please make a guess");
            String guess = sc.nextLine();
            // If user enter save then save the game to file and exit.
            if (guess.equals("save")) {
                FileOperations.saveToFile(word + "\n", new String(display), guessLeft + "\n");
                break;
            }
            int index = word.indexOf(guess);
            if (index == -1) {
                guessLeft--;
            }
            while (index >= 0) {
                display[index] = guess.charAt(0);
                index = word.indexOf(guess, index + 1);
            }
            String s = new String(display);
            // If guessed word is equal to actual word, then exit the game and clear saved file.
            if (s.equals(word)) {
                System.out.println("Well Done");
                FileOperations.clearFile();
                break;
            }
        }
    }

    /**
     * Create "-" word from the actual word, or use the saved displayWord.
     */
    private static char[] getDisplayWord(String savedGameDisplayWord, String word) {
        char[] display;
        if (savedGameDisplayWord == null) {
            display = word.toCharArray();
            for (int i = 0; i < display.length; i++) {
                if (display[i] != ' ') {
                    display[i] = '-';
                }
            }
        } else {
            display = savedGameDisplayWord.toCharArray();
        }
        return display;
    }

    private static Integer getGuessLeft(String savedGuessLeft) {
        Integer guessLeft;
        if (savedGuessLeft == null) {
            guessLeft = 7;
        } else {
            guessLeft = Integer.valueOf(savedGuessLeft);
        }
        return guessLeft;
    }

    private static String getWord(String savedGameWord) throws IOException {
        String word;
        if (savedGameWord == null) {
            RandomWord randomWord = new RandomWord();
            word = randomWord.getRandomWord();
        } else {
            word = savedGameWord;
        }
        return word;
    }
}
