import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class DictionaryManagement {
    protected Dictionary dictionary;

    /**
     * Function get data from database (txt file).
     */
    public void insertFromFile() throws IOException {
        File dataFileIsFormatted = new File(Main.DATA_FILE_PATH);
        Scanner scanner = new Scanner(dataFileIsFormatted);

        dictionary = new Dictionary();
        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            String target = str.substring(0, str.indexOf("\t"));
            String explain = str.substring(str.indexOf("\t") + 1);
            wordListOfDict.put(target, explain);
        }
        scanner.close();
    }

    /**
     * Lookup the complete word in dictionary.
     */
    public String dictionarySearch(String engWord) {
        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();

        if (wordListOfDict.get(engWord) == null) {
            return "Not found!";
        }

        return wordListOfDict.get(engWord);
    }

    /**
     * Add the new word into dictionary.
     */
    public void dictionaryAdd(String engWord, String vietWord) {
        dictionary.getWordListOfDict().put(engWord, vietWord);
    }

    /**
     * Remove the word you want.
     */
    public boolean dictionaryDelete(String engWord) {
        if (dictionary.getWordListOfDict().get(engWord) != null) {
            dictionary.getWordListOfDict().remove(engWord);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Fix meaning of the word you want fix.
     */
    public boolean dictionaryFix(String engWord, String vietWord) {
        if (dictionary.getWordListOfDict().containsKey(engWord)) {
            dictionary.getWordListOfDict().put(engWord, vietWord);
        } else {
            return false;
        }

        return true;
    }

    /**
     * Export current data of dictionary to files.
     */
    public void dictionaryExportToFile(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();

        for (Map.Entry<String, String> entry : wordListOfDict.entrySet()) {
            fw.write(entry.getKey() + "    " + entry.getValue() + "\n");
        }

        fw.close();
    }

    public void showAllWords() {
        System.out.println("NO        |English                       |Vietnamese");

        String leftAlignFormat = "%-9d |%-32s |%s %n";

        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();

        int count = 0;

        for (Map.Entry<String, String> entry : wordListOfDict.entrySet()) {
            System.out.printf(leftAlignFormat, ++count, entry.getKey(), entry.getValue());
        }
        System.out.println();
    }

    public ArrayList<String> dictionaryLookup(String engWord) {
        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();
        ArrayList<String> listWord = new ArrayList<>();

        for (Map.Entry<String, String> entry : wordListOfDict.entrySet()) {
            // Use indexOf() but not contains()
            // to make sure that "engWord" is the first part of the complete word.
            if (entry.getKey().indexOf(engWord) == 0) {
                listWord.add(entry.getKey());
            }
        }

        return listWord;
    }
}