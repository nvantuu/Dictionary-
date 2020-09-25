public class Word {
    private final String word_target;
    private final String word_explain;
    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public String getWord_explain() {
        return word_explain;
    }
    // Constructors (overloading)
    /**
     * Constructor has no parameter.
     */
    Word() { // 1st (default) constructor
        this.word_target = null;
        this.word_explain = null;
    }

    /**
     * Constructor takes 2 parameters.
     * @param word_target is 1 word, followed by it's Vietnamese meaning
     * @param word_explain is Vietnamese meaning
     */
    Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }
}
