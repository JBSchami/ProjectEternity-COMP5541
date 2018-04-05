package Eternity.GUI.MainView;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Eternity_View.fxml"));
        Parent root = fxmlLoader.load();
        EternityController controller = (EternityController)fxmlLoader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setTitle("Eternity");
        primaryStage.setScene(new Scene(root, 520, 640));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();
        root.requestFocus();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
