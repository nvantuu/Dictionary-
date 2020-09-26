public class DictionaryCommandline extends DictionaryManagement {

    /**
     * Displays all words and meanings following the format.
     */
    public void showAllWords() {
        System.out.println("NO      |English               |Vietnamese");
        String leftAlignFormat = "%-7d |%-25s |%s%n";
        for (int i = 0; i < dictionary.arr.length; i++) {
            System.out.printf(leftAlignFormat,(i+1), dictionary.arr[i].getWord_target(), dictionary.arr[i].getWord_explain());
        }
    }

    /**
     * The dictionaryBasic () function calls for insertFromCommandline () and showAllWords ().
     */
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }
}