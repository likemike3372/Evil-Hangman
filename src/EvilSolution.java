import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class EvilSolution {

    private String partialSolution;

     Set<String> possiblePatternWords;


/*    public EvilSolution(ArrayList<Character> partialSolution) {
        this.partialSolution = partialSolution;
    }*/

/*    public EvilSolution() {
        this.partialSolution = new ArrayList<>();
        this.possiblePatternWords = new HashMap<>();
    }*/

    public boolean isSolved() {
        if (partialSolution.contains("_"))
            return false;

        return true;
    }

    public String getPartialSolution() {
        return partialSolution;
    }

    public void setPartialSolution(String partialSolution) {
        this.partialSolution = partialSolution;
    }

    public Set<String> getPossiblePatternWords() {
        return possiblePatternWords;
    }

    public void setPossiblePatternWords(Set<String> possiblePatternWords) {
        this.possiblePatternWords = possiblePatternWords;
    }

    public void printProgress() {
        for (char c : partialSolution.toCharArray()) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}
