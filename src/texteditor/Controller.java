package texteditor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sun.font.FontFamily;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {

    public TextArea textAreaContent;
    public MenuItem copyEditMenu;
    public MenuItem cutEditMenu;
    public MenuItem copyTextArea;
    public MenuItem cutTextArea;
    public CheckMenuItem sansFontFamily;
    public CheckMenuItem freeFontFamily;
    public CheckMenuItem twelveFontSize;
    public CheckMenuItem fourteenFontSize;
    private Stage stage;//Only for FileChooser
    private FileChooser fileChooser = new FileChooser();
    private File openFile;
    private File saveFile;



    public void initialize(){
        System.out.println("iniciando . .");

    }


/*********************** FILE MENU ***********************/
    /**
     * Opción del menú para salir del programa
     * */
    public void exitAction(ActionEvent ae){
        System.out.println("Finalizado . .");
        Platform.exit();
    }

    /**
     * Opción de menú para abrir un archivo
     * Abre el archivo y muestra su contenido en el TextArea
     * */
    public void openFileAction(ActionEvent ae) {
        configureFileChooser(fileChooser, "Open File to Read");

        try {
            openFile = fileChooser.showOpenDialog(stage);
            if (openFile != null) {
//                System.out.println(openFile.getAbsolutePath());
//                stage.setTitle("holaaaaaaaaaaaaaaaaaa");
                FileClass fileClass = new FileClass();
                String openContentFile = fileClass.getContentFile(openFile);
                System.out.println("texto" + openContentFile);
                textAreaContent.setText(openContentFile);
            }else
                System.out.println("No Files Selected");

        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException e) {
            System.out.println("Error to read file (bad file)");
        }

    }
    /**
     * Opción del menú que guarda el contenido modificado del TextArea
     * en el mismo archivo abierto
     * */
    public void saveFileAction(ActionEvent ae) {
        if (openFile != null){
            FileClass fileClass = new FileClass();
            try {
                fileClass.setContentFile(openFile, textAreaContent.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /**
     * Opción del menú que guarda el contenido modificado del TextArea
     * en un nuevo archivo
     * */
    public void saveAsFileAction(ActionEvent ae) {
        configureFileChooser(fileChooser, "Save As New File");
        saveFile = fileChooser.showSaveDialog(stage);
        if (saveFile != null){
            FileClass fileClass = new FileClass();
            try {
                fileClass.setContentFile(saveFile, textAreaContent.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    /*********************** EDIT MENU ***********************/
    public void enableDisableItems(Event event) {
        if (textAreaContent.getSelectedText().equals("")){
            copyEditMenu.setDisable(true);
            cutEditMenu.setDisable(true);
            copyTextArea.setDisable(true);
            cutTextArea.setDisable(true);
        }else{
            copyEditMenu.setDisable(false);
            cutEditMenu.setDisable(false);
            copyTextArea.setDisable(false);
            cutTextArea.setDisable(false);
        }
    }
    public void copyTextAction(ActionEvent ae) {
        textAreaContent.copy();
    }
    public void cutTextAction(ActionEvent ae) {
        textAreaContent.cut();
    }
    public void pasteTextAction(ActionEvent ae) {
        textAreaContent.paste();
    }
    public void undoTextAction(ActionEvent ae) {
        textAreaContent.undo();
    }
    public void redoTextAction(ActionEvent ae) {
        textAreaContent.redo();
    }

    /*********************** OPTIONS MENU ***********************/
    public void fontFamilyAction(ActionEvent ae) {
        CheckMenuItem item = (CheckMenuItem) ae.getSource();
        sansFontFamily.setSelected(false);
        freeFontFamily.setSelected(false);
        item.setSelected(true);
        textAreaContent.setFont(new Font(item.getText(), textAreaContent.getFont().getSize()));

    }

    public void fontSizeAction(ActionEvent ae) {
        CheckMenuItem item = (CheckMenuItem) ae.getSource();
        fourteenFontSize.setSelected(false);
        twelveFontSize.setSelected(false);
        item.setSelected(true);
        double fontSize = Double.parseDouble( item.getText() );
        textAreaContent.setFont(new Font(fontSize));
    }


    /*********************** HELP MENU ***********************/
    public void aboutHelpAction(ActionEvent ae) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About TextEditor");
        alert.setHeaderText("TextEditor v. 0.8");
        alert.setContentText("Copyright © 2015-2016 TextEditor Beta\n" +
                             "Developed by: Diego Zavaleta");
        alert.showAndWait();
    }




    private void configureFileChooser(FileChooser fileChooser, String msj) {
        fileChooser.setTitle(msj);

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files (*.*)", "*.*"),
                new FileChooser.ExtensionFilter("HTML (*.html;*.xhtml)", "*.html;*.xhtml"),
                new FileChooser.ExtensionFilter("Java (*.java)", "*.java"),
                new FileChooser.ExtensionFilter("SQL (*.sql)", "*.sql"),
                new FileChooser.ExtensionFilter("TXT (*.txt)", "*.txt")
        );

    }
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }


}
