/*import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WordleSolver {

	public static void main(String[] args) {
		//System.out.println(RandomFirstWord(LoadList("ValidWordleWords.txt")));
		System.out.println(Guessing(RandomFirstWord(LoadList("ValidWordleWords.txt")), GuessingWord(LoadList("ValidWordleWords.txt"))));
		//System.out.println(RandomFirstWord(LoadList("ValidWordleWords.txt")));
		//System.out.println(GuessingWord(LoadList("ValidWordleWords.txt")));
	}
	
	public static ArrayList<String> LoadList(String FileName){
		String word;
		ArrayList<String> WordList = new ArrayList<String>(); 
		try (BufferedReader br = new BufferedReader(new FileReader(FileName))) {
			while((word = br.readLine()) != null) {
				WordList.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return WordList;
	}
	
	public static String RandomFirstWord(ArrayList<String> List) {
		Random random = new Random();
		int RandomNumber = random.nextInt(List.size()+1);
		return List.get(RandomNumber);
	}
	
	public static String GuessingWord(ArrayList<String> List) {
		Random random = new Random();
		int RandomNumber = random.nextInt(List.size()+1);
		return List.get(RandomNumber);
	}
	
	
	public static void UpdatedWordList(String Word, String GW, ArrayList<Character> WList, ArrayList<Character> GWList, @SuppressWarnings("rawtypes") ArrayList<List> WordList){
		ArrayList<Character> WList1 = new ArrayList<>();
		 for (char c : Word.toCharArray()) {
	            WList1.add(c);
	        }
		ArrayList<Character> GWList1 = new ArrayList<>();
		 for (char c : GW.toCharArray()) {
	            GWList1.add(c);
	       }
		 
		for(int i=0; i<5; i++) {
			for(int j=0; j<WordList.size();j++) {
				for(int h=0; h<WordList.get(j).size(); h++){
					if(WordList.get(j).contains(WList1.get(i))) {
						WordList.remove(WordList.get(j));
					}
				}
			}
		}
		System.out.println(WordList);
		
	}
	
	public static ArrayList<Integer> Guessing(String Word, String GW){
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add(0);
		values.add(0);
		values.add(0);
		values.add(0);
		values.add(0);
		
		ArrayList<Character> WList = new ArrayList<>();
		 for (char c : Word.toCharArray()) {
	            WList.add(c);
	        }
		ArrayList<Character> GWList = new ArrayList<>();
		 for (char c : GW.toCharArray()) {
	            GWList.add(c);
	       }
		 System.out.println(WList);
		 System.out.println(GWList);
		 
		ArrayList<String> LoadedList = LoadList("ValidWordleWords.txt");
		@SuppressWarnings("rawtypes")
		ArrayList<List> WordList = new ArrayList<List>();
		 for(int i=0; i<LoadedList.size(); i++) {
			 String word = LoadedList.get(i);
			 ArrayList<Character> tempList = new ArrayList<>();
			 for (char c : word.toCharArray()) {
		            tempList.add(c);
		        }
			 WordList.add(tempList);
		 }

		 
		 for(int i=0; i<5; i++) {
			 if(GWList.contains(WList.get(i)) == false) {
				 values.set(i, 0);
				 UpdatedWordList(Word, GW, WList, GWList, WordList);
			 }
			 else if((GWList.contains(WList.get(i))) && (WList.get(i) == GWList.get(i))) {
				 values.set(i, 2);
				 
			 }
			 else if((GWList.contains(WList.get(i))) && (WList.get(i) != GWList.get(i))){
				 values.set(i, 1);
			 }

		 }
		return values;
	
	}
}*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordleSolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Wordle Solver!");

        // Specify the filename of your wordlist here.
        String wordlistFilename = "wordlist.txt";

        // Generate a random word from the wordlist for the player to guess.
        String targetWord = generateRandomWordFromFile(wordlistFilename);

        int maxAttempts = 6;
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.print("Enter your guess (5-letter word): ");
            String guess = scanner.nextLine().toLowerCase();

            if (guess.length() != 5) {
                System.out.println("Please enter a 5-letter word.");
                continue;
            }

            if (guess.equals(targetWord)) {
                System.out.println("Congratulations! You've guessed the word: " + targetWord);
                break;
            } else {
                attempts++;
                String hint = generateHint(targetWord, guess);
                System.out.println("Hint: " + hint);
                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }
        }

        if (attempts == maxAttempts) {
            System.out.println("Sorry, you've run out of attempts. The word was: " + targetWord);
        }

        scanner.close();
    }

    private static String generateRandomWordFromFile(String filename) {
        List<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading wordlist file: " + e.getMessage());
            System.exit(1);
        }

        Random random = new Random();
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    private static String generateHint(String target, String guess) {
        StringBuilder hint = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (target.charAt(i) == guess.charAt(i)) {
                hint.append(target.charAt(i));
            } else if (target.contains(String.valueOf(guess.charAt(i)))) {
                hint.append("+");
            } else {
                hint.append("-");
            }
        }
        return hint.toString();
    }
}
