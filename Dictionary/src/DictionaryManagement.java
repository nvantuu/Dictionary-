import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.TreeMap;
import java.util.Map;

public class DictionaryManagement  {
    protected Dictionary dictionary;

    /**
     * Display function of dictionary.
     */
    public void displayFunction() {
        System.out.println("Nhấn phím 1 để tìm kiếm");
        System.out.println("Nhấn phím 2 để thêm từ");
        System.out.println("Nhấn phím 3 để sửa từ");
        System.out.println("Nhấn phím 4 để xóa từ");
        System.out.println("Nhấn phím 5 để tra cứu (chỉ với từ hoàn chỉnh)");
        System.out.println("Nhấn phím 6 điển hiển thị toàn bộ từ điển");
        System.out.println("Nhấn phím 7 để xuất ra file");
        System.out.println("Nhấn phím 0 để đóng từ điển, hoặc để kết thúc chức năng hiện tại");
    }

    /**
     * Function imports data for the dictionary.
     * First line enter the number of words on the keyboard (numberOfWord).
     * With each word (1 --> numberOfWord), will enter two lines:
     *      First line contains English Word
     *      The following line contains Vietnamese meaning
     */
    public void insertFromCommandline() {
        dictionary = new Dictionary();
        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();

        Scanner sc = new Scanner(System.in);
        int numberOfWords = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < numberOfWords; i++) {
            String target = sc.nextLine();
            String explain = sc.nextLine();
            wordListOfDict.put(target, explain);
        }
    }

    /**
     * Function get data from database (txt file).
     */
    public void insertFromFile() {
        File dataFileIsFormatted = new File("D:\\Learning\\Dictionary-\\Dictionary\\data\\dictionaries.txt");
        try {
            Scanner scanner = new Scanner(dataFileIsFormatted);

            dictionary = new Dictionary();
            TreeMap<String, String> wordListInDict = dictionary.getWordListOfDict();

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();
                String target = str.substring(0, str.indexOf("\t"));
                String explain = str.substring(str.indexOf("\t") + 1);
                wordListInDict.put(target, explain);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Lookup the complete word in dictionary.
     */
    public void dictionaryLookup() {
        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();
        System.out.println("___LOOK UP THE WORD IN DICTIONARY___");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Look up the word: ");

            String keyWord = sc.nextLine();

            if (keyWord.equals("0")) {
                System.out.println("Look up ended!\n");
                displayFunction();
                return;
            }

            if (wordListOfDict.get(keyWord) != null) {
                System.out.print("The Vietnamese meaning: ");
                System.out.println(wordListOfDict.get(keyWord));
            } else {
                System.out.println("Can not find the word you need to look up! Try again!");
            }
        }
    }

    /**
     * Add the new word into dictionary.
     */
    public void dictionaryAdd() {
        System.out.println("___ADD THE WORD INTO DICTIONARY___");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Add the new English word: ");
            String target = sc.nextLine();
            if (target.equals("0")) {
                System.out.println("Add ended!\n");
                displayFunction();
                return;
            }
            System.out.print("Enter the Vietnamese meaning: ");
            String explain = sc.nextLine();
            dictionary.getWordListOfDict().put(target, explain);
        }

    }

    /**
     * Remove the word you want.
     */
    public void dictionaryRemove() {
        System.out.println("___REMOVE THE WORD FROM DICTIONARY___");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Remove the word: ");
            String keyWord = sc.nextLine();

            if (keyWord.equals("0")) {
                System.out.println("Remove ended!\n");
                displayFunction();
                return;
            }
            if (dictionary.getWordListOfDict().get(keyWord) != null) {
                dictionary.getWordListOfDict().remove(keyWord);
            } else {
                System.out.println("Can not find the word you need to remove! Try again!");
            }
        }
    }

    /**
     * Fix meaning of the word you want fix.
     */
    public void dictionaryFix() {
        System.out.println("___FIX THE WORD IN DICTIONARY___");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Fix the word: ");
            String keyWord = sc.nextLine();

            if (keyWord.equals("0")) {
                System.out.println("Fix ended!\n");
                displayFunction();
                return;
            }

            System.out.print("The new Vietnamese meaning: ");
            String newExplain = sc.nextLine();
            if (dictionary.getWordListOfDict().containsKey(keyWord)) {
                dictionary.getWordListOfDict().put(keyWord, newExplain);
            } else {
                System.out.println("Can not find the word you need to fix! Try again!");
            }
        }

    }

    /**
     * Export current data of dictionary to files.
     */
    public void dictionaryExportToFile() {
        System.out.println("___EXPORT DATA OF DICTIONARY TO FILE___");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the path: ");
        String path = sc.nextLine();
        try {
            FileWriter fw = new FileWriter(path);
            TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();

            for (Map.Entry<String, String> entry : wordListOfDict.entrySet()) {
                fw.write(entry.getKey() + "    " + entry.getValue() + "\n");
            }

            fw.close();
            System.out.println("Current data of dictionary has been exported to file!");
            displayFunction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}