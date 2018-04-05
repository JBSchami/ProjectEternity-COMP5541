package Eternity.GUI.EquationManagement.EquationLoader;

import Eternity.GUI.EquationManagement.EquationCell.EquationCell;
import Eternity.Logic.Equation.EternityEquation;
import Eternity.GUI.MainView.EternityController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class EquationLoaderController {
    @FXML protected ListView<EternityEquation> equationLoader;
    @FXML private EternityController mainController;

    @FXML public void init(EternityController mainController) {
        this.mainController = mainController;
        equationLoader = new ListView<>();
        try {
            VBox box = new VBox();

            Stage stage;
            stage = new Stage();
            stage.setTitle("Eternity Equation Loader");

            ArrayList<EternityEquation> equations = loadEquations();
            ObservableList<EternityEquation> equationList = FXCollections.observableArrayList(equations);

            stage.setScene(new Scene(box, 450, 400));
            stage.setOnHidden(e -> {
                mainController.setEqLoaderActive(false);
            });
            box.getChildren().addAll(equationLoader);
            VBox.setVgrow(equationLoader, Priority.ALWAYS);


            equationLoader.setItems(equationList);
            equationLoader.setCellFactory(new Callback<ListView<EternityEquation>,ListCell<EternityEquation>>() {
                   @Override
            public ListCell<EternityEquation> call(ListView<EternityEquation> list) {
                return new EquationCell();
            }
            });

            equationLoader.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EternityEquation>() {

                @Override
                public void changed(ObservableValue<? extends EternityEquation> observable, EternityEquation oldValue, EternityEquation newValue) {
                    mainController.loadEquation(newValue);
                    stage.hide();
                }
            });

            stage.show();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    private List<String> readEquations(){
        List<String> lines = new ArrayList<>();
        try{
            Path file = Paths.get("./Equations/equations.txt");
            lines = Files.readAllLines(file, Charset.forName("UTF-8"));
        }catch (IOException err){
            System.out.println("Could not load equations...");
        }
        return lines;
    }

    public ArrayList<EternityEquation> loadEquations(){
        ArrayList<EternityEquation> equations = new ArrayList<>();
        List<String> equationDetails = readEquations();
        for(String st:equationDetails){
            String[] splitDetails = st.split("%%");
            equations.add(new EternityEquation(
                    splitDetails[0],
                    splitDetails[1],
                    splitDetails[2],
                    splitVariablesToSet(splitDetails[3])));
        }
        return equations;
    }

    private Set<String> splitVariablesToSet(String variables){
        Set<String> vars = new HashSet<>();
        String[] splitString = variables.split(",");
        for(String st:splitString){
            vars.add(st);
        }
        return vars;
    }
}
