import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.Scanner;
import java.io.File;

public class DictionaryManagement  {
    protected static int numberOfWords;
    protected Dictionary dictionary;

    DictionaryManagement() {
        numberOfWords = 0;
    }

    /**
     * Get the number of words in dictionary.
     * @return the number of words
     */
    public int getNumberWord() {
        return numberOfWords;
    }

    /**
     * Function imports data for the dictionary.
     * Step1 First line enter the number of words on the keyboard (Word).
     * Step2 Next line enter English words
     * Step3 Next line again enter explanation into Vietnamese and repeat step2
     */
    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);

        numberOfWords = Integer.parseInt(sc.nextLine());
        dictionary = new Dictionary();

        TreeMap<String, String> arrWord = dictionary.getMapWord();

        for (int i = 0; i < numberOfWords; i++) {
            String target = sc.nextLine();
            String explain = sc.nextLine();
            arrWord.put(target, explain);
        }
    }

    /**
     * Function imports data from file dictionaries.txt
     * Step1 enter the English words.
     * Step2 enter the mean of word after the tab.
     * Step3 adđ the English word and the mean into the map and repeat step2.
     */
    public void insertFromFile() {
        File input = new File("D:\\Learning\\Dictionary-\\Dictionary\\data\\dictionaries.txt");
        try {
            Scanner sc = new Scanner(input);

            dictionary = new Dictionary();
            TreeMap<String, String> arrWord = dictionary.getMapWord();

            while (sc.hasNext()) {
                String line = sc.nextLine();
                String engWord = line.substring(0, line.indexOf("\t"));
                String vietWord = line.substring(line.indexOf("\t") + 1);
                arrWord.put(engWord, vietWord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function print the English words and its Vietnamese meaning.
     */
    public void dictionaryLookup() {
        while (true) {
            System.out.println("Enter the keyword: ");

            Scanner sc = new Scanner(System.in);
            String keyWord = sc.nextLine();

            if (keyWord.equals("0")) {
                return;
            }

            TreeMap<String, String> mapWord = dictionary.getMapWord();

            if (mapWord.get(keyWord) != null) {
                System.out.println(mapWord.get(keyWord));
            } else {
                System.out.println("Not found keyword in the dictionary!");
            }
        }
    }
}