import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerWordleSolver {

    public static void main(String[] args) {
        try {
            // Load the wordlist from a file.
            List<String> wordlist = loadWordlist("wordlist.txt");
            if (wordlist.isEmpty()) {
                System.out.println("Wordlist is empty or not found.");
                return;
            }

            // Select a random target word for the computer to guess.
            String targetWord = getRandomWord(wordlist);

            System.out.println("Welcome to Unlimited Wordle Solver!");
            System.out.println("Target Word: " + targetWord);

            // Start guessing.
            int attempts = 0;
            while (true) {
                String guess = getRandomWord(wordlist);
                System.out.println("Attempt " + (attempts + 1) + ": " + guess);

                // Check if the guess is correct.
                if (guess.equals(targetWord)) {
                    System.out.println("Computer has guessed the word: " + guess);
                    break;
                }

                attempts++;
            }
        } catch (IOException e) {
            System.err.println("Error reading wordlist file: " + e.getMessage());
        }
    }

    private static List<String> loadWordlist(String filename) throws IOException {
        List<String> wordlist = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordlist.add(line.trim().toLowerCase());
            }
        }
        return wordlist;
    }

    private static String getRandomWord(List<String> wordlist) {
        Random random = new Random();
        int index = random.nextInt(wordlist.size());
        return wordlist.get(index);
    }
}
