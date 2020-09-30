public class Word {
    private String word_target;
    private String word_explain;

    /**
     * Constructor without parameter.
     */
    Word() {
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

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    /**
     * Get the English word.
     * @return the English word.
     */
    public String getWord_target() {
        return word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    /**
     * Get the mean of word.
     * @return the mean of word.
     */
    public String getWord_explain() {
        return word_explain;
    }

}