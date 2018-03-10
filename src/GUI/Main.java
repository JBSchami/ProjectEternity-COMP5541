package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Eternity.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        Controller controller = (Controller) fxmlLoader.getController();
        primaryStage.setTitle("Eternity");
        primaryStage.setScene(new Scene(root, 500, 485));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
