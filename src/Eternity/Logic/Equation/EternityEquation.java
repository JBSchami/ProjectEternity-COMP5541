package Eternity.Logic.Equation;

import java.util.Set;

public class EternityEquation {
    String equation;
    String displayEquation;
    Set<String> variable;

    public EternityEquation(String equation, String displayEquation, Set<String> variable) {
        this.equation = equation;
        this.variable = variable;
        this.displayEquation = displayEquation;
    }

    public EternityEquation() {
        this.equation = "";
        this.variable = null;
    }

    public String getEquation() {
        return equation;
    }

    public Set<String> getVariable() {
        return variable;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public void setVariable(Set<String> variable) {
        this.variable = variable;
    }

    public String getDisplayEquation() {
        return displayEquation;
    }

    public void setDisplayEquation(String displayEquation) {
        this.displayEquation = displayEquation;
    }

    public void addVariable(String varName){this.variable.add(varName);}

    @Override
    public boolean equals(Object test) {
        boolean retVal = false;

        if (test instanceof EternityEquation){
            EternityEquation ptr = (EternityEquation) test;
            retVal = ptr.getEquation().equals(this.getEquation());
        }

        return retVal;
    }
}
