package GUI;

import Eternity.semanticsParser;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class Controller {

    private final static semanticsParser parser = new semanticsParser(0.000000001);
    private static ArrayList<Function> customFunctions = new ArrayList<>();

    private static double result;

    public void initialize(Stage primaryStage){
        installEventHandler(primaryStage);
    }

    @FXML
    protected TextField equationField;

    @FXML
    protected void BtnZeroPress(ActionEvent event){
        System.out.println("0");
        equationField.setText(equationField.getText().concat("0"));
    }
    @FXML
    protected void BtnOnePress(ActionEvent event){
        System.out.println("1");
        equationField.setText(equationField.getText().concat("1"));
    }
    @FXML
    protected void BtnTwoPress(ActionEvent event){
        System.out.println("2");
        equationField.setText(equationField.getText().concat("2"));
    }
    @FXML
    protected void BtnThreePress(ActionEvent event){
        System.out.println("3");
        equationField.setText(equationField.getText().concat("3"));
    }
    @FXML
    protected void BtnFourPress(ActionEvent event){
        System.out.println("4");
        equationField.setText(equationField.getText().concat("4"));
    }
    @FXML
    protected void BtnFivePress(ActionEvent event){
        System.out.println("5");
        equationField.setText(equationField.getText().concat("5"));
    }
    @FXML
    protected void BtnSixPress(ActionEvent event){
        System.out.println("6");
        equationField.setText(equationField.getText().concat("6"));
    }
    @FXML
    protected void BtnSevenPress(ActionEvent event){
        System.out.println("7");
        equationField.setText(equationField.getText().concat("7"));
    }
    @FXML
    protected void BtnEightPress(ActionEvent event){
        System.out.println("8");
        equationField.setText(equationField.getText().concat("8"));
    }
    @FXML
    protected void BtnNinePress(ActionEvent event){
        System.out.println("9");
        equationField.setText(equationField.getText().concat("9"));
    }

    @FXML
    protected void BtnPlusPress(ActionEvent event){
        System.out.println("+");
        equationField.setText(equationField.getText().concat("+"));
    }

    @FXML
    protected void BtnMinusPress(ActionEvent event){
        System.out.println("-");
        equationField.setText(equationField.getText().concat("-"));
    }

    @FXML
    protected void BtnMultPress(ActionEvent event){
        System.out.println("*");
        equationField.setText(equationField.getText().concat("*"));
    }

    @FXML
    protected void BtnDivPress(ActionEvent event){
        System.out.println("/");
        equationField.setText(equationField.getText().concat("/"));
    }

    @FXML
    protected void BtnCosPress(ActionEvent event){
        System.out.println("Cos");
        equationField.setText(equationField.getText().concat("Cos("));
    }

    @FXML
    protected void BtnXPowYPress(ActionEvent event){
        System.out.println("x^y");
        equationField.setText(equationField.getText().concat("^"));
    }

    @FXML
    protected void BtnLogTenPress(ActionEvent event){
        System.out.println("Log_10");
        equationField.setText(equationField.getText().concat("Log("));
    }

    @FXML
    protected void BtnEulerExpPress(ActionEvent event){
        System.out.println("e^x");
        equationField.setText(equationField.getText().concat("Exp("));
    }

    @FXML
    protected void BtnFactorialPress(ActionEvent event){
        System.out.println("n!");
        equationField.setText(equationField.getText().concat("!"));
    }

    @FXML
    protected void BtnEqualPress(ActionEvent event){
        System.out.println("=");
        customFunctions.add(parser.eBaseTenExp);
        customFunctions.add(parser.eCos);
        customFunctions.add(parser.eEulerExp);
        customFunctions.add(parser.eLog);
        customFunctions.add(parser.eNaturalLog);
        try {
            result = new ExpressionBuilder(equationField.getText())
                    .functions(customFunctions)
                    .operator(parser.eFactorial)
                    .build()
                    .evaluate();
        } catch (java.lang.IllegalArgumentException error){
            System.out.println(error.getMessage());
        }
        equationField.setText(Double.toString(result));
    }

    @FXML
    protected void BtnBracketOpenPress(ActionEvent event){
        System.out.println("(");
        equationField.setText(equationField.getText().concat("("));
    }

    @FXML
    protected void BtnBracketClosePress(ActionEvent event){
        System.out.println(")");
        equationField.setText(equationField.getText().concat(")"));
    }

    @FXML
    protected void BtnDotPress(ActionEvent event){
        System.out.println(".");
        equationField.setText(equationField.getText().concat("."));
    }

    @FXML
    protected void BtnClearPress(ActionEvent event){
        System.out.println("Clear");
        result = 0;
        equationField.setText("");
    }

    private void installEventHandler(Stage primaryStage) {
        // handler for enter key press / release events, other keys are
        // handled by the parent (keyboard) node handler
        final EventHandler<KeyEvent> keyEventHandler =
                new EventHandler<KeyEvent>() {
                    public void handle(final KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.ENTER) {
                            System.out.println("Enter Pressed");
                            keyEvent.consume();
                        }
                    }
                };
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
