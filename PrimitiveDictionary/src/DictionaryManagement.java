import java.util.Scanner;

public class DictionaryManagement {
    static Dictionary dictionary;

    /**
     * Function imports data for the dictionary.
     * Step1 First line enter the number of words on the keyboard (Word).
     * Step2 Next line enter English words
     * Step3 Next line again enter explanation into Vietnamese and repeat step2
     */
    public static void  insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int numberOfWords = Integer.parseInt(sc.nextLine());
        dictionary = new Dictionary(numberOfWords);
        for (int i = 0; i < dictionary.arr.length; i++) {
            String target = sc.nextLine();
            String explain = sc.nextLine();
            dictionary.arr[i] = new Word(target, explain);
        }
    }
}
