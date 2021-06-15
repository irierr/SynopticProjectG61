package org.synoptic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Window;
import javafx.scene.control.TabPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controller implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //listens for an iput in the shops list sets selection to the number in the list that's been selected
        //initialising ShopList List
        DirectoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                int selection = DirectoryList.getSelectionModel().getSelectedIndex();
                if (selection != -1)
                {
                    System.out.println("Updated selected goal to: " + selection);
                }
            }
        });
    }
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 800;


    public Scene loadScene(String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
        return new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public void displayScene(String path) throws IOException {
        Stage stage = new Stage();
        stage.setScene(loadScene(path));
        stage.show();
    }

    /*  Info Page -----------------------------------------------------------*/

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


    /* Shops Page -------------------------------------------------------------*/

    @FXML private Label ShopName = new Label();
    @FXML private Label Description = new Label();
    @FXML private Label OpeningHours = new Label();
    @FXML private ListView DirectoryList = new ListView();
    @FXML private ListView StockList = new ListView();
    @FXML private ImageView ShopImage = new ImageView();

    /* Activity Page ----------------------------------------------------------*/

    @FXML private ListView ActivityList = new ListView();
    @FXML private Label ActivityDescription = new Label();
    @FXML private Button ActivityMapButton = new Button();
    @FXML private Button ActivityDirectoryButton = new Button();


    public void ActivityMapButton() throws IOException
    {

    }

    public void ActivityDirectoryButton() throws IOException
    {

    }
}
