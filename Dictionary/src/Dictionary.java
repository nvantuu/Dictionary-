import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> arr;

    /**
     * Constructor without parameter.
     */
    public Dictionary() {
        arr = new ArrayList<Word>();
    }

    /**
     * Constructor initialize a word array with size.
     * @param size is the length of the array.
     */
    public Dictionary(int size) {
        arr = new ArrayList<Word>(size);
    }

    /**
     * Get the array words in the dictionary.
     * @return array of words
     */
    public ArrayList<Word> getArrayWord() {
        return arr;
    }
}