package Eternity.Logic;

public class EternityModel {

    private static double result;
    private boolean radianSetting;

    /**
     * Constructor
     */
    public EternityModel() {
        result = 0;
        radianSetting = true;
        //searchPosition = 0;
        //history = new LinkedList<>();
    }

    /**
     * Sets the value of the radian/degree setting
     * True is for radian
     * False is for degree
     * @param radianSetting the value of the radian setting
     */
    public void setRadianSetting(boolean radianSetting) {
        this.radianSetting = radianSetting;
    }

    /**
     * Returns the value of the radian/degree setting
     * @return
     */
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

    //The history feature of the calculator was removed from the final version due to it causes issues
    //with the ability to load functions. Considering the most highly requested feature was the ability
    //to load and store features, we saw it fit that this should be our focus for the final deliverable.
    //private static final int HISTORY_CAPACITY = 10;
    //private static int searchPosition;
    //private static LinkedList<EternityEquation> history;
    //    /**
    //     * This function allows eternity to keep track of the history of expressions
    //     * entered by the user during a session. The number of expressions that are
    //     * kept is limited by HISTORY_CAPACITY.
    //     * @param newEntry the new expression to add to the list
    //     */
    //    public void pushBackHistory(EternityEquation newEntry){
    //        System.out.println(newEntry.getDisplayEquation());
    //        if(!history.contains(newEntry)){
    //            if(history.size() < HISTORY_CAPACITY){
    //                history.add(newEntry);
    //                searchPosition++;
    //            }
    //            else{
    //                history.removeFirst();
    //                history.add(newEntry);
    //                searchPosition = history.size()-1;
    //            }
    //        }
    //        for(EternityEquation eq : history){
    //            System.out.println(eq.getEquation());
    //        }
    //    }
    //
    //    /**
    //     * To enable a better experience the ability to maintain an expression history
    //     * was added to Eternity. This function allows the user to move backwards in
    //     * expressions they have entered during a session.
    //     * @return the previous expression in the list of the session
    //     */
    //    public EternityEquation previousHistory(){
    //        try {
    //            searchPosition--;
    //            return history.get(searchPosition);
    //        }
    //        catch (IndexOutOfBoundsException e){
    //            searchPosition = 0;
    //            return history.getFirst();
    //        }
    //    }
    //
    //    /**
    //     * To enable a better experience, the ability to maintain an expression history
    //     * was added to Eternity. This function allows the user to move forward in
    //     * expressions they have entered during a session.
    //     * @return the next expression in the list of the session
    //     */
    //    public EternityEquation nextHistory(){
    //        try {
    //            searchPosition++;
    //            return history.get(searchPosition);
    //        }
    //        catch (IndexOutOfBoundsException e){
    //            searchPosition = history.size()-1;
    //            return history.getLast();
    //        }
    //    }
    //
    //    public void resetCurrentPosition(){
    //        searchPosition = history.size()-1;
    //    }
    //
    //    public void clearHistory(){
    //        history.clear();
    //        searchPosition = 0;
    //    }
    //
    //    public static LinkedList<EternityEquation> getHistory() {
    //        return history;
    //    }
}
