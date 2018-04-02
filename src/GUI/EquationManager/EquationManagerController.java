package GUI.EquationManager;

import Eternity.EternityEquation;
import GUI.EternityController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.beans.Expression;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class EquationManagerController {
    @FXML protected TextField newVarText;
    @FXML protected static EternityController eternityController;
    @FXML protected ScrollPane varViewer;
    @FXML protected VBox vBoxRoot;
    private int variableCount;

    public static ArrayList<Button> varButtons = new ArrayList<>();
    public static ArrayList<String> varButtonNames = new ArrayList<>();
    public static ArrayList<TextField> valueInputs = new ArrayList<>();

    public int id = 0;

    @FXML public void initialize(){
        if(!varButtons.isEmpty()){
            addVarToScrollPane();
        }
    }
    public ArrayList<Button> getVarButtons() {
        return varButtons;
    }

    @FXML public void init(EternityController mainController){
        eternityController = mainController;
    }

    @FXML
    public ArrayList<Double> getValueInputs() {
        return new ArrayList<>();
    }

    @FXML
    protected void addVarToList(){
        if(!newVarText.getText().isEmpty()) {
            if(!varButtonNames.contains(newVarText.getText())){
            Button buttonVar = new Button(newVarText.getText());
            varButtonNames.add(newVarText.getText());

                buttonVar.getStyleClass().add("largeButton");
                buttonVar.getStyleClass().add("button");
                buttonVar.getStyleClass().add("equationManagerButton");
                buttonVar.setId("VarBtn" + variableCount);
                buttonVar.setOnAction(event -> eternityController.addVariableToEquation(buttonVar.getText()));
                varButtons.add(buttonVar);


                Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");
                UnaryOperator<TextFormatter.Change> filter = c -> {
                    String text = c.getControlNewText();
                    if (validEditingState.matcher(text).matches()) {
                        return c;
                    } else {
                        return null;
                    }
                };
                StringConverter<Double> converter = new StringConverter<Double>() {

                    @Override
                    public Double fromString(String s) {
                        if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                            return 0.0;
                        } else {
                            return Double.valueOf(s);
                        }
                    }


                    @Override
                    public String toString(Double d) {
                        return d.toString();
                    }
                };
                TextFormatter<Double> textFormatter = new TextFormatter<Double>(converter, 0.0, filter);
                TextField varValue = new TextField();
                varValue.setTextFormatter(textFormatter);
                varValue.setPromptText("Enter Variable Value");
                varValue.setId("VarValue" + variableCount);
                varValue.getStyleClass().add("inputVarValue");
                variableCount++;
                valueInputs.add(varValue);
                addVarToScrollPane();
                newVarText.clear();
            }
        }
    }

    @FXML
    protected void addVarToScrollPane(){
        VBox scrollContents = new VBox();
        //scrollContents.getStyleClass().add("mainWrapper");
        scrollContents.setSpacing(10);
        int i = 0;
        for(Button btn : varButtons){
            HBox variableCombo = new HBox();
            variableCombo.setSpacing(10);
            //variableCombo.getStyleClass().add("mainWrapper");
            variableCombo.getChildren().add(btn);
            variableCombo.getChildren().add(valueInputs.get(i));
            i++;
            scrollContents.getChildren().add(variableCombo);
        }
        varViewer.setContent(scrollContents);
    }

    @FXML
    public ArrayList<Double> printAllTextFieldValues(){
        ArrayList<Double> values = new ArrayList<>();
        for(TextField tf : valueInputs){
            values.add(Double.parseDouble(tf.getText()));
        }
        return values;
    }

    public void clearEquationVariables(){
        varButtons.clear();
        valueInputs.clear();
        varButtonNames.clear();
    }

    public void saveEquation(EternityEquation equationToSave){
        Set<String> variableNames = equationToSave.getVariable();
        String expression = equationToSave.getEquation();
        String variableNameString = new String();
        int i = 0;
        for(String varName:variableNames){
            if(i==0){
                variableNameString = varName;
            }
            else{
                variableNameString = variableNameString.concat(","+varName);
            }
            i++;
        }
        List<String> lines = Arrays.asList(expression + "%%" + variableNameString);
        try{
            Path file = Paths.get("./Equations/equations.txt");
            Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        }catch (IOException err){
            System.out.println("Could not load equations...");
        }
    }


}
