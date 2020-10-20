public class Main {
    public static final String FXML_FILE_PATH = "Application.fxml";
    public static final String DATA_FILE_PATH = "data/dictionaries.txt";

    /**
     * The main function.
     */
    public static void main(String[] args) {
        DictionaryApplication appDictionary = new DictionaryApplication();
        appDictionary.runApplication(args);
    }
}