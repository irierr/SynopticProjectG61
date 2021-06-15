package org.synoptic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controller implements Initializable {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 800;

    public ImageView mapImage = new ImageView();
    public ScrollPane scroller = new ScrollPane();
    final DoubleProperty zoomProperty = new SimpleDoubleProperty(400);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMap();
    }

    private void initMap(){
        scroller.setHvalue(0.5);
        scroller.setVvalue(1);
        mapImage.preserveRatioProperty().set(true);

        zoomProperty.addListener(arg0 -> {
            double oldV = scroller.getVvalue() ;
            double oldH = scroller.getHvalue();
            mapImage.setFitWidth(zoomProperty.get() * 4);
            mapImage.setFitHeight(zoomProperty.get() * 3);
            scroller.setVvalue(oldV);
            scroller.setHvalue(oldH);
        });

        scroller.addEventFilter(ScrollEvent.SCROLL, event -> {
            if ((event.getDeltaY() > 0) && (mapImage.getFitHeight() < 2130)) {
                zoomProperty.set(zoomProperty.get() * 1.1);
            } else if ((event.getDeltaY() < 0) && (mapImage.getFitWidth() > 1455)) {
                zoomProperty.set(zoomProperty.get() / 1.1);
            }
        });
    }

    public Scene loadScene(String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
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
        displayScene("climate.fxml");

    }

    @FXML public Button demographicButton;
    public void demographicButton() throws IOException{
        Stage closeStage = (Stage) demographicButton.getScene().getWindow();
        closeStage.close();
        displayScene("demographics.fxml");
    }

    @FXML public Button localAreaButton;
    public void localAreaButton() throws IOException{
        Stage closeStage = (Stage) localAreaButton.getScene().getWindow();
        closeStage.close();
        displayScene("localArea.fxml");
    }

    @FXML public Button industriesAndEmploymentButton;
    public void industriesAndEmploymentButton() throws IOException{
        Stage closeStage = (Stage) industriesAndEmploymentButton.getScene().getWindow();
        closeStage.close();
        displayScene("industriesAndEmployment.fxml");
    }

    @FXML public Button backInfo;
    public void backInfoButton() throws IOException{
        Stage closeStage = (Stage) backInfo.getScene().getWindow();
        closeStage.close();
        displayScene("view.fxml");
    }

}
