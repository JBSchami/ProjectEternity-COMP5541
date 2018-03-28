package Eternity;

public class EternityVariable {
    private String varName;
    private Double varValue;
    /**
     * Default Constructor
     */
    public EternityVariable(){
        setVarName("");
        setVarValue(0.0);
    }
    /**
     * Constructor for name only
     * @param varName variable name
     */
    public EternityVariable(String varName){
        setVarName(varName);
        setVarValue(0.0);
    }
    /**
     * Constructor for name and value
     * @param varName variable name
     * @param varValue variable value
     */
    public EternityVariable(String varName, Double varValue){
        setVarName(varName);
        setVarValue(varValue);
    }

    /**
     * Getter for name
     * @return variable name
     */
    public String getVarName() {
        return varName;
    }

    /**
     * Getter for value
     * @return variable value
     */
    public double getVarValue() {
        return varValue;
    }
    /**
     * Setter for name
     * @param varName variable name
     */
    public void setVarName(String varName) {
        this.varName = varName;
    }
    /**
     * Setter for value
     * @param varValue variable value
     */
    public void setVarValue(double varValue) {
        this.varValue = varValue;
    }
}
