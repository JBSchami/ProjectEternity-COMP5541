package Eternity.GUI.EquationManagement.EquationSaver;

import Eternity.GUI.EquationManagement.EquationManager.EquationManagerController;
import Eternity.GUI.MainView.EternityController;
import Eternity.Logic.Equation.EternityEquation;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class EquationSaver {

    @FXML private static EternityController eternityController;
    @FXML protected TextField equationNameTF;
    private static Stage stage;

    public EquationSaver() {

    }

    @FXML
    public void init(EternityController mainController) {
        eternityController = mainController;
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setController(this);
            root = loader.load(getClass().getClassLoader().getResource("Eternity/GUI/EquationManagement/EquationSaver/Equation_Saver.fxml"));
            stage = new Stage();
            stage.setTitle("Eternity Equation Manager");
            stage.setScene(new Scene(root, 500, 200));
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    mainController.setEqManagerActive(false);
                }
            });
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void saveEquation(){
        if(!equationNameTF.getText().isEmpty()){
            eternityController.saveEquation(equationNameTF.getText());
            stage.hide();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Equation Name Warning");
            alert.setHeaderText("No equation name was found.");
            alert.setContentText("Please enter a name for your new equation before saving.");
            alert.showAndWait();
        }

    }

    @FXML
    protected void cancelSave(){
        stage.hide();
        eternityController.navMenuSlide();

    }
}
