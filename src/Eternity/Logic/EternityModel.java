package Eternity.Logic;

import Eternity.Logic.Equation.EternityEquation;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class EternityModel {
    private static int searchPosition;

    private static final int HISTORY_CAPACITY = 10;

    private static LinkedList<EternityEquation> history;

    private static double result;
    private boolean radianSetting = true;

    /**
     * Constructor
     */
    public EternityModel() {
        searchPosition = 0;
        result = 0;
        history = new LinkedList<>();
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
        if(!history.contains(newEntry)){
            System.out.println("Adding: " + newEntry.getEquation());
            if(history.size() < HISTORY_CAPACITY){
                history.add(newEntry);
                System.out.println("Within limits: " + history.size());
                searchPosition++;
            }
            else{
                System.out.println("Out of limits: " + history.size());
                history.removeFirst();
                history.add(newEntry);
                searchPosition = history.size()-1;
            }
        }
        for(EternityEquation eq : history){
            System.out.println(eq.getEquation());
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
            searchPosition--;
            return history.get(searchPosition);
        }
        catch (IndexOutOfBoundsException e){
            searchPosition = 0;
            return history.getFirst();
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
            searchPosition++;
            return history.get(searchPosition);
        }
        catch (IndexOutOfBoundsException e){
            searchPosition = history.size()-1;
            return history.getLast();
        }
    }

    public void resetCurrentPosition(){
        searchPosition = history.size()-1;
    }

    public void clearHistory(){
        history.clear();
        searchPosition = 0;
    }

    public static LinkedList<EternityEquation> getHistory() {
        return history;
    }
}
