package Eternity;

import java.util.Set;

public class EternityEquation {
    String equation;
    Set<String> variable;

    public EternityEquation(String equation, Set<String> variable) {
        this.equation = equation;
        this.variable = variable;
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
}
