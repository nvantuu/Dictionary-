import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.Set;
import java.util.TreeMap;

public class DictionaryManagement  {
    protected Dictionary dictionary;

    /**
     * Function imports data for the dictionary.
     * First line enter the number of words on the keyboard (numberOfWord).
     * With each word (1 --> numberOfWord), will enter two lines:
     *      First line contains English Word
     *      The following line contains Vietnamese meaning
     */
    public void insertFromCommandline() {
        dictionary = new Dictionary();
        TreeMap<String, String> wordListInDict = dictionary.getWordListOfDict();

        Scanner sc = new Scanner(System.in);
        int numberOfWords = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfWords; i++) {
            String target = sc.nextLine();
            String explain = sc.nextLine();
            wordListInDict.put(target, explain);
        }
    }

    /**
     * Function get data from database (txt file).
     */
    public void insertFromFile() {
        File dataFileIsFormatted = new File("D:\\Dictionary-\\Dictionary\\data\\dictionaries.txt");
        try {
            Scanner scanner = new Scanner(dataFileIsFormatted);

            dictionary = new Dictionary();
            TreeMap<String, String> wordListInDict = dictionary.getWordListOfDict();

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String target = str.substring(0, str.indexOf("\t"));
                String explain = str.substring(str.indexOf("\t")+1);
                wordListInDict.put(target, explain);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Lookup the complete word in dictionary.
     */
    public void dictionaryLookup() {
        TreeMap<String, String> wordListInDict = dictionary.getWordListOfDict();
        System.out.println("+Lookup():");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the key word: ");

            String keyWord = sc.nextLine();

            if (keyWord.equals("0")) {
                System.out.println("Lookup ended!\n");
                return;
            }

            if (wordListInDict.get(keyWord) != null) {
                System.out.print("Explain Vietnamese meaning: ");
                System.out.println(wordListInDict.get(keyWord));
            } else {
                System.out.println("Ended! Can't find the word you need to lookup");
            }
        }
    }

    /**
     * Add the new word into dictionary.
     */
    public void dictionaryAdd() {
        System.out.println("+Add():");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the new English word: ");
            String target = sc.nextLine();
            if (target.equals("0")) {
                System.out.println("Add ended!\n");
                return;
            }
            System.out.print("Enter explain Vietnamese meaning: ");
            String explain = sc.nextLine();
            dictionary.getWordListOfDict().put(target, explain);
        }

    }

    /**
     * Remove the word you want.
     */
    public void dictionaryRemove() {
        System.out.println("+Remove():");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the word you need delete: ");
            String keyWord = sc.nextLine();

            if (keyWord.equals("0")) {
                System.out.println("Remove ended!\n");
                return;
            }
            if (dictionary.getWordListOfDict().get(keyWord) != null) {
                dictionary.getWordListOfDict().remove(keyWord);
            } else {
                System.out.println("Ended! Can't find the word you need to remove");
            }
        }
    }

    /**
     * Fix meaning of the word you want fix.
     */
    public void dictionaryFix() {
        System.out.println("+Fix():");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the word you want to fix: ");
            String keyWord = sc.nextLine();

            if (keyWord.equals("0")) {
                System.out.println("Fix ended!\n");
                return;
            }

            System.out.print("New meaning: ");
            String newExplain = sc.nextLine();
            if (dictionary.getWordListOfDict().containsKey(keyWord)) {
                dictionary.getWordListOfDict().put(keyWord, newExplain);
            } else {
                System.out.println("Ended! Can't find the word you need to fix");
            }
        }

    }

    /**
     * Export current data of dictionary to files.
     */
    public void dictionaryExportToFile() {
        System.out.println("+Export data():");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the path: ");
        String path = sc.nextLine();
        try {
            FileWriter fw = new FileWriter(path);
            TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();
            Set<String> set = wordListOfDict.keySet();
            for (String key : set) {
                fw.write(key + "    " +wordListOfDict.get(key) + "\n");
            }
            fw.close();
            System.out.println("Current data of dictionary has been exported to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}