package texteditor;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller {

    public TextArea textAreaContent;
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
