package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.PrimitiveIterator;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Eternity_View.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        EternityController controller = (EternityController) fxmlLoader.getController();
        primaryStage.setTitle("Eternity");
        primaryStage.setScene(new Scene(root, 420, 580));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
        root.requestFocus();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
