package GUI.EquationManager;

import Eternity.EternityEquation;
import GUI.EternityController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EquationLoaderController {
    @FXML protected ListView equationLoader;
    @FXML protected EternityController mainController;
    public static final ObservableList data = FXCollections.observableArrayList();

    private static Set<EternityEquation> equationsList = new HashSet<>();

    @FXML public void init(EternityController mainController) {
        mainController = mainController;
    }

    @FXML
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

    @FXML
    public void populateEquationsList(){
        List<String> lines = readEquations();
        int i = 0;
        for(String line:lines){
            String[] temp = line.split("<>");
            equationsList.add(new EternityEquation(temp[0], splitVariablesToSet(temp[1])));
            i++;
        }

        equationLoader.setItems(equationsList);
    }

    private Set<String> splitVariablesToSet(String variables){
        Set<String> vars = new HashSet<>();
        String[] splitString = variables.split(",");
        for(String st:splitString){
            vars.add(st);
        }
        return vars;
    }

//    @FXML
//    protected void addVarToScrollPane(){
//        VBox scrollContents = new VBox();
//        //scrollContents.getStyleClass().add("mainWrapper");
//        scrollContents.setSpacing(10);
//        int i = 0;
//        for(Button btn : varButtons){
//            HBox variableCombo = new HBox();
//            variableCombo.setSpacing(10);
//            //variableCombo.getStyleClass().add("mainWrapper");
//            variableCombo.getChildren().add(btn);
//            variableCombo.getChildren().add(valueInputs.get(i));
//            i++;
//            scrollContents.getChildren().add(variableCombo);
//        }
//        varViewer.setContent(scrollContents);
//    }

}
