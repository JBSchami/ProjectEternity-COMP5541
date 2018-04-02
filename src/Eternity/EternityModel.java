package Eternity;

import java.util.ArrayList;

public class EternityModel {
    static int nextPosition;
    static int currentPosition;

    static final int HISTORY_CAPACITY = 10;

    static ArrayList<EternityEquation> history;

    static double result;
    boolean radianSetting = true;

    /**
     * Constructor
     */
    public EternityModel() {
        nextPosition = 0;
        currentPosition = 0;
        result = 0;
        history = new ArrayList<>();
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
        System.out.println(history.contains(newEntry));
        if (!history.contains(newEntry)){

            if(nextPosition + 1 > HISTORY_CAPACITY){
                history.remove(0);
                history.add(newEntry);
                nextPosition = 0;
                System.out.println("Added: " + newEntry.getEquation());
            }
            else{
                System.out.println("Added: " + newEntry.getEquation());
                history.add(newEntry);
            }
            nextPosition++;
            currentPosition = nextPosition;
            System.out.println(nextPosition);
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
            if (currentPosition - 1 < 0) {

                return history.get(currentPosition);
            } else {

                return history.get(--currentPosition);
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
            if (currentPosition + 1 >= HISTORY_CAPACITY || currentPosition + 1 >= history.size()) {
                return history.get(currentPosition);
            } else {
                return history.get(++currentPosition);
            }
        }
        catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public void resetCurrentPosition(){
        currentPosition = nextPosition;
    }

    public void clearHistory(){
        history.clear();
        currentPosition = 0;
        nextPosition = 0;
    }
}
