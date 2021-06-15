package org.synoptic;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.control.TabPane;
import javafx.scene.control.SingleSelectionModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class controller implements Initializable {

    public ImageView mapImage = new ImageView();
    public ScrollPane scroller = new ScrollPane();
    final DoubleProperty zoomProperty = new SimpleDoubleProperty(400);

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 800;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMap();

        //listens for an input in the shops list sets selection to the number in the list that's been selected
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

    @FXML public Button historyButton;
    public void historyButton() throws IOException {
        Stage closeStage = (Stage) historyButton.getScene().getWindow();
        closeStage.close();
        displayScene("history.fxml");
    }

    @FXML public Button ecoProblemsButton;
    public void ecoProblemsButton() throws IOException {
        Stage closeStage = (Stage) ecoProblemsButton.getScene().getWindow();
        closeStage.close();
        displayScene("ecoProblems.fxml");
    }

    @FXML public Button backInfo;
    @FXML public TabPane tabPane = new TabPane();

    public TabPane getTabPane() {
        return tabPane;
    }

    public void backInfoButton() throws IOException{
        Stage closeStage = (Stage) backInfo.getScene().getWindow();
        closeStage.close();
        displayScene("view.fxml");
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(3);

    }


    /* Directory Page -------------------------------------------------------------*/

    @FXML private Label POIName = new Label();
    @FXML private Label POIDescription = new Label();
    @FXML private TableView POIOpeningHours = new TableView();
    @FXML private ListView DirectoryList = new ListView();
    @FXML private ImageView POIImage = new ImageView();


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
