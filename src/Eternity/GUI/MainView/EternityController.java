package Eternity.GUI.MainView;

import Eternity.Logic.Equation.EternityEquation;
import Eternity.Logic.EternityModel;
import Eternity.Logic.SemanticsParser;
import Eternity.Logic.Equation.EternityVariable;
import Eternity.GUI.EquationManagement.EquationLoaderController;
import Eternity.GUI.EquationManagement.EquationManagerController;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class EternityController {

    @FXML private EquationManagerController equationManagerController = new EquationManagerController();
    @FXML private EquationLoaderController equationLoaderController = new EquationLoaderController();

    @FXML protected TextField equationField;
    @FXML protected AnchorPane navList;
    @FXML protected StackPane mainContent;
    @FXML protected Button angleMode;
    @FXML protected Button precisionSettingButton;

    private final static SemanticsParser parser = new SemanticsParser(0.000000001, false);
    private static ArrayList<Function> customFunctions = new ArrayList<>();
    private static EternityModel eternityModel = new EternityModel();
    private static EternityEquation eternityEquation = new EternityEquation();

    private static Set<EternityVariable> eternityVariables = new HashSet<>();
    private static Set<String> variableNames = new HashSet<>();

    private static double result;

    /**
     * Initializes the calculator at startup, notably:
     * - Loads custom functions and operators into the semantics parser
     * - sets the default angle setting to radians
     */
    @FXML public void initialize() {
        containsVariables = false;
        eqLoaderActive = false;
        eqManagerActive = false;
        parser.setEngineAngle(eternityModel.isRadianSetting());
        customFunctions.add(parser.eBaseTenExp);
        customFunctions.add(parser.eCos);
        customFunctions.add(parser.eEulerExp);
        customFunctions.add(parser.eLog);
        customFunctions.add(parser.eNaturalLog);
    }

    public void shutdown(){
        System.out.println("Terminating calculator");
    }

    @FXML
    protected void BtnZeroPress(){
        equationField.setText(equationField.getText().concat("0"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("0"));
    }
    @FXML
    protected void BtnOnePress(){
        equationField.setText(equationField.getText().concat("1"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("1"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("1"));
    }
    @FXML
    protected void BtnTwoPress(){
        equationField.setText(equationField.getText().concat("2"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("2"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("2"));
    }
    @FXML
    protected void BtnThreePress(){
        equationField.setText(equationField.getText().concat("3"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("3"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("3"));
    }
    @FXML
    protected void BtnFourPress(){
        equationField.setText(equationField.getText().concat("4"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("4"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("4"));
    }
    @FXML
    protected void BtnFivePress(){
        equationField.setText(equationField.getText().concat("5"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("5"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("5"));
    }
    @FXML
    protected void BtnSixPress(){
        equationField.setText(equationField.getText().concat("6"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("6"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("6"));
    }
    @FXML
    protected void BtnSevenPress(){
        equationField.setText(equationField.getText().concat("7"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("7"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("7"));
    }
    @FXML
    protected void BtnEightPress(){
        equationField.setText(equationField.getText().concat("8"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("8"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("8"));
    }
    @FXML
    protected void BtnNinePress(){
        equationField.setText(equationField.getText().concat("9"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("9"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("9"));
    }
    @FXML
    protected void BtnPlusPress(){
        equationField.setText(equationField.getText().concat("+"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("+"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("+"));
    }
    @FXML
    protected void BtnMinusPress(){
        equationField.setText(equationField.getText().concat("-"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("-"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("-"));
    }
    @FXML
    protected void BtnMultPress(){
        equationField.setText(equationField.getText().concat("*"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("*"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("*"));
    }
    @FXML
    protected void BtnDivPress(){
        equationField.setText(equationField.getText().concat("/"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("/"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("/"));
    }
    @FXML
    protected void BtnCosPress(){
        equationField.setText(equationField.getText().concat("cos"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("cos"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("cos"));
    }
    @FXML
    protected void BtnXPowYPress(){
        equationField.setText(equationField.getText().concat("^"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("^"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("^"));
    }
    @FXML
    protected void BtnLogTenPress(){
        equationField.setText(equationField.getText().concat("log"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("log"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("log"));
    }
    @FXML
    protected void BtnEulerExpPress(){
        equationField.setText(equationField.getText().concat("e^"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("e^"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("e^"));
    }
    @FXML
    protected void BtnNaturalLogPress(){
        equationField.setText(equationField.getText().concat("ln"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("ln"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("ln"));
    }
    @FXML
    protected void BtnBaseTenExpPress(){
        equationField.setText(equationField.getText().concat("10^"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("10^"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("10^"));
    }
    @FXML
    protected void BtnFactorialPress(){
        equationField.setText(equationField.getText().concat("!"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("!"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("!"));
    }
    @FXML
    protected void BtnPiPress(){
        equationField.setText(equationField.getText().concat("pi"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("pi"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("pi"));
    }
    @FXML
    protected void BtnBracketOpenPress(){
        equationField.setText(equationField.getText().concat("("));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("("));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("("));

    }
    @FXML
    protected void BtnBracketClosePress(){
        equationField.setText(equationField.getText().concat(")"));
        eternityEquation.setEquation(eternityEquation.getEquation().concat(")"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat(")"));
    }
    @FXML
    protected void BtnDotPress(){
        equationField.setText(equationField.getText().concat("."));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("."));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat("."));
    }
    @FXML
    protected void BtnClearPress(){
        result = 0;
        eternityEquation.setDisplayEquation("");
        eternityEquation.setEquation("");
        equationField.clear();
        containsVariables = false;
        eternityModel.resetCurrentPosition();
    }
    @FXML
    protected void BtnClearAllPress(){
        result = 0;
        equationField.clear();
        eternityEquation.setDisplayEquation("");
        eternityEquation.setEquation("");
        containsVariables = false;
        eternityModel.clearHistory();
        equationManagerController.clearEquationVariables();
    }
    @FXML
    protected void BtnNextHistoryPress(){
        equationField.clear();
        try{
            eternityEquation = eternityModel.nextHistory();
            equationField.setText(eternityEquation.getDisplayEquation());
            variableNames = eternityEquation.getVariable();
            if(!variableNames.isEmpty()){
                containsVariables = true;
            }
        } catch (NoSuchElementException e){
            //Do nothing
        }

    }
    @FXML
    protected void BtnPreviousHistoryPress(){
        equationField.clear();
        try{
            eternityEquation = eternityModel.previousHistory();
            equationField.setText(eternityEquation.getDisplayEquation());
            variableNames = eternityEquation.getVariable();
            if(!variableNames.isEmpty()){
                containsVariables = true;
            }
        }catch(NoSuchElementException e){
            //Do nothing
        }
    }
    @FXML
    protected void BtnEqualPress(){
        eternityEquation.setVariable(variableNames);
        EternityEquation pushBackEquation = new EternityEquation(eternityEquation.getEquation(),eternityEquation.getDisplayEquation(),eternityEquation.getVariable());
        eternityModel.pushBackHistory(pushBackEquation);
        if(containsVariables){
            evaluateExpressionWithVariables();
        }
        else {
            evaluateExpressionWithoutVariables();
        }
        eternityEquation.setEquation(equationField.getText());
        eternityEquation.setDisplayEquation(equationField.getText());
    }

    /**
     * Evaluates expressions that contain variables
     */
    private void evaluateExpressionWithVariables(){
        try{
            String input = parser.preFormatInput(eternityEquation.getEquation());
            Expression expression = new ExpressionBuilder(input)
                    .functions(customFunctions)
                    .operator(parser.eFactorial, parser.eExpY)
                    .variables(variableNames)
                    .build();

            ArrayList<Double> values = equationManagerController.getAllVariableValues();
            updateVariableValues(equationManagerController.getVarButtons(), values);
            for(EternityVariable var: eternityVariables){
                expression.setVariable(var.getVarName(), var.getVarValue());
            }
            result = expression.evaluate();
            updateWithResult();
        } catch (java.lang.IllegalArgumentException error) {
            System.out.println(error.getMessage());
            equationField.setText(error.getMessage());
        }
    }

    /**
     * Evaluates expressions with no variables.
     */
    private void evaluateExpressionWithoutVariables(){
        try {
            String input = parser.preFormatInput(eternityEquation.getEquation());
            result = new ExpressionBuilder(input)
                    .functions(customFunctions)
                    .operator(parser.eFactorial, parser.eExpY)
                    .build()
                    .evaluate();
            updateWithResult();
        } catch (java.lang.IllegalArgumentException error) {
            System.out.println(error.getMessage());
            equationField.setText(error.getMessage());
        }
    }

    private void updateWithResult(){
        eternityModel.setResult(result);
        int precision = parser.getEnginePrecision();
        DecimalFormat df = new DecimalFormat(getPrecisionFormat(precision));
        df.setRoundingMode(RoundingMode.HALF_UP);
        equationField.setText((df.format(eternityModel.getResult())));
    }

    /**
     * Key handler is used to allow for keyboard input
     * @param event the keyboard event that triggers the call of the function
     */
    @FXML
    protected void keyHandler(KeyEvent event) {
        KeyCodeCombination shiftPlus = new KeyCodeCombination(KeyCode.EQUALS, KeyCodeCombination.SHIFT_DOWN);
        KeyCodeCombination shiftOpenBracket = new KeyCodeCombination(KeyCode.DIGIT9, KeyCodeCombination.SHIFT_DOWN);
        KeyCodeCombination shiftCloseBracket = new KeyCodeCombination(KeyCode.DIGIT0, KeyCodeCombination.SHIFT_DOWN);
        KeyCodeCombination shiftFactorial = new KeyCodeCombination(KeyCode.DIGIT1, KeyCodeCombination.SHIFT_DOWN);
        KeyCodeCombination shiftStar = new KeyCodeCombination(KeyCode.DIGIT8, KeyCodeCombination.SHIFT_DOWN);

        if (shiftPlus.match(event)) {
            BtnPlusPress();
        }
        else if(shiftCloseBracket.match(event)){
            BtnBracketClosePress();
        }
        else if(shiftOpenBracket.match(event)){
            BtnBracketOpenPress();
        }
        else if(shiftFactorial.match(event)){
            BtnFactorialPress();
        }
        else if(shiftStar.match(event)){
            BtnMultPress();
        }
        else {
            switch (event.getCode()) {
                case DIGIT0:
                    this.BtnZeroPress();
                    break;
                case NUMPAD0:
                    this.BtnZeroPress();
                    break;

                case DIGIT1:
                    BtnOnePress();
                    break;

                case NUMPAD1:
                    BtnOnePress();
                    break;

                case DIGIT2:
                    BtnTwoPress();
                    break;

                case NUMPAD2:
                    BtnTwoPress();
                    break;

                case DIGIT3:
                    BtnThreePress();
                    break;

                case NUMPAD3:
                    BtnThreePress();
                    break;

                case DIGIT4:
                    BtnFourPress();
                    break;

                case NUMPAD4:
                    BtnFourPress();
                    break;

                case DIGIT5:
                    BtnFivePress();
                    break;

                case NUMPAD5:
                    BtnFivePress();
                    break;

                case DIGIT6:
                    BtnSixPress();
                    break;

                case NUMPAD6:
                    BtnSixPress();
                    break;

                case DIGIT7:
                    BtnSevenPress();
                    break;

                case NUMPAD7:
                    BtnSevenPress();
                    break;

                case DIGIT8:
                    BtnEightPress();
                    break;

                case NUMPAD8:
                    BtnEightPress();
                    break;

                case DIGIT9:
                    BtnNinePress();
                    break;

                case NUMPAD9:
                    BtnNinePress();
                    break;

                case MINUS:
                    BtnMinusPress();
                    break;

                case SLASH:
                    BtnDivPress();
                    break;

                case PERIOD:
                    BtnDotPress();
                    break;

                case ENTER:
                    BtnEqualPress();
                    break;

                case EQUALS:
                    BtnEqualPress();
                    break;

                case BACK_SPACE:
                    BtnClearPress();
                    break;

                case ADD:
                    BtnPlusPress();
                    break;

                case SUBTRACT:
                    BtnMinusPress();
                    break;

                case DIVIDE:
                    BtnDivPress();
                    break;

                case MULTIPLY:
                    BtnMultPress();
                    break;

                case LEFT:
                    BtnPreviousHistoryPress();
                    break;

                case RIGHT:
                    BtnNextHistoryPress();
                    break;

                default:
                    if (eqManagerActive){
                        //Do nothing
                    }
                    break;
            }
        }
    }

    /**
     * Allows for the sliding navigation menu
     */
    @FXML
    protected void navMenuSlide(){
        TranslateTransition openNav=new TranslateTransition(new Duration(350), navList);
        openNav.setToX(0);
        TranslateTransition closeNav=new TranslateTransition(new Duration(350), navList);
        ObservableList<Node> childs = mainContent.getChildren();
        if(navList.getTranslateX()!=0){
            openNav.play();
            Node topNode = childs.get(1);
            topNode.toBack();
        }else{
            closeNav.setToX(-(navList.getWidth()));
            closeNav.play();
            Node topNode = childs.get(1);
            topNode.toBack();
        }
    }

    /**
     * Flip-flops between radians and degrees when pressed
     */
    @FXML
    protected void changeAngleMode(){
        if(angleMode.getText().equals("D")){
            eternityModel.setRadianSetting(true);
            parser.setEngineAngle(eternityModel.isRadianSetting());
            angleMode.setText("R");
        }
        else{
            eternityModel.setRadianSetting(false);
            parser.setEngineAngle(eternityModel.isRadianSetting());
            angleMode.setText("D");
        }
    }

    /**
     * Lets the user change the decimal point precision when pressed
     */
    @FXML
    protected void changePrecisionSetting(){
        int previousSetting = parser.getEnginePrecision();
        if (previousSetting == 9) {
            previousSetting = 1;
        }
        else {
            previousSetting += 1;
        }
        switch (previousSetting-1){
            case 0:
                precisionSettingButton.setText("d.1");
                parser.setEnginePrecision(0.1);
                break;
            case 1:
                precisionSettingButton.setText("d.2");
                parser.setEnginePrecision(0.01);
                break;
            case 2:
                precisionSettingButton.setText("d.3");
                parser.setEnginePrecision(0.001);
                break;
            case 3:
                precisionSettingButton.setText("d.4");
                parser.setEnginePrecision(0.0001);
                break;
            case 4:
                precisionSettingButton.setText("d.5");
                parser.setEnginePrecision(0.00001);
                break;
            case 5:
                precisionSettingButton.setText("d.6");
                parser.setEnginePrecision(0.000001);
                break;
            case 6:
                precisionSettingButton.setText("d.7");
                parser.setEnginePrecision(0.0000001);
                break;
            case 7:
                precisionSettingButton.setText("d.8");
                parser.setEnginePrecision(0.00000001);
                break;
            case 8:
                precisionSettingButton.setText("d.9");
                parser.setEnginePrecision(0.000000001);
                break;
        }

    }

    private String getPrecisionFormat(int value){
        String format;
        switch (value){
            case 0:
                format = "#";
                break;
            case 1:
                format = "#.#";
                break;
            case 2:
                format = "#.##";
                break;
            case 3:
                format = "#.###";
                break;
            case 4:
                format = "#.####";
                break;
            case 5:
                format = "#.#####";
                break;
            case 6:
                format = "#.######";
                break;
            case 7:
                format = "#.#######";
                break;
            case 8:
                format = "#.########";
                break;
            case 9:
                format = "#.#########";
                break;
            default:
                format = "#.#########";
                break;
        }
        return format;
    }

    /*
     * EQUATION MANAGER RELATED
     */
    private boolean eqManagerActive = false;

    @FXML
    protected void BtnNewEquation(){
        BtnClearPress();
        eternityEquation = new EternityEquation();
        equationManagerController.clearEquationVariables();
        launchEquationManager();
    }

    @FXML
    protected void BtnSaveEquation(){
        eternityEquation.setVariable(variableNames);
        equationManagerController.saveEquation(eternityEquation);
        navMenuSlide();
    }

    @FXML
    protected void launchEquationManager(){
        if (!eqManagerActive) {
            equationManagerController.init(this);
            eqManagerActive = true;
        }
        navMenuSlide();
    }

    @FXML
    protected void launchEquationManager(Set<String> varNames){
        if (!eqManagerActive) {
            equationManagerController.init(this, varNames);
            eqManagerActive = true;
        }
    }

    private boolean eqLoaderActive = false;
    @FXML
    protected void launchEquationLoader(){
        if (!eqLoaderActive) {
            equationLoaderController.init(this);
            eqLoaderActive = true;
        }
        navMenuSlide();
    }

    private static boolean containsVariables = false;

    @FXML
    public void addVariableToEquation(String varName){
        equationField.setText(equationField.getText().concat(varName));
        eternityEquation.setEquation(eternityEquation.getEquation().concat("_"+varName+"_"));
        eternityVariables.add(new EternityVariable("_"+varName+"_"));
        eternityEquation.setDisplayEquation(eternityEquation.getDisplayEquation().concat(varName));
        for(EternityVariable et:eternityVariables){
            variableNames.add(et.getVarName());
        }
        containsVariables = true;
    }

    public void updateVariableValues(ArrayList<Button> varNames, ArrayList<Double> varValues){
        eternityVariables.clear();
        for(int i = 0; i < varNames.size(); i++){
            eternityVariables.add(new EternityVariable("_"+varNames.get(i).getText()+"_", varValues.get(i)));
        }
    }

    public void setEqLoaderActive(boolean eqLoaderActive) {
        this.eqLoaderActive = eqLoaderActive;
    }

    public void setEqManagerActive(boolean eqManagerActive){
        this.eqManagerActive = eqLoaderActive;
    }

    @FXML
    public void loadEquation(EternityEquation loadedEquation){
        eternityEquation = loadedEquation;
        equationField.setText(eternityEquation.getDisplayEquation());
        equationManagerController.clearEquationVariables();
        launchEquationManager(eternityEquation.getVariable());
    }
}