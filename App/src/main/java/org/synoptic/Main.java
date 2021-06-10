package org.synoptic;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 800;

    @Override
    public void start(Stage stage) throws Exception {
        Parent homeRoot = FXMLLoader.load(getClass().getResource("/view.fxml"));
        Scene homeScene = new Scene(homeRoot, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setScene(homeScene);
        homeScene.getStylesheets().add("stylesheet.css");
        stage.setFullScreen(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
