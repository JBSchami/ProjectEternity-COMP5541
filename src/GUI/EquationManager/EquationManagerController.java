package GUI.EquationManager;

import GUI.EternityController;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class EquationManagerController {
    @FXML protected TextField newVarText;
    @FXML protected static EternityController eternityController;
    @FXML protected ScrollPane varViewer;
    @FXML protected Scene root;
    @FXML protected VBox vBoxRoot;

    public static ArrayList<Button> varButtons = new ArrayList<>();
    public static ArrayList<TextField> valueInputs = new ArrayList<>();
    public int id = 0;

    @FXML public void initialize(){
        if(!varButtons.isEmpty()){
            addVarToScrollPane();
        }
        root = vBoxRoot.getScene();
    }

    private static int variableCount = 0;

    public int getVariableCount() {
        return variableCount;
    }

    public ArrayList<Button> getVarButtons() {
        return varButtons;
    }

    @FXML
    public ArrayList<Double> getValueInputs() {
        ArrayList<Double> temp = new ArrayList<>();
        for(int i = 0; i < variableCount; i++){
            try{
                TextField tf = (TextField) root.lookup("#VarValue" + i);
                System.out.println("WHAT THE FUCK IS GOING ON");
                temp.add(Double.parseDouble(tf.getText()));
            }catch (NullPointerException ex){
                ex.printStackTrace();
            }
        }
        return temp;
    }

    @FXML public void init(EternityController mainController){
        eternityController = mainController;
    }

    public void helloButton(String what){
        System.out.println(what);
    }

    @FXML
    protected void addVarToList(){
        for(Button btn : varButtons){
            System.out.println(btn.getText());
        }
        if(!newVarText.getText().isEmpty()) {
            Button buttonVar = new Button(newVarText.getText());
            buttonVar.getStyleClass().add("largeButton");
            buttonVar.getStyleClass().add("button");
            buttonVar.getStyleClass().add("numButton");
            buttonVar.setId("VarBtn" + variableCount);
            buttonVar.setOnAction(event -> eternityController.addVariableToEquation(buttonVar.getText()));
            varButtons.add(buttonVar);

            TextField varValue = new TextField();
            varValue.setPromptText("Enter Variable Value");
            varValue.setId("VarValue" + variableCount);
            variableCount++;
            valueInputs.add(varValue);
            addVarToScrollPane();
            newVarText.clear();
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

    public void clearEquationVariables(){
        variableCount = 0;
        varButtons.clear();
    }

    public boolean validateVariableValues(){
        for(int i = 0; i < valueInputs.size(); i++){
            if(valueInputs.get(i).equals("")){
                return false;
            }
        }
        return true;
    }
}
