package org.synoptic;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 800;


    public static void switchView(String view, MouseEvent event, Class c) throws IOException {
        Parent parent = FXMLLoader.load(c.getResource(view));
        Scene scene = new Scene(parent, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML public Button climateButton;
    public void climateButton(MouseEvent event) throws IOException{
        switchView("climate.fxml", event, getClass());
    }

}
