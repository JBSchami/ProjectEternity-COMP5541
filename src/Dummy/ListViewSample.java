package Dummy;

import Eternity.GUI.EquationManagement.EquationCell;
import Eternity.Logic.Equation.EternityEquation;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListViewSample extends Application {

    ListView<EternityEquation> list = new ListView<EternityEquation>();
    ObservableList<EternityEquation> data = FXCollections.observableArrayList(
            new EternityEquation("98+123.25/8"), new EternityEquation("963.554+8"));

    @Override
    public void start(Stage stage) {
        VBox box = new VBox();
        Scene scene = new Scene(box, 200, 200);
        stage.setScene(scene);
        stage.setTitle("ListViewSample");
        box.getChildren().addAll(list);
        VBox.setVgrow(list, Priority.ALWAYS);

        list.setItems(data);

        list.setCellFactory(new Callback<ListView<EternityEquation>,
                                    ListCell<EternityEquation>>() {
                                @Override
                                public ListCell<EternityEquation> call(ListView<EternityEquation> list) {
                                    return new EquationCell();
                                }
                            }
        );

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
