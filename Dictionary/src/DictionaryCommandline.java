import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DictionaryCommandline extends DictionaryManagement{

    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Search for key when user enter.
     */
    public void dictionarySearcher() {
        System.out.println("The dictionary search function: ");
        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the search key: ");
            String keySearch = sc.nextLine();
            if (keySearch.equals("0")) {
                System.out.println("Search function ended!\n");
                return;
            }

            String leftAlignFormat = "%-32s |%s %n";
            for (Map.Entry<String, String> entry : wordListOfDict.entrySet()) {
                if (entry.getKey().indexOf(keySearch) == 0) {
                    String stringRemain = entry.getKey().substring(keySearch.length());
                    System.out.printf(leftAlignFormat, ANSI_YELLOW + "|"+ keySearch + ANSI_RESET + stringRemain, entry.getValue());
                }
            }
        }
    }

    /**
     * Displays all words and meanings following the format.
     */
    public void showAllWords() {
        System.out.println("NO        |English                       |Vietnamese");
        String leftAlignFormat = "%-9d |%-32s |%s %n";

        TreeMap<String, String> wordListOfDict = dictionary.getWordListOfDict();

        int count = 0;

        for (Map.Entry<String, String> entry : wordListOfDict.entrySet()) {
            System.out.printf(leftAlignFormat, ++count, entry.getKey(), entry.getValue());
        }
    }

    /**
     * The basic dictionary.
     * Function:
     *      knows in advance the number of elements (restrictive)
     *      english words and Vietnamese meanings were entered line by line
     */
    public void dictionaryBasic() {
        insertFromCommandline();
        showAllWords();
    }

    /**
     * The advanced dictionary.
     * With many good choices
     */
    public void dictionaryAdvanced() {
        insertFromFile();
        System.out.println("Từ điển đã sẵn sàng!");
        System.out.println("Nhấn phím 1 để tìm kiếm");
        System.out.println("Nhấn phím 2 để thêm từ");
        System.out.println("Nhấn phím 3 để sửa từ");
        System.out.println("Nhấn phím 4 để xóa từ");
        System.out.println("Nhấn phím 5 để tra cứu (chỉ với từ hoàn chỉnh)");
        System.out.println("Nhấn phím 6 điển hiển thị toàn bộ từ điển");
        System.out.println("Nhấn phím 7 để xuất ra file");
        System.out.println("Nhấn phím 0 để đóng từ điển");

        Scanner sc = new Scanner(System.in);
        while (true) {
            int status = sc.nextInt();
            if (status == 1) {
                dictionarySearcher();
            } else if (status == 2) {
                dictionaryAdd();
            } else if (status == 3) {
                dictionaryFix();
            } else if (status == 4) {
                dictionaryRemove();
            } else if (status == 5) {
                dictionaryLookup();
            } else if (status == 6) {
                showAllWords();
            } else if (status == 7) {
                dictionaryExportToFile();
            } else if (status == 0) {
                return;
            }
        }
    }
}