package Eternity;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class EternityVariable {
    private final SimpleStringProperty varName = new SimpleStringProperty("");
    private final SimpleDoubleProperty varValue = new SimpleDoubleProperty(0.0);

    public EternityVariable(){
        setVarName("");
        setVarValue(0.0);
    }

    public EternityVariable(String varName){
        setVarName(varName);
        setVarValue(0.0);
    }

    public EternityVariable(String varName, Double varValue){
        setVarName(varName);
        setVarValue(varValue);
    }

    public String getVarName() {
        return varName.get();
    }

    public SimpleStringProperty varNameProperty() {
        return varName;
    }

    public double getVarValue() {
        return varValue.get();
    }

    public SimpleDoubleProperty varValueProperty() {
        return varValue;
    }

    public void setVarName(String varName) {
        this.varName.set(varName);
    }

    public void setVarValue(double varValue) {
        this.varValue.set(varValue);
    }
}
