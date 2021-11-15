import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {

    /**
     * Save the Word, DisplayWord and GuessLeft to file.
     */
    public static void saveToFile(String word, String displayWord, String guessLeft) throws IOException {
        FileWriter writer = new FileWriter("src/main/resources/save.txt");
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write(word);
        buffer.write(displayWord + "\n");
        buffer.write(guessLeft);
        buffer.close();
    }

    /**
     * Get the savedGame data from file and return a list containing those values.
     */
    public static List<String> getSavedGame() throws IOException {
        File file = new File(
                "src/main/resources/save.txt");
        BufferedReader br
                = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Error Reading the file");
        }
        List<String> game = new ArrayList<>();
        String word;
        while ((word = br.readLine()) != null)
            game.add(word);
        return game;
    }

    public static boolean isSavedGameAvailable() {
        File file = new File("src/main/resources/save.txt");
        return file.length() != 0;
    }

    public static void clearFile() {
        try {
            FileWriter fw = new FileWriter("src/main/resources/save.txt", false);
            fw.close();
        } catch (Exception exception) {
            System.out.println("Exception occurred");
        }
    }
}
