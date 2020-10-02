import java.util.TreeMap;

public class Dictionary {
    private final TreeMap<String, String> wordListOfDict = new TreeMap<>();

    /**
     * Get the map include word list.
     * @return Word list of dictionary
     */
    public TreeMap<String, String> getWordListOfDict() {
        return wordListOfDict;
    }
}