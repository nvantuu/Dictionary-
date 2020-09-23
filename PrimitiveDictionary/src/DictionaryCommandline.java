import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    public static void showAllWords() {
        System.out.println("NO      |English               |Vietnamese");
        String leftAlignFormat = "%-7d |%-25s |%s%n";
        for (int i = 0; i < dictionary.arr.length; i++) {
             System.out.printf(leftAlignFormat,(i+1), dictionary.arr[i].getWord_target(), dictionary.arr[i].getWord_explain());
        }
    }
    public static void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        dictionary = new Dictionary(n);
        dictionaryBasic();
    }
}
