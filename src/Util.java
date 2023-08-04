import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class Util {


     static void filterWords(WordProperties wordProperties) {
         HashSet<String> wordSet = wordProperties.getWordSet();
         wordSet.removeIf(word -> word.length() != wordProperties.getWordLength());
        wordProperties.setWordSet(wordSet);
    }


    static WordProperties dictionaryToList(String filename) throws IOException {
        FileInputStream fs = new FileInputStream(filename);
        Scanner scnr = new Scanner(fs);
        WordProperties wordProperties = new WordProperties();
        HashSet<String> wordSet = new HashSet<>();
        int min = 1000;
        int max = 0;

        while (scnr.hasNext()) {
            String word = scnr.next();
            if (word.length() > max)
                max = word.length();

            if (word.length() < min)
                min = word.length();

            wordSet.add(word);
        }

        wordProperties.setWordSet(wordSet);
        wordProperties.setWordMin(min);
        wordProperties.setWordMax(max);

        return wordProperties;
    }

    static int generateRandomWordLength(WordProperties wordProperties) {
        return (int) (Math.random() * (wordProperties.getWordMax() - wordProperties.getWordMin()) + wordProperties.getWordMin());
    }
}
