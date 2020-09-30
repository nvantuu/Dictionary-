import java.util.Scanner;

public class DictionaryManagement  {
    protected static int numberOfWords;
    protected Dictionary dictionary;

    DictionaryManagement() {
        numberOfWords = 0;
        dictionary = null;
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
        Word[] arrWord = dictionary.getArrayWord();

        for (int i = 0; i < numberOfWords; i++) {
            String target = sc.nextLine();
            String explain = sc.nextLine();
            arrWord[i] = new Word(target, explain);
        }
    }
}