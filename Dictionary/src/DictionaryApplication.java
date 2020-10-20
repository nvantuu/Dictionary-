import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.text.FontWeight.BOLD;

public class DictionaryApplication extends Application {
    private static final int lengthFuncSearch = 11;
    private static final int lengthFuncDefault = 8;
    private static final int lengthFuncAdd = 13;
    private static final int lengthFuncDelete = 11;
    private static final int lengthFuncFix = 13;
    private static final int lengthFuncExport = 12;

    private TextField engText;
    private TextArea vietText;
    private TextField pathText;
    private Label engLabel;
    private Label vietLabel;
    private ListView<String> listView;
    @FXML
    private ListView<String> historySearch;
    private static ArrayList<String> listHistorySearch;

    private static DictionaryManagement management;

    public DictionaryApplication() {
        management = new DictionaryManagement();
        listHistorySearch = new ArrayList<>();
    }

    public void initialize() throws IOException {
        management.insertFromFile();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource(Main.FXML_FILE_PATH));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Dictionary Application 3T");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Initialize dictionary from dictionaries.txt.
        initialize();
    }

    public void runApplication(String[] args) {
        launch(args);
    }

    public Label setLabel(String text, int layoutX, int layoutY, int width, int height) {
        Label label = new Label(text);
        label.setLayoutX(layoutX);
        label.setLayoutY(layoutY);
        label.setPrefSize(width, height);
        return label;
    }

    public Button setButton(String text, int layoutX, int layoutY, int width, int height) {
        Button button = new Button(text);
        button.setLayoutX(layoutX);
        button.setLayoutY(layoutY);
        button.setPrefSize(width, height);
        return button;
    }

    public TextField setTextField(int layoutX, int layoutY, int width, int height) {
        TextField textField = new TextField();
        textField.setLayoutX(layoutX);
        textField.setLayoutY(layoutY);
        textField.setPrefSize(width, height);
        return textField;
    }

    public TextArea setTextArea(int layoutX, int layoutY, int width, int height) {
        TextArea textArea = new TextArea();
        textArea.setLayoutX(layoutX);
        textArea.setLayoutY(layoutY);
        textArea.setPrefSize(width, height);
        return textArea;
    }

    public ListView<String> setListView(ObservableList<String> list, int layoutX, int layoutY, int width, int height) {
        ListView<String> listView = new ListView<>(list);
        listView.setLayoutX(layoutX);
        listView.setLayoutY(layoutY);
        listView.setPrefSize(width, height);
        return listView;
    }

    public static void resetFuncView(AnchorPane root) {
        if (getLength(root) > lengthFuncDefault) {
            int subtract = getLength(root) - lengthFuncDefault;
            root.getChildren().remove(root.getChildren().toArray().length - subtract
                    , root.getChildren().toArray().length);
        }
        for (Object obj : root.getChildren().toArray()) {
            if (obj instanceof Button) {
                Button button = (Button) obj;
                button.setEffect(null);
            }
        }
    }

    public static void setEffectButtonFunc(AnchorPane root, String nameFunc) {
        for (Object obj : root.getChildren().toArray()) {
            if (obj instanceof Button) {
                Button button = (Button) obj;
                if (button.getText().equals(nameFunc)) {
                    button.setEffect(new Lighting());
                    break;
                }
            }
        }
    }

    public static int getLength(AnchorPane root) {
        return root.getChildren().toArray().length;
    }

    public static Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public static AnchorPane getRoot(Stage stage) {
        return (AnchorPane) stage.getScene().getRoot();
    }

    public void loadFuncView(ActionEvent event) {
        Stage stage = getStage(event);
        AnchorPane root = getRoot(stage);
        String nameFunc = ((Button) event.getSource()).getText();

        resetFuncView(root);
        loadHistorySearch(root);
        setEffectButtonFunc(root, nameFunc);

        switch (nameFunc) {
            case "Search" : loadSearchView(root);
                break;
            case "Add" : loadAddView(root);
                break;
            case "Delete" : loadDeleteView(root);
                break;
            case "Fix" : loadFixView(root);
                break;
            case "Export" : loadExportView(stage);
                break;
            default:
        }
    }

    public void loadSearchView(AnchorPane root) {
        engLabel = setLabel("English word", 125, 113, 70, 17);
        engText = setTextField(125, 136, 320,35);
        engText.setOnKeyReleased(keyEvent -> displayListView(root));
        Button searchButton = setButton("Search", 390, 136, 55, 35);
        searchButton.setOnAction(actionEvent -> displayVietnameseMeaning(root, 0));
        root.getChildren().addAll(engLabel, engText, searchButton);
    }

    public void loadAddView(AnchorPane root) {
        engLabel = setLabel("English word", 125, 113, 70, 17);
        vietLabel = setLabel("Vietnamese meaning", 125, 187, 118, 17);
        engText = setTextField(125, 136, 320,35);
        vietText = setTextArea(125, 210, 320, 124);
        Button addButton = setButton("Add", 390, 350, 55, 35);
        addButton.setOnAction(actionEvent -> addAction(root));
        root.getChildren().addAll(engLabel, vietLabel, engText, vietText, addButton);
    }

    public void loadDeleteView(AnchorPane root) {
        engLabel = setLabel("English word", 125, 113, 70, 17);
        engText = setTextField(125, 136, 320,35);
        Button deleteButton = setButton("Delete", 390, 187, 55, 35);
        deleteButton.setOnAction(actionEvent -> deleteAction(root));
        root.getChildren().addAll(engLabel, engText, deleteButton);
    }

    public void loadFixView(AnchorPane root) {
        engLabel = setLabel("English word", 125, 113, 70, 17);
        vietLabel = setLabel("Vietnamese meaning", 125, 187, 118, 17);
        engText = setTextField(125, 136, 320,35);
        vietText = setTextArea(125, 210, 320, 124);
        Button fixButton = setButton("Fix", 390, 350, 55, 35);
        fixButton.setOnAction(actionEvent -> fixAction(root));
        root.getChildren().addAll(engLabel, vietLabel, engText, vietText, fixButton);
    }

    public void loadExportView(Stage stage) {
        AnchorPane root = getRoot(stage);
        Label pathLabel = setLabel("Enter the path", 125, 113, 100, 17);
        pathText = setTextField(125, 136, 255,35);
        Button chooseFileButton = setButton("Open...", 390, 136, 55, 35);
        chooseFileButton.setOnAction(actionEvent -> chooseFile(stage));
        Button exportButton = setButton("Export", 390, 187, 55, 35);
        exportButton.setOnAction(actionEvent -> {
            try {
                exportAction(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().addAll(pathLabel, pathText, chooseFileButton, exportButton);
    }

    public void displayListView(AnchorPane root) {
        if (getLength(root) > lengthFuncSearch) {
            int subtract = getLength(root) - lengthFuncSearch;
            root.getChildren().remove(getLength(root) - subtract, getLength(root));
        }
        String engWord = engText.getText();
        ArrayList<String> listWord = management.dictionaryLookup(engWord);
        if (!listWord.isEmpty()) {
            ObservableList<String> list = FXCollections.observableList(listWord);

            listView = setListView(list, 125, 187, 255, 198);
            listView.setOnMouseClicked(mouseEvent -> displayVietnameseMeaning(root, 1));
            root.getChildren().add(listView);
        } else {
            Label label1 = setLabel("Try again!", 125, 187, 255, 17);
            label1.setFont(Font.font("System", BOLD, 12));
            Label label2 = setLabel("The word \"" + engWord + "\" is not in the dictionary.", 125, 205, 255, 17);
            root.getChildren().addAll(label1, label2);
        }
    }

    public void displayVietnameseMeaning(AnchorPane root, int sourceAction) {
        String engWord;
        if (sourceAction == 0) {
            engWord = engText.getText();
        } else {
            engWord = listView.getSelectionModel().getSelectedItem();
            engText.setText(engWord);
        }

        String vietWord = management.dictionarySearch(engWord);
        Label label1;
        Label label2;

        if (getLength(root) > lengthFuncSearch) {
            int subtract = getLength(root) - lengthFuncSearch;
            root.getChildren().remove(getLength(root) - subtract, getLength(root));
        }

        if (vietWord.equals("Not found!")) {
            label1 = setLabel("Try again!", 125, 187, 255, 17);
            label1.setFont(Font.font("System", BOLD, 12));
            label2 = setLabel("The word \"" + engWord + "\" is not in the dictionary.", 125, 205, 255, 17);
            root.getChildren().addAll(label1, label2);
        } else {
            //Add English word to list history search.
            loadToListHistory(engWord);
            loadHistorySearch(root);

            label1 = setLabel("English word", 125, 187, 255, 17);
            label2 = setLabel(engWord, 125, 205, 255, 17);
            label1.setFont(Font.font("System", BOLD, 12));
            Label label3 = setLabel("Vietnamese meaning", 125, 238, 255, 17);
            Label label4 = setLabel(vietWord, 125, 256, 255, 17);
            label3.setFont(Font.font("System", BOLD, 12));
            root.getChildren().addAll(label1, label2, label3, label4);
        }
    }

    public void addAction(AnchorPane root) {
        String engWord = engText.getText();
        String vietWord = vietText.getText();
        management.dictionaryAdd(engWord, vietWord);

        Label label = setLabel("Add \"" + engWord + "\" complete!", 125, 350, 255, 17);
        if (getLength(root) > lengthFuncAdd) {
            root.getChildren().remove(getLength(root) - 1);
        }
        root.getChildren().add(label);
    }

    public void deleteAction(AnchorPane root) {
        String engWord = engText.getText();
        boolean check = management.dictionaryDelete(engWord);
        Label label1;
        Label label2;

        if (check) {
            label1 = setLabel(" ", 125, 187, 255, 17);
            label2 = setLabel("Delete \"" + engWord + "\" complete!", 125, 205, 255, 17);
        } else {
            label1 = setLabel("Try again!", 125, 187, 255, 17);
            label1.setFont(Font.font("System", BOLD, 12));
            label2 = setLabel("The word \"" + engWord + "\" is not in the dictionary.", 125, 205, 255, 17);
        }
        if (getLength(root) > lengthFuncDelete) {
            int subtract = getLength(root) - lengthFuncDelete;
            root.getChildren().remove(getLength(root) - subtract, getLength(root));
        }
        root.getChildren().addAll(label1, label2);
    }

    public void fixAction(AnchorPane root) {
        String engWord = engText.getText();
        String vietWord = vietText.getText();
        boolean check = management.dictionaryFix(engWord, vietWord);
        Label label1;
        Label label2;

        if (check) {
            label1 = setLabel(" ", 125, 340, 255, 17);
            label2 = setLabel("Fix \"" + engWord + "\" complete!", 125, 358, 255, 17);
        } else {
            label1 = setLabel("Try again!", 125, 340, 255, 17);
            label1.setFont(Font.font("System", BOLD, 12));
            label2 = setLabel("Do not find \"" + engWord + "\" in the dictionary.", 125, 358, 255, 17);
        }
        if (getLength(root) > lengthFuncFix) {
            int subtract = getLength(root) - lengthFuncFix;
            root.getChildren().remove(getLength(root) - subtract, getLength(root));
        }
        root.getChildren().addAll(label1, label2);
        management.showAllWords();
    }

    public void exportAction(AnchorPane root) throws IOException {
        String path = pathText.getText();
        Label label;
        if (path.equals("")) {
            label = setLabel("Invalid path!", 125, 205, 255, 17);
            label.setFont(Font.font("System", BOLD, 12));
        } else {
            String fileName = new File(path).toURI().getPath();
            management.dictionaryExportToFile(fileName);

            label = setLabel("Export to \"" + fileName + "\" complete!", 125, 205, 255, 17);
        }
        if (getLength(root) > lengthFuncExport) {
            root.getChildren().remove(getLength(root) - 1);
        }
        root.getChildren().add(label);
    }

    public void chooseFile(Stage stage) {
        FileChooser fileExport = new FileChooser();
        fileExport.setTitle("Choose file to export...");
        FileChooser.ExtensionFilter fileFilter = new FileChooser.ExtensionFilter("Text File", "*.txt");
        fileExport.getExtensionFilters().add(fileFilter);
        File file = fileExport.showOpenDialog(stage);
        if (file != null) {
            pathText.setText(file.toPath().toString());
        }
    }

    public void settingAction() {

    }

    public void loadToListHistory(String newWord) {
        listHistorySearch.remove(newWord);
        listHistorySearch.add(0, newWord);
    }

    public void loadHistorySearch(AnchorPane root) {
        ObservableList<String> list = FXCollections.observableList(listHistorySearch);
        historySearch.setItems(list);
        historySearch.setOnMouseClicked(mouseEvent -> {
            engText.setText(historySearch.getSelectionModel().getSelectedItem());
            displayVietnameseMeaning(root, 0);
            historySearch.getSelectionModel().clearSelection();
        });
    }
}
