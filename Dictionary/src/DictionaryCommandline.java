import java.util.TreeMap;
import java.util.Set;

public class DictionaryCommandline extends DictionaryManagement{
    /**
     * Displays all words and meanings following the format.
     */
    public void showAllWords() {
        System.out.println("NO                    |English                                           |Vietnamese");
        String leftAlignFormat = "%-24d |%-50s |%s%n";

        TreeMap<String, String> mapWord = dictionary.getMapWord();
        Set<String> set = mapWord.keySet();

        int count = 0;

        for (String key : set) {
            System.out.printf(leftAlignFormat, ++count, key, mapWord.get(key));
        }
    }

    /**
     * The dictionaryBasic() function calls for insertFromCommandline() and showAllWords().
     */
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() {
        insertFromFile();
        showAllWords();
        dictionaryLookup();
    }
}