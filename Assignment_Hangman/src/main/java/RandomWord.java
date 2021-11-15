import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RandomWord {

    public static int getRandomNumber(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    /**
     * Read the file and store all words in list, and then use
     * Math class to randomly select value from list.
     */
    public String getRandomWord() throws IOException {
        File file = new File(
                "src/main/resources/words.txt");
        BufferedReader br
                = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.out.println("Error Reading the file");
        }
        List<String> words = new ArrayList<>();
        String word;
        while ((word = br.readLine()) != null)
            words.add(word);
        return words.get(getRandomNumber(0, words.size() - 1));
    }
}
