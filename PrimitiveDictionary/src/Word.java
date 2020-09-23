public class Word {
    private final String word_target;
    private final String word_explain;
    Word(String target, String explain) {
        this.word_target = target;
        this.word_explain = explain;
    }
    public String getWord_target() {
        return word_target;
    }
    public String getWord_explain() {
        return word_explain;
    }
}
