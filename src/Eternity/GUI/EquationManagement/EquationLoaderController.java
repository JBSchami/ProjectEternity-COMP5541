package Eternity.GUI.EquationManagement;

import Eternity.Logic.Equation.EternityEquation;
import Eternity.GUI.MainView.EternityController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        //Parent root;
        try {
            VBox box = new VBox();
            //FXMLLoader loader = new FXMLLoader();
            //root = loader.load(getClass().getClassLoader().getResource("Eternity/GUI/EquationManagement/EquationLoader.fxml"));
            Stage stage;
            stage = new Stage();
            stage.setTitle("Eternity Loader");
            LinkedList<EternityEquation> equations = mainController.getEternityModelHistory();
            ObservableList<EternityEquation> equationList = FXCollections.observableArrayList(equations);
            stage.setScene(new Scene(box, 250, 400));
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
        }
            );

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
            for(String line:lines){
                System.out.println(line);
            }
        }catch (IOException err){
            System.out.println("Could not load equations...");
        }
        return lines;
    }

    private Set<String> splitVariablesToSet(String variables){
        Set<String> vars = new HashSet<>();
        String[] splitString = variables.split(",");
        for(String st:splitString){
            vars.add(st);
        }
        return vars;
    }

    @FXML
    public void populateList(){
        LinkedList<EternityEquation> equations = mainController.getEternityModelHistory();
        for (EternityEquation et:equations){
            System.out.println("equation: " + et.getEquation());
        }
        ObservableList<EternityEquation> equationList = FXCollections.observableArrayList(equations);
        equationLoader.getItems().addAll(equationList);


    }
}
