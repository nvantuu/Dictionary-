public class Main {
    public static final String FXML_FILE_PATH = "Application.fxml";
    public static final String DATA_FILE_PATH = "data/dictionaries.txt";
    public static final String ICON_IMAGE_PATH = "image/icon.png";
    public static String CSS_FILE_PATH = "blue.css";
    public static String LOGO_IMAGE_PATH = "image/blueLogo.png";

    /**
     * The main function.
     */
    public static void main(String[] args) {
        DictionaryApplication appDictionary = new DictionaryApplication();
        appDictionary.runApplication(args);
    }
}