public class DictionaryCommandline extends DictionaryManagement {

    /**
     * Displays all words and meanings following the format.
     */
    public static void showAllWords() {
        System.out.println("NO                    |English                                           |Vietnamese");
        String leftAlignFormat = "%-24d |%-50s |%s%n";

        Word[] arrWord = dictionary.getArrWord();

        for (int i = 0; i < numberOfWords; i++) {
             System.out.printf(leftAlignFormat,(i + 1), arrWord[i].getWord_target(), arrWord[i].getWord_explain());
        }
    }

    /**
     * The dictionaryBasic () function calls for insertFromCommandline () and showAllWords ().
     */
    public static void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }
}
