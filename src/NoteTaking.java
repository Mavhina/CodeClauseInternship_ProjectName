import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.*;

public class NoteTaking {

	// Global Variables
    private TextArea textArea;
    private File currentFile;
    private BorderPane root;  // Declare the BorderPane variable

    // Constructor
    public NoteTaking() {
        textArea = new TextArea();

        root = new BorderPane();  // Initialize the BorderPane

        root.setCenter(textArea);

        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);

        this.currentFile = null;
    }

    // getters and setters
    public BorderPane getRoot() {
        return root;
    }

    // The MenuBar at the top 
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem newMenuItem = new MenuItem("New");
        newMenuItem.setOnAction(event -> newFile());

        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(event -> openFile());

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(event -> saveFile());

        MenuItem saveAsMenuItem = new MenuItem("Save As");
        saveAsMenuItem.setOnAction(event -> saveFileAs());

        fileMenu.getItems().addAll(newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem);

        Menu editMenu = new Menu("Edit");

        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.setOnAction(event -> copyText());

        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.setOnAction(event -> pasteText());

        MenuItem deleteMenuItem = new MenuItem("Delete");
        deleteMenuItem.setOnAction(event -> deleteText());

        editMenu.getItems().addAll(copyMenuItem, pasteMenuItem, deleteMenuItem);

        menuBar.getMenus().addAll(fileMenu, editMenu);

        return menuBar;
    }

    // To create a new file
    private void newFile() {
        textArea.clear();
        currentFile = null;
    }

    // To open an existing file 
    private void openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            currentFile = selectedFile;
            try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // To save the text local
    private void saveFile() {
        if (currentFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            saveFileAs();
        }
    }

    // To save the text file
    private void saveFileAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        File selectedFile = fileChooser.showSaveDialog(null);

        if (selectedFile != null) {
            currentFile = selectedFile;
            saveFile();
        }
    }

    // To copy the text
    private void copyText() {
        String selectedText = textArea.getSelectedText();
        ClipboardContent content = new ClipboardContent();
        content.putString(selectedText);
        Clipboard.getSystemClipboard().setContent(content);
    }

    // To paste the text
    private void pasteText() {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        if (clipboard.hasString()) {
            textArea.replaceSelection(clipboard.getString());
        }
    }

    // To delete the text
    private void deleteText() {
        int start = textArea.getSelection().getStart();
        int end = textArea.getSelection().getEnd();
        textArea.replaceText(start, end, "");
    }
}
