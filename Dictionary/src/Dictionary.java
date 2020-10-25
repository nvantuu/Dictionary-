import java.util.ArrayList;
import java.util.TreeMap;

public class Dictionary {
    private static TreeMap<String, ArrayList<String>> wordList  = new TreeMap<>();

    /**
     * Set data dictionary.
     * @param wordList the parameter of data
     */
    public void setWordList(TreeMap<String, ArrayList<String>> wordList) {
        Dictionary.wordList = wordList;
    }

    /**
     * Get the map include word list.
     * @return Word list of dictionary
     */
    public TreeMap<String, ArrayList<String>> getWordList() {
        return wordList;
    }
}