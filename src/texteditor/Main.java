package texteditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("xml_editor.fxml"));
        primaryStage.setTitle("Simple Text Editor");
        primaryStage.setScene(new Scene(root, 600, 400));
        Controller controller = new Controller();
        controller.setStage(primaryStage);//Enviando el Stage al Controller
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }



}
