import java.util.TreeMap;

public class Dictionary {
    private TreeMap<String, String> arr;

    /**
     * Constructor without parameter.
     */
    public Dictionary() {
        arr = new TreeMap<>();
    }

    /**
     * Get English words entered from keyboard.
     * @return map has all English words.
     */
    public TreeMap<String, String> getMapWord() {
        return arr;
    }
}