public class Dictionary {
    private Word[] arr;

    /**
     * Constructor without parameter.
     */
    Dictionary() {
        arr = null;
    }

    /**
     * Constructor initialize a word array with size.
     * @param size is the length of the array.
     */
    Dictionary(int size) {
        arr = new Word[size];
    }

    /**
     * Get the array words in the dictionary.
     * @return array of words
     */
    public Word[] getArrayWord() {
        return arr;
    }
}