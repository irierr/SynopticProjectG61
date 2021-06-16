package org.synoptic;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;

public class Controller implements Initializable {

    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 800;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMap();

        /**
         * Listens for an input in the DirectoryEntry, sets selection to the number in the list that's been selected
         * @author Darcey Gardiner
         **/
        DirectoryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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
                    DEAnnouncement.setText(entry.getAnnouncement());
                    List<Map.Entry<String, LocalTime[]>> hours = new ArrayList<>();

                    for (int i : entry.getOpeningHours().keySet())
                    {
                        hours.add(new AbstractMap.SimpleEntry(entry.dayNumberToString(i), entry.getOpeningHours().get(i)));
                    }

                    DEOpeningHours.setItems(FXCollections.observableArrayList(hours));
                }
                else
                {
                    DEQueryName.setText(null);
                    DEQueryDesc.setText(null);
                    DEQueryPhone.setText(null);
                    DEQueryAddress.setText(null);
                    DEAnnouncement.setText(null);
                    DEOpeningHours.getItems().clear();
                }

            }
        });

        /**
         * Listens for an input in the Activities table, sets selection to the number in the list that's been selected
         * @author Darcey Gardiner
         **/

        ActivityList.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Activity>) (observable, oldValue, newValue) -> {
            int selection = ActivityList.getSelectionModel().getSelectedIndex();
            if (selection != -1)
            {
                System.out.println("Updated selected Activity entry to: " + selection);
                Activity activity = (Activity) ActivityList.getItems().get(selection);

                if (activity != null)
                {
                    ActivityQName.setText(activity.getName());
                    ActivityQAddress.setText(activity.getAddress());
                    ActivityQDesc.setText(activity.getDescription());

                    switch (activity.getType()){
                        case ATTRACTION -> {
                            attractionType.setText("Phone:");
                            phoneOrAddress.setText(activity.getPhoneNumber());
                            ActivityPlaceholder.setImage(waterImage);
                        }
                        case WALKING_TRAIL -> {
                            attractionType.setText("End Address:");
                            phoneOrAddress.setText(activity.getEndAddress());
                            ActivityPlaceholder.setImage(activityImage);
                        }
                        default -> throw new IllegalStateException("Unexpected value: " + activity.getType());
                    }
                }
                else
                {
                    ActivityQName.setText(null);
                    ActivityQAddress.setText(null);
                    ActivityQDesc.setText(null);
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

    /**
     * Displays the Info tab with all the buttons and links to their relevant pages
     * @author Darcey Gardiner
     **/

    @FXML public Button climateButton;
    public void climateButton() throws IOException{
        Stage closeStage = (Stage) climateButton.getScene().getWindow();
        closeStage.close();
        displayScene("views/climate.fxml");
    }

    @FXML public Button demographicButton;
    public void demographicButton() throws IOException{
        Stage closeStage = (Stage) demographicButton.getScene().getWindow();
        closeStage.close();
        displayScene("views/demographics.fxml");
    }

    @FXML public Button localAreaButton;
    public void localAreaButton() throws IOException{
        Stage closeStage = (Stage) localAreaButton.getScene().getWindow();
        closeStage.close();
        displayScene("views/localArea.fxml");
    }

    @FXML public Button industriesAndEmploymentButton;
    public void industriesAndEmploymentButton() throws IOException{
        Stage closeStage = (Stage) industriesAndEmploymentButton.getScene().getWindow();
        closeStage.close();
        displayScene("views/industriesAndEmployment.fxml");
    }

    @FXML public Button historyButton;
    public void historyButton() throws IOException {
        Stage closeStage = (Stage) historyButton.getScene().getWindow();
        closeStage.close();
        displayScene("views/history.fxml");
    }

    @FXML public Button ecoProblemsButton;
    public void ecoProblemsButton() throws IOException {
        Stage closeStage = (Stage) ecoProblemsButton.getScene().getWindow();
        closeStage.close();
        displayScene("views/ecoProblems.fxml");
    }

    @FXML public Button backInfo;
    @FXML public TabPane tabPane = new TabPane();

    public TabPane getTabPane() {
        return tabPane;
    }

    public void backInfoButton() throws IOException{
        Stage closeStage = (Stage) backInfo.getScene().getWindow();
        closeStage.close();
        displayScene("views/view.fxml");
        // This doesn't quite work as it's meant to show the selected tab as the Info tab
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(3);

    }

    /* Directory Page ------------------------------------------------------------------------------------------------*/

    /**
     * Initializes the Directory Tab with all of the shops/restaurants/businesses etc in the database and loads
     * information on them based on which one is selected
     * @author Darcey Gardiner
     * @see <a href = "https://stackoverflow.com/questions/36657299/how-can-i-populate-a-listview-in-javafx-using-custom-objects">
     *     Populating a list view</a>
     **/

    @FXML private Label DEName;
    @FXML private TextField DEQueryName, DEQueryPhone, DEQueryAddress;
    @FXML private TextArea DEQueryDesc, DEAnnouncement;
    @FXML private TableView<Map.Entry<String, LocalTime[]>> DEOpeningHours = new TableView<>();
    @FXML private ListView<DirectoryEntry> DirectoryList = new ListView<>();
    @FXML private ImageView DirectoryEntryImage = new ImageView();

    public void loadDirectory(Event event) {
        DirectoryList.getItems().clear();
        DirectoryList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(DirectoryEntry item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        try{
            List<DirectoryEntry> directories = Database.getAllDirectoryEntrys();

            for (DirectoryEntry entry : directories) {
                DirectoryList.getItems().add(entry);
            }
            DEOpeningHours.getColumns().get(0).setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getKey()));
            DEOpeningHours.getColumns().get(1).setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getValue()[0].equals(param.getValue().getValue()[1]) ? "CLOSED" : param.getValue().getValue()[0]));
            DEOpeningHours.getColumns().get(2).setCellValueFactory(param -> new ReadOnlyObjectWrapper(param.getValue().getValue()[0].equals(param.getValue().getValue()[1]) ? "CLOSED" : param.getValue().getValue()[1]));
        } catch (SQLException e)
        {
            System.out.println("Error" + e.getMessage());
        }
    }


    /* Activity Page ----------------------------------------------------------*/

    /**
     * Initializes the Activity Tab with all of the actvities in the database and loads information on them
     * based on which one is selected
     * @author Darcey Gardiner
     **/

    @FXML private ListView ActivityList = new ListView();
    @FXML private TextField ActivityQName, ActivityQAddress, phoneOrAddress;
    @FXML private TextArea ActivityQDesc;
    @FXML private Label attractionType = new Label();
    @FXML private ImageView ActivityPlaceholder = new ImageView();
    @FXML private Image waterImage = new Image("images/waterPlaceholder.jpg");
    @FXML private Image activityImage = new Image("images/activityPlaceholder.jpg");

    public void loadActivities(Event event) {
        ActivityList.getItems().clear();
        ActivityList.setCellFactory(param -> new ListCell<Activity>() {
            @Override
            protected void updateItem(Activity item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                }
                else {
                    setText(item.getName());
                }
            }
        });
        try{
            for (Activity activity : Database.getAllActivities()) {
                ActivityList.getItems().add(activity);
            }
        } catch (SQLException e)
        {
            System.out.println("Error" + e.getMessage());
        }
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
