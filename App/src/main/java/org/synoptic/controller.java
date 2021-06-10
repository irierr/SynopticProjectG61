package org.synoptic;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 800;

    public Scene loadScene(String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        return new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void displayScene(String path) throws IOException {
        Stage stage = new Stage();
        stage.setScene(loadScene(path));
        stage.show();
    }

    @FXML public Button climateButton;
    public void climateButton() throws IOException{
        Stage closeStage = (Stage) climateButton.getScene().getWindow();
        closeStage.close();
        displayScene("climate1.fxml");
    }


}
