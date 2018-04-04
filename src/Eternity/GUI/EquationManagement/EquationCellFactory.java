package Eternity.GUI.EquationManagement;

import Eternity.Logic.Equation.EternityEquation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;


public class EquationCellFactory implements Callback<ListView<EternityEquation>, ListCell<EternityEquation>> {
    @Override
    public ListCell<EternityEquation> call (ListView<EternityEquation> param){
        return new EquationCell();
    }
}
