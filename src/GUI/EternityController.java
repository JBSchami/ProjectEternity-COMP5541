package GUI;

import Eternity.EternityModel;
import Eternity.SemanticsParser;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.StringTokenizer;


public class EternityController {

    private final static SemanticsParser parser = new SemanticsParser(0.000000001, false);
    private static ArrayList<Function> customFunctions = new ArrayList<>();
    private static EternityModel eternityModel = new EternityModel();

    private static double result;
    private static int precision = parser.getEnginePrecision();

    @FXML
    protected TextField equationField;
    @FXML
    protected AnchorPane navList;
    @FXML
    protected StackPane mainContent;
    @FXML
    protected Button angleMode;
    @FXML
    protected Button precisionSettingButton;

    @FXML
    protected void BtnZeroPress(){
        equationField.setText(equationField.getText().concat("0"));
    }
    @FXML
    protected void BtnOnePress(){
        equationField.setText(equationField.getText().concat("1"));
    }
    @FXML
    protected void BtnTwoPress(){
        equationField.setText(equationField.getText().concat("2"));
    }
    @FXML
    protected void BtnThreePress(){
        equationField.setText(equationField.getText().concat("3"));
    }
    @FXML
    protected void BtnFourPress(){
        equationField.setText(equationField.getText().concat("4"));
    }
    @FXML
    protected void BtnFivePress(){
        equationField.setText(equationField.getText().concat("5"));
    }
    @FXML
    protected void BtnSixPress(){
        equationField.setText(equationField.getText().concat("6"));
    }
    @FXML
    protected void BtnSevenPress(){
        equationField.setText(equationField.getText().concat("7"));
    }
    @FXML
    protected void BtnEightPress(){
        equationField.setText(equationField.getText().concat("8"));
    }
    @FXML
    protected void BtnNinePress(){
        equationField.setText(equationField.getText().concat("9"));
    }

    @FXML
    protected void BtnPlusPress(){
        equationField.setText(equationField.getText().concat("+"));
    }

    @FXML
    protected void BtnMinusPress(){
        equationField.setText(equationField.getText().concat("-"));
    }

    @FXML
    protected void BtnMultPress(){
        equationField.setText(equationField.getText().concat("*"));
    }

    @FXML
    protected void BtnDivPress(){
        equationField.setText(equationField.getText().concat("/"));
    }

    @FXML
    protected void BtnCosPress(){
        equationField.setText(equationField.getText().concat("cos"));
    }

    @FXML
    protected void BtnXPowYPress(){
        equationField.setText(equationField.getText().concat("^"));
    }

    @FXML
    protected void BtnLogTenPress(){
        equationField.setText(equationField.getText().concat("log"));
    }

    @FXML
    protected void BtnEulerExpPress(){
        equationField.setText(equationField.getText().concat("e^"));
    }

    @FXML
    protected void BtnNaturalLogPress(){
        equationField.setText(equationField.getText().concat("ln"));
    }

    @FXML
    protected void BtnBaseTenExpPress(){
        equationField.setText(equationField.getText().concat("10^"));
    }

    @FXML
    protected void BtnFactorialPress(){
        equationField.setText(equationField.getText().concat("!"));
    }

    @FXML
    protected void BtnPiPress(){
        equationField.setText(equationField.getText().concat("pi"));
    }

    @FXML
    protected void BtnEqualPress(){
        parser.setEngineAngle(eternityModel.isRadianSetting());
        customFunctions.add(parser.eBaseTenExp);
        customFunctions.add(parser.eCos);
        customFunctions.add(parser.eEulerExp);
        customFunctions.add(parser.eLog);
        customFunctions.add(parser.eNaturalLog);
        try {
            eternityModel.pushBackHistory(equationField.getText());
            String input = parser.preFormatInput(equationField.getText());
            result = new ExpressionBuilder(input)
                    .functions(customFunctions)
                    .operator(parser.eFactorial, parser.eExpY)
                    .build()
                    .evaluate();
            eternityModel.setResult(result);
            precision = parser.getEnginePrecision();
            DecimalFormat df = new DecimalFormat(getPrecisionFormat(precision));
            df.setRoundingMode(RoundingMode.HALF_UP);
            equationField.setText((df.format(eternityModel.getResult())));
        } catch (java.lang.IllegalArgumentException error){
            System.out.println(error.getMessage());
            equationField.setText(error.getMessage());
        }
    }

    @FXML
    protected void BtnBracketOpenPress(){
        equationField.setText(equationField.getText().concat("("));
    }

    @FXML
    protected void BtnBracketClosePress(){
        equationField.setText(equationField.getText().concat(")"));
    }

    @FXML
    protected void BtnDotPress(){
        equationField.setText(equationField.getText().concat("."));
    }

    @FXML
    protected void BtnClearPress(){
        result = 0;
        equationField.setText("");
    }

    @FXML
    protected void BtnNextHistoryPress(){
        equationField.clear();
        equationField.setText(eternityModel.nextHistory());
    }

    @FXML
    protected void BtnPreviousHistoryPress(){
        equationField.clear();
        equationField.setText(eternityModel.previousHistory());
    }

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
            //Behavior to be determined
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
                    System.out.println("Invalid Key Pressed");
                    break;
            }
        }
    }

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

    @FXML
    protected void changeAngleMode(){
        if(angleMode.getText().equals("D")){
            eternityModel.setRadianSetting(true);
            angleMode.setText("R");
        }
        else{
            eternityModel.setRadianSetting(false);
            angleMode.setText("D");
        }
    }

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

}