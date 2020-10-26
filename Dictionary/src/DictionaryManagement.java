import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class DictionaryManagement {
    private Dictionary dictionary;

    /**
     * Function get data from database (txt file).
     */
    public void insertFromFile() throws IOException {
        File file = new File(Main.DATA_FILE_PATH);
        Scanner sc = new Scanner(file);

        dictionary = new Dictionary();
        TreeMap<String, ArrayList<String>> wordList = new TreeMap<>();
        String target = sc.nextLine().substring(1).trim();
        ArrayList<String> explain = new ArrayList<>();
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            if (str.indexOf("@") == 0) {
                wordList.put(target, explain);
                target = str.substring(1).trim();
                explain = new ArrayList<>();
            } else if (str.indexOf("=") == 0) {
                str = str.replace("+", " : ");
                String word = "  =  " + str.substring(1);
                explain.add(word);
            } else if (str.indexOf("#") == 0 || str.indexOf("*") == 0) {
                explain.add(str);
            } else {
                explain.add("     " + str);
            }
        }
        dictionary.setWordList(wordList);
        sc.close();
    }

    /**
     * Lookup the complete word in dictionary.
     */
    public ArrayList<String> dictionarySearch(String engWord) {
        TreeMap<String, ArrayList<String>> wordList = dictionary.getWordList();
        if (engWord != null) {
            if (wordList.get(engWord) != null) {
                return wordList.get(engWord);
            }
        }
        return null;
    }

    /**
     * Add the new word into dictionary.
     */
    public void dictionaryAdd(String engWord, String vietWord) {
        String[] explain = vietWord.split("\n");

        if (dictionary.getWordList().get(engWord) != null) {
            dictionary.getWordList().get(engWord).addAll(Arrays.asList(explain));
        } else {
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(explain));
            dictionary.getWordList().put(engWord, arrayList);
        }
    }

    /**
     * Remove the word you want.
     */
    public boolean dictionaryDelete(String engWord) {
        if (dictionary.getWordList().get(engWord) != null) {
            dictionary.getWordList().remove(engWord);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Edit meaning of the word you want edit.
     */
    public boolean dictionaryEdit(String engWord, String vietWord) {
        if (dictionary.getWordList().containsKey(engWord)) {
            String[] explain = vietWord.split("\n");
            ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(explain));
            dictionary.getWordList().put(engWord, arrayList);
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
        TreeMap<String, ArrayList<String>> wordList = dictionary.getWordList();

        int count = 0;

        for (Map.Entry<String, ArrayList<String>> entry : wordList.entrySet()) {
            fw.write(++count + ")    " + entry.getKey() + "\n");
            for (String str : entry.getValue()) {
                fw.write("       " + str + "\n");
            }
        }

        fw.close();
    }

    public ArrayList<String> dictionaryLookup(String engWord) {
        TreeMap<String, ArrayList<String>> wordList = dictionary.getWordList();
        ArrayList<String> listWord = new ArrayList<>();

        for (Map.Entry<String, ArrayList<String>> entry : wordList.entrySet()) {
            // Use indexOf() but not contains()
            // to make sure that "engWord" is the first part of the complete word.
            if (entry.getKey().indexOf(engWord) == 0) {
                listWord.add(entry.getKey());
            }
        }

        return listWord;
    }
}