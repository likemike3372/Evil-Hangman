import java.io.IOException;
import java.util.*;

public class EvilHangman {

    private WordProperties wordProperties;

    private HashSet<Character> previousGuesses;

    private TreeSet<Character> incorrectGuesses;

    private EvilSolution evilSolution;

    private Scanner inputScanner;

    public EvilHangman() {
        this("engDictionary.txt");
    }

    private EvilHangman(String filename) {
        try {
            wordProperties = Util.dictionaryToList(filename);
        } catch (IOException e) {
            System.out.printf(
                    "Couldn't read from the file %s. Verify that you have it in the right place and try running again.",
                    filename);
            e.printStackTrace();
            System.exit(0); // stop the program--no point in trying if you don't have a dictionary
        }
        wordProperties.setWordLength(Util.generateRandomWordLength(wordProperties));
        Util.filterWords(wordProperties);
        previousGuesses = new HashSet<>();
        incorrectGuesses = new TreeSet<>();
        inputScanner = new Scanner(System.in);
        evilSolution = new EvilSolution();
        evilSolution.setPartialSolution("_".repeat(wordProperties.getWordLength()));
    }


    public void start() {
        while (!evilSolution.isSolved()) {
            char guess = promptForGuess();
            recordGuess(guess);
        }
        printVictory();
    }

    private void printVictory() {
        System.out.printf("Congrats! The word was %s%n", evilSolution.getPartialSolution());
    }


    private void recordGuess(char guess) {

        previousGuesses.add(guess);

        Map<String, HashSet<String>> map = new HashMap<>();
        String partialSolution = evilSolution.getPartialSolution();
        for (String word : wordProperties.getWordSet()) {
            char[] wordPattern = partialSolution.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                if (guess == word.charAt(i) && wordPattern[i] == '_') {
                    wordPattern[i] = guess;
                }
            }
            HashSet<String> wordSet;
            String wordP = new String(wordPattern);
/*
            System.out.print("Current pattern " + wordP);
*/
            if (map.containsKey(wordP)) {
                wordSet = map.get(wordP);

            } else {
                wordSet = new HashSet<>();

            }
            wordSet.add(word);
            map.put(wordP, wordSet);

        }


        int max = -1;
        HashSet<String> updatedSet = new HashSet<>();
        String updatedSolution = "";
        for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
/*
            System.out.print("Current pattern " + entry.getKey());
*/
            if (entry.getValue().size() > max) {
                max = entry.getValue().size();
                updatedSet = entry.getValue();
                updatedSolution = entry.getKey();
            }

        }
        evilSolution.setPartialSolution(updatedSolution);
        evilSolution.setPossiblePatternWords(updatedSet);
        wordProperties.setWordSet(updatedSet);
        System.out.println(updatedSolution + " " + updatedSet);

        if (!updatedSolution.contains(String.valueOf(guess)))
            incorrectGuesses.add(guess);

    }

    private char promptForGuess() {
        while (true) {
            System.out.println("Guess a letter.\n");
            evilSolution.printProgress();
            System.out.println("Incorrect guesses:\n" + incorrectGuesses.toString());
            String input = inputScanner.next();
            if (input.length() != 1) {
                System.out.println("Please enter a single character.");
            } else if (previousGuesses.contains(input.charAt(0))) {
                System.out.println("You've already guessed that.");
            } else {
                return input.charAt(0);
            }
        }

    }


}
