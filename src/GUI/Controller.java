package GUI;

import Eternity.semanticsParser;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Controller {

    private final static semanticsParser parser = new semanticsParser(0.000000001, false);
    private static ArrayList<Function> customFunctions = new ArrayList<>();

    private static double result;
    String lastEntered;

    @FXML
    protected TextField equationField;

    @FXML
    protected void BtnZeroPress(){
        equationField.setText(equationField.getText().concat("0"));
    }
    @FXML
    protected void BtnOnePress(){
        System.out.println("1");
        equationField.setText(equationField.getText().concat("1"));
    }
    @FXML
    protected void BtnTwoPress(){
        System.out.println("2");
        equationField.setText(equationField.getText().concat("2"));
    }
    @FXML
    protected void BtnThreePress(){
        System.out.println("3");
        equationField.setText(equationField.getText().concat("3"));
    }
    @FXML
    protected void BtnFourPress(){
        System.out.println("4");
        equationField.setText(equationField.getText().concat("4"));
    }
    @FXML
    protected void BtnFivePress(){
        System.out.println("5");
        equationField.setText(equationField.getText().concat("5"));
    }
    @FXML
    protected void BtnSixPress(){
        System.out.println("6");
        equationField.setText(equationField.getText().concat("6"));
    }
    @FXML
    protected void BtnSevenPress(){
        System.out.println("7");
        equationField.setText(equationField.getText().concat("7"));
    }
    @FXML
    protected void BtnEightPress(){
        System.out.println("8");
        equationField.setText(equationField.getText().concat("8"));
    }
    @FXML
    protected void BtnNinePress(){
        System.out.println("9");
        equationField.setText(equationField.getText().concat("9"));
    }

    @FXML
    protected void BtnPlusPress(){
        System.out.println("+");
        equationField.setText(equationField.getText().concat("+"));
    }

    @FXML
    protected void BtnMinusPress(){
        System.out.println("-");
        equationField.setText(equationField.getText().concat("-"));
    }

    @FXML
    protected void BtnMultPress(){
        System.out.println("*");
        equationField.setText(equationField.getText().concat("*"));
    }

    @FXML
    protected void BtnDivPress(){
        System.out.println("/");
        equationField.setText(equationField.getText().concat("/"));
    }

    @FXML
    protected void BtnCosPress(){
        System.out.println("Cos");
        equationField.setText(equationField.getText().concat("cos("));
    }

    @FXML
    protected void BtnXPowYPress(){
        System.out.println("x^y");
        equationField.setText(equationField.getText().concat("^("));
    }

    @FXML
    protected void BtnLogTenPress(){
        System.out.println("Log_10");
        equationField.setText(equationField.getText().concat("log("));
    }

    @FXML
    protected void BtnEulerExpPress(){
        System.out.println("e^x");
        equationField.setText(equationField.getText().concat("e^("));
    }

    @FXML
    protected void BtnNaturalLogPress(){
        System.out.println("ln");
        equationField.setText(equationField.getText().concat("ln("));
    }

    @FXML
    protected void BtnBaseTenExpPress(){
        System.out.println("10^");
        equationField.setText(equationField.getText().concat("10^("));
    }

    @FXML
    protected void BtnFactorialPress(){
        System.out.println("n!");
        equationField.setText(equationField.getText().concat("!"));
    }

    @FXML
    protected void BtnEqualPress(){
        System.out.println("=");
        customFunctions.add(parser.eBaseTenExp);
        customFunctions.add(parser.eCos);
        customFunctions.add(parser.eEulerExp);
        customFunctions.add(parser.eLog);
        customFunctions.add(parser.eNaturalLog);
        try {
            String input = parser.preFormatInput(equationField.getText());
            result = new ExpressionBuilder(input)
                    .functions(customFunctions)
                    .operator(parser.eFactorial)
                    .build()
                    .evaluate();
            equationField.setText(Double.toString(result));
        } catch (java.lang.IllegalArgumentException error){
            System.out.println(error.getMessage());
            equationField.setText(error.getMessage());
        }
    }

    @FXML
    protected void BtnBracketOpenPress(){
        System.out.println("(");
        equationField.setText(equationField.getText().concat("("));
    }

    @FXML
    protected void BtnBracketClosePress(){
        System.out.println(")");
        equationField.setText(equationField.getText().concat(")"));
    }

    @FXML
    protected void BtnDotPress(){
        System.out.println(".");
        equationField.setText(equationField.getText().concat("."));
    }

    @FXML
    protected void BtnClearPress(){
        System.out.println("Clear");
        result = 0;
        equationField.setText("");
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

                default:
                    System.out.println("Invalid Key Pressed");
                    break;
            }
        }
    }

    private static String basicParse(String input){
        ArrayList<Double> values = new ArrayList<Double>(0);
        ArrayList<String> operators = new ArrayList<String>(0);

        StringTokenizer st = new StringTokenizer(input);
        while(st.hasMoreTokens()){
            String thisToken = st.nextToken();
            if (thisToken.equals("+"))
                operators.add("+");
            else if (thisToken.equals("-"))
                operators.add("-");
            else if (thisToken.equals("*"))
                operators.add("*");
            else if (thisToken.equals("/"))
                operators.add("/");
            else{
                values.add(Double.parseDouble(thisToken));
            }
        }


        for (Double s:values) {
            System.out.println(s);

        }

        for (String c:operators){
            System.out.println(c);
        }

        return basicEvaluate(values, operators);
    }

    private static String basicEvaluate(ArrayList<Double> values, ArrayList<String> operators){
        if(operators.contains("/") || operators.contains("*")){
            //find where they are and perform those calculations first
            System.out.println("PEDMAS must be respected");
            int countOfMultDiv = 0;
            for(int i = 0, size = values.size(); i < size; i++){
                if(operators.get(i).equals("*")){
                    values.set(i - countOfMultDiv, values.get(i-countOfMultDiv) * values.get(i-countOfMultDiv+1));
                    values.remove(i-countOfMultDiv+1);
                    size--;
                    countOfMultDiv++;
                }
                else if(operators.get(i).equals("/")){
                    values.set(i - countOfMultDiv, values.get(i-countOfMultDiv) / values.get(i-countOfMultDiv+1));
                    values.remove(i-countOfMultDiv+1);
                    size--;
                    countOfMultDiv++;
                }
            }
            operators.remove("*");
            operators.remove("/");
            return basicEvaluate(values, operators);
        }
        else{
            double finalValue = values.get(0);
            int j = 0;
            for(int i = 1; i < values.size(); i++){
                if(operators.get(j) == "+")
                    finalValue += values.get(i);
                else
                    finalValue -= values.get(i);
                j++;
            }
            System.out.println(finalValue);
            return Double.toString(finalValue);
        }
    }
}

/*

 */