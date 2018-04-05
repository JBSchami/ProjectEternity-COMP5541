package Eternity.GUI.EquationManagement.EquationManager;

import Eternity.Logic.Equation.EternityEquation;
import Eternity.GUI.MainView.EternityController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class EquationManagerController {
    @FXML protected TextField newVarText;
    @FXML private static EternityController eternityController;
    @FXML protected ScrollPane varViewer;
    @FXML protected VBox vBoxRoot;
    private static Stage stage;

    private int variableCount;
    private static ArrayList<Button> varButtons = new ArrayList<>();
    private static ArrayList<String> varButtonNames = new ArrayList<>();
    private static ArrayList<TextField> valueInputs = new ArrayList<>();

    private static Stage primaryStage;

    @FXML public void initialize(){
        if(!varButtons.isEmpty()){
            addVarToScrollPane();
        }
        varViewer.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        varViewer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    @FXML public void terminate(){
        eternityController.setEqManagerActive(false);
        stage.hide();
    }

    /**
     * Launches the stage to create new variables
     * @param mainController allows for interaction between main controller and secondary controller
     */
    @FXML public void init(EternityController mainController, Stage primaryStage) {
        eternityController = mainController;
        EquationManagerController.primaryStage = primaryStage;
        basicInit(mainController);
    }

    @FXML public void init(EternityController mainController, Set<String> varNames, Stage primaryStage) {
        eternityController = mainController;
        addVarToList(varNames);
        EquationManagerController.primaryStage = primaryStage;
        basicInit(mainController);
    }

    private void basicInit(EternityController mainController) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(this);
            root = loader.load(getClass().getClassLoader().getResource("Eternity/GUI/EquationManagement/EquationManager/Eternity_Equation_Manager.fxml"));
            stage = new Stage();
            stage.setTitle("Eternity Equation Manager");
            stage.setScene(new Scene(root, 370, 560));
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    mainController.setEqManagerActive(false);
                }
            });
            stage.setWidth(390);
            stage.setHeight(560);
            stage.setResizable(false);
            stage.setX(primaryStage.getX() + primaryStage.getWidth());
            stage.setY(primaryStage.getY() + primaryStage.getHeight()/2 - stage.getHeight()/2);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all the variable buttons used in the equation
     * @return the list of variable buttons
     */
    public ArrayList<Button> getVarButtons() {
        return varButtons;
    }

    /**
     * Creates a new variable the user can input into their equation
     */
    @FXML
    protected void addVarToList(){
        if(!newVarText.getText().isEmpty()) {
            String temp = newVarText.getText();
            if(Character.isDigit(temp.charAt(0))){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Numeric Value at Start");
                alert.setHeaderText("A variable name cannot start with a number or symbol");
                alert.setContentText("Please enter a valid variable name.");
                alert.showAndWait();
                newVarText.clear();
            }
            else {
                if (!varButtonNames.contains(newVarText.getText())) {
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
    }

    @FXML
    private void addVarToList(Set<String> variablesToAdd){
        for(String varName : variablesToAdd) {
            if (!varButtonNames.contains(varName)) {
                Button buttonVar = new Button(varName.replace("_", ""));
                varButtonNames.add(varName.replace("_", ""));

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
            }
        }
    }

    /**
     * Adds a newly created variable to the scroll pane so the user can select it
     */
    @FXML
    private void addVarToScrollPane(){
        VBox scrollContents = new VBox();
        scrollContents.getStyleClass().add("newVar");
        //scrollContents.getStyleClass().add("mainWrapper");
        scrollContents.setSpacing(10);
        int i = 0;
        for(Button btn : varButtons){
            HBox variableCombo = new HBox();
            variableCombo.getStyleClass().add("newVar");
            variableCombo.setSpacing(10);
            variableCombo.getChildren().add(btn);
            variableCombo.getChildren().add(valueInputs.get(i));
            i++;
            scrollContents.getChildren().add(variableCombo);
        }
        varViewer.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        varViewer.setContent(scrollContents);
    }

    /**
     * Returns the values input by the user for the created variables
     * @return the list of values for each variable
     */
    @FXML
    public ArrayList<Double> getAllVariableValues(){
        ArrayList<Double> values = new ArrayList<>();
        for(TextField tf : valueInputs){
            values.add(Double.parseDouble(tf.getText()));
        }
        return values;
    }

    /**
     * clears the equation variables;
     */
    public void clearEquationVariables(){
        varButtons.clear();
        valueInputs.clear();
        varButtonNames.clear();
    }

    /**
     * Saves an equation for reuse
     * @param equationToSave the equation to save
     */
    public void saveEquation(EternityEquation equationToSave){
        //Get the equation name
        String equationName = equationToSave.getEquationName();
        //Get the display equation
        String displayEquation = equationToSave.getDisplayEquation();
        //Get the actual equation
        String equation = equationToSave.getEquation();
        //Get the variable names
        Set<String> variableNames = equationToSave.getVariable();
        //Convert variable names to string
        String variableNameString = "";
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

        List<String> lines = Collections.singletonList(
                equationName +
                "%%" +
                equation +
                "%%" +
                displayEquation+
                "%%" +
                variableNameString);
        try{
            Path file = Paths.get("./Equations/equations.txt");
            Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        }catch (IOException err){
            System.out.println("Could not load equations...");
        }
    }

    @FXML
    protected void keyHandler(KeyEvent event) {
        switch (event.getCode()) {
            case ENTER:
                this.addVarToList();
                break;
        }
    }
}
