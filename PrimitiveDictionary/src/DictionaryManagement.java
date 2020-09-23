import java.util.Scanner;

public class DictionaryManagement {
    static Dictionary dictionary;
    public static void  insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < dictionary.arr.length; i++) {
            String target = sc.nextLine();
            String explain = sc.nextLine();
            dictionary.arr[i] = new Word(target, explain);
        }
    }
}
