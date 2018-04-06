package Eternity.GUI.EquationManagement.EquationSaver;

import Eternity.GUI.MainView.EternityController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class EquationSaver {

    @FXML private static EternityController eternityController;
    @FXML protected TextField equationNameTF;
    private static Stage stage;


    /**
     * Initializes the equation saving window
     * @param mainController the primary controller
     */
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
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icons/EternityLogo.png")));
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

    /**
     * Validates that there is an equation to save. And then calls the main controller to save it.
     */
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

    /**
     * Cancels the save for the equation
     */
    @FXML
    protected void cancelSave(){
        stage.hide();
        eternityController.navMenuSlide();

    }
}
