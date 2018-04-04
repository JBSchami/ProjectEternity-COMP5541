package Eternity.Logic;

import Eternity.Logic.Equation.EternityEquation;

import java.util.ArrayList;

public class EternityModel {
    private static int searchPosition;
    private static int currentPosition;
    private static boolean lapAround;

    private static final int HISTORY_CAPACITY = 10;

    private static EternityEquation[] history;

    private static double result;
    private boolean radianSetting = true;

    /**
     * Constructor
     */
    public EternityModel() {
        searchPosition = 0;
        currentPosition = 0;
        result = 0;
        lapAround = false;
        history = new EternityEquation[HISTORY_CAPACITY];
    }

    public void setRadianSetting(boolean radianSetting) {
        this.radianSetting = radianSetting;
    }

    public boolean isRadianSetting() {
        return radianSetting;
    }

    /**
     * sets the value for the last calculated result
     * @param result the result to be stored
     */
    public void setResult(double result) {
        EternityModel.result = result;
    }

    /**
     * getter for the result stored in the calculator
     * @return the value of the result of the last calculated expression
     */
    public double getResult() {
        return result;
    }

    /**
     * This function allows eternity to keep track of the history of expressions
     * entered by the user during a session. The number of expressions that are
     * kept is limited by HISTORY_CAPACITY.
     * @param newEntry the new expression to add to the list
     */
    public void pushBackHistory(EternityEquation newEntry){
        history[currentPosition%HISTORY_CAPACITY] = newEntry;
        currentPosition++;
        if(currentPosition > HISTORY_CAPACITY){
            lapAround = true;
        }
    }

    /**
     * To enable a better experience the ability to maintain an expression history
     * was added to Eternity. This function allows the user to move backwards in
     * expressions they have entered during a session.
     * @return the previous expression in the list of the session
     */
    public EternityEquation previousHistory(){
        try {
            if (!lapAround && searchPosition-1 < 0) {
                searchPosition = 0;
                return history[searchPosition];
            } else {
                return history[searchPosition--];
            }
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    /**
     * To enable a better experience, the ability to maintain an expression history
     * was added to Eternity. This function allows the user to move forward in
     * expressions they have entered during a session.
     * @return the next expression in the list of the session
     */
    public EternityEquation nextHistory(){
        try {
            if (!lapAround && searchPosition >= currentPosition) {
                return history[currentPosition-1];
            } else {
                return history[searchPosition++];
            }
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public void resetCurrentPosition(){
        searchPosition = currentPosition%HISTORY_CAPACITY;
    }

    public void clearHistory(){
        history = new EternityEquation[HISTORY_CAPACITY];
        currentPosition = 0;
        searchPosition = currentPosition;
    }
}
