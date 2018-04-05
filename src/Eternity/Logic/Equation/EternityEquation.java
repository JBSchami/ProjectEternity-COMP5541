package Eternity.Logic.Equation;

import java.util.HashSet;
import java.util.Set;

public class EternityEquation {
    String equationName;
    String equation;
    String displayEquation;
    Set<String> variable;

    public EternityEquation(String equationName, String equation, String displayEquation, Set<String> variable) {
        this.equationName = equationName;
        this.equation = equation;
        this.variable = variable;
        this.displayEquation = displayEquation;
    }

    public EternityEquation(String equation, String displayEquation, Set<String> variable) {
        this.equationName = "";
        this.equation = equation;
        this.variable = variable;
        this.displayEquation = displayEquation;
    }

    public EternityEquation(String displayEquation) {
        this.equationName = "";
        this.equation = displayEquation;
        this.variable = new HashSet<>();
        this.displayEquation = displayEquation;
    }

    public EternityEquation() {
        this.equationName = "";
        this.equation = "";
        this.displayEquation="";
        this.variable = null;
    }

    public String getEquationName() {
        return equationName;
    }

    public String getEquation() {
        return equation;
    }

    public Set<String> getVariable() {
        return variable;
    }

    public String getDisplayEquation() {
        return displayEquation;
    }

    public void setEquationName(String equationName) {
        this.equationName = equationName;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public void setVariable(Set<String> variable) {
        this.variable = variable;
    }

    public void setDisplayEquation(String displayEquation) {
        this.displayEquation = displayEquation;
    }

    public void addVariable(String varName){this.variable.add(varName);}

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
}
