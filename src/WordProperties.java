import java.util.HashSet;

public class WordProperties {

    private HashSet<String> wordSet;

    private int wordMax;

    private int wordMin;

    private int wordLength;


    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public HashSet<String> getWordSet() {
        return wordSet;
    }

    public void setWordSet(HashSet<String> wordSet) {
        this.wordSet = wordSet;
    }

    public int getWordMax() {
        return wordMax;
    }

    public void setWordMax(int wordMax) {
        this.wordMax = wordMax;
    }

    public int getWordMin() {
        return wordMin;
    }

    public void setWordMin(int wordMin) {
        this.wordMin = wordMin;
    }
}
