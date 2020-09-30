import java.io.FileNotFoundException;
import java.util.ArrayList;
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

        dictionary = new Dictionary(numberOfWords);
        ArrayList<Word> arrWord = dictionary.getArrayWord();

        for (int i = 0; i < numberOfWords; i++) {
            String target = sc.nextLine();
            String explain = sc.nextLine();
            arrWord.set(i, new Word(target, explain));
        }
    }

    public void insertFromFile() {
        File input = new File("D:\\Learning\\Dictionary-\\data\\dictionaries.txt");
        try {
            Scanner sc = new Scanner(input);

            dictionary = new Dictionary();
            ArrayList<Word> arrWord = dictionary.getArrayWord();
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String engWord = line.substring(0, line.indexOf("\t"));
                String vietWord = line.substring(line.indexOf("\t") + 1);
                arrWord.add(new Word(engWord, vietWord));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void dictionaryLookup() {
        while (true) {
            System.out.println("Enter the keyword: ");

            Scanner sc = new Scanner(System.in);
            String keyWord = sc.nextLine();

            if (keyWord.equals("0")) {
                return;
            }

            ArrayList<Word> arrWord = dictionary.getArrayWord();

            boolean found = false;
            for (Word currentWord : arrWord) {
                if (currentWord.getWord_target().equals(keyWord)) {
                    System.out.println(currentWord.getWord_explain());
                    found = true;
                    break;
                }
            }

            if (found == false) {
                System.out.println("Not found the keyword!");
            }
        }
    }
}