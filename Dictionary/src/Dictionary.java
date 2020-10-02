import java.util.TreeMap;

public class Dictionary {
    private TreeMap<String, String> arr;

    /**
     * Constructor without parameter.
     */
    public Dictionary() {
        arr = new TreeMap<>();
    }

    public TreeMap<String, String> getMapWord() {
        return arr;
    }
}