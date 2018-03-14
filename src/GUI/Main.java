package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Eternity_View.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader();
        EternityController controller = (EternityController) fxmlLoader.getController();
        primaryStage.setTitle("Eternity");
        primaryStage.setScene(new Scene(root, 470, 500));
        primaryStage.show();
        root.requestFocus();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
