package Eternity.GUI.EquationManagement;

import Eternity.Logic.Equation.EternityEquation;
import Eternity.Logic.Equation.EternityVariable;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class EquationCell extends ListCell<EternityEquation> {
    @FXML private Label equationName;

    @FXML private Label variableNames;

    public EquationCell(){
        loadFXML();
    }

    private void loadFXML(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("equation_cell.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(EternityEquation item, boolean empty){
        super.updateItem(item, empty);

        if(empty){
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else{
            equationName.setText(item.getEquation());
            Set<String> variables = item.getVariable();
            for(String variableName:variables){
                variableNames.setText(variableNames.getText().concat(variableName));
            }
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}
