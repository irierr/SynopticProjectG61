package org.synoptic;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class controller implements Initializable {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 800;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMap();

        //listens for an input in the shops list sets selection to the number in the list that's been selected
        //initialising ShopList List
        DirectoryList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DirectoryEntry>() {
            @Override
            public void changed(ObservableValue<? extends DirectoryEntry> observable, DirectoryEntry oldValue, DirectoryEntry newValue) {
                int selection = DirectoryList.getSelectionModel().getSelectedIndex();
                if (selection != -1)
                {
                    System.out.println("Updated selected Directory entry to: " + selection);
                    DirectoryEntry entry = DirectoryList.getItems().get(selection);

                    if (entry != null)
                    {
                        DEQueryName.setText(entry.getName());
                        DEQueryDesc.setText(entry.getDescription());
                        DEQueryPhone.setText(entry.getPhoneNumber());
                        DEQueryAddress.setText(entry.getAddress());
                    }
                    else
                    {
                        DEQueryName.setText(null);
                        DEQueryDesc.setText(null);
                        DEQueryPhone.setText(null);
                        DEQueryAddress.setText(null);
                    }

                }
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

    /*  Info Page ----------------------------------------------------------------------------------------------------*/

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


    /* Directory Page ------------------------------------------------------------------------------------------------*/

    @FXML private Label DEName;
    @FXML private TextField DEQueryName, DEQueryPhone, DEQueryAddress;
    @FXML private TextArea DEQueryDesc;
    @FXML private Label POIDescription;
    @FXML private TableView POIOpeningHours = new TableView();
    @FXML private ListView<DirectoryEntry> DirectoryList = new ListView();
    @FXML private ImageView POIImage = new ImageView();


    public void loadDirectory(Event event) {
        DirectoryList.getItems().clear();

        //TODO add reference to stackoverflow

        DirectoryList.setCellFactory(param -> new ListCell<DirectoryEntry>() {
            @Override
            protected void updateItem(DirectoryEntry item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getName() == null)
                {
                    setText(null);
                }
                else
                {
                    setText(item.getName());
                }

            }
        });

        //try
        //{

            List<DirectoryEntry> list = new ArrayList<>();
            list.add(new DirectoryEntry(DirectoryEntry.Type.SHOP, "1", "Bank", "1 Bank ROad", "Money", new HashMap<>(), ""));
            list.add(new DirectoryEntry(DirectoryEntry.Type.SHOP, "2", "Bank 2", "2 Bank ROad", "Money", new HashMap<>(), ""));
            for (DirectoryEntry entry : /*Database.getAllDirectoryEntrys()*/ list)
            {
                DirectoryList.getItems().add(entry);
            }
        //}
        /*catch (SQLException e)
        {
            //TODO Handle SQL Exception
        }*/
    }


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

    /* Map Page ------------------------------------------------------------------------------------------------------*/

    @FXML public ImageView mapImage = new ImageView();
    @FXML ScrollPane scroller = new ScrollPane();
    final DoubleProperty zoomProperty = new SimpleDoubleProperty(400);

    /**
     * Initializes the map to the correct position and adds listeners for zooming functionality.
     * @author Irie Railton
     * @see <a href = "http://www.java2s.com/Code/Java/JavaFX/JavaFXImageZoomExample.htm">JavaFX Image Zoom Example</a>
     **/
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
}
