public class Dictionary {
    protected Word[] arr;

    /**
     * Constructor without parameter.
     */
    Dictionary(){
        arr = null;
    }

    /**
     * Initialize a word array with size.
     * @param size is the length of the array.
     */
    Dictionary(int size) {
        arr = new Word[size];
    }

    /**
     * Get the array words in the dictionary.
     * @return array of words
     */
    public Word[] getArrWord(){
        return arr;
    }
}

