package Eternity.Logic.Equation;

import java.util.Set;

public class EternityEquation {
    private String equationName;
    private String equation;
    private String displayEquation;
    private Set<String> variable;

    /**
     * Full constructor
     * @param equationName the equations name
     * @param equation the equation string
     * @param displayEquation the display string for the equation
     * @param variable the variables in the equation
     */
    public EternityEquation(String equationName, String equation, String displayEquation, Set<String> variable) {
        this.equationName = equationName;
        this.equation = equation;
        this.variable = variable;
        this.displayEquation = displayEquation;
    }

    /**
     * Partial constructor
     * @param equation the equation string
     * @param displayEquation the display string for the equation
     * @param variable the variables in the equation
     */
    public EternityEquation(String equation, String displayEquation, Set<String> variable) {
        this.equationName = "";
        this.equation = equation;
        this.variable = variable;
        this.displayEquation = displayEquation;
    }

    /**
     * Default constructor
     */
    public EternityEquation() {
        this.equationName = "";
        this.equation = "";
        this.displayEquation="";
        this.variable = null;
    }

    /**
     * Getter for the equation name
     * @return the equation name
     */
    public String getEquationName() {
        return equationName;
    }

    /**
     * Getter for the equation string
     * @return the equation string
     */
    public String getEquation() {
        return equation;
    }

    /**
     * Getter for the variables in the equation
     * @return the set of variables in the equation
     */
    public Set<String> getVariable() {
        return variable;
    }

    /**
     * Getter for the display equation
     * @return the dispaly equation (clean variable names)
     */
    public String getDisplayEquation() {
        return displayEquation;
    }

    /**
     * Setter for the equation name
     * @param equationName the equation name
     */
    public void setEquationName(String equationName) {
        this.equationName = equationName;
    }

    /**
     * Setter for the equation string
     * @param equation the equation string
     */
    public void setEquation(String equation) {
        this.equation = equation;
    }

    /**
     * Setter for the variables
     * @param variable the set of variables in the equation
     */
    public void setVariable(Set<String> variable) {
        this.variable = variable;
    }

    /**
     * Setter for the display equation
     * @param displayEquation the display equation
     */
    public void setDisplayEquation(String displayEquation) {
        this.displayEquation = displayEquation;
    }

    /**
     * Adds a sing variable to set of variables
     * @param varName the name of the variable to be added
     */
    public void addVariable(String varName){this.variable.add(varName);}

    /**
     * Used to compare to equations based on their strings
     * @param test the object to compare
     * @return true if the same, false if not
     */
    @Override
    public boolean equals(Object test) {
        boolean retVal = false;

        if (test instanceof EternityEquation){
            EternityEquation testAgainst = (EternityEquation) test;
            retVal = testAgainst.getEquation().equals(this.getEquation());
        }

        return retVal;
    }

    @Override
    public int hashCode(){
        int hash = 1;
        hash += Integer.parseInt(this.getEquation());
        hash *= 31;
        return hash;
    }

    /**
     * Resets the equation to a blank state
     */
    public void equationReset(){
        setEquationName("");
        setEquation("");
        setDisplayEquation("");
        if(variable!=null)
            variable.clear();
    }
}
