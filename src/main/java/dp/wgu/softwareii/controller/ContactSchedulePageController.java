package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.Utilities.TimeHandler;
import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.model.Appointment;
import dp.wgu.softwareii.model.Contact;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class ContactSchedulePageController extends BaseController{

    /**The contact to populate the schedule with*/
    public static Contact contact;

    /**The list of appointments to populate*/
    private static ObservableList<Appointment> appts;

    /**Header label to indicate who the snapshot is for*/
    @FXML
    private Label headerLbl;

    /**Tableview for displaying appts from db*/
    @FXML
    private TableView apptTV;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptTitleCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptDescriptionCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptLocationCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptTypeCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn<Appointment, String> apptStartCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn<Appointment, String> apptEndCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptCustIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptUserIdCol;

    /**
     * Populates the appt data.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // set the header
        headerLbl.setText("Appointment Snapshot for " + contact.getName());
        // set the data source for the appts table
        appts = DBAppointments.getContactAppointments(contact.getId());
        apptTV.setItems(appts);
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        // show the UTC times as local user offset time
        apptStartCol.setCellValueFactory(utc -> {
            ZonedDateTime local = TimeHandler.getZonedDateTimeLocal(utc.getValue().getStartZDT_utc());
            return new SimpleStringProperty(local.toString());
        });
        apptEndCol.setCellValueFactory(utc -> {
            ZonedDateTime local = TimeHandler.getZonedDateTimeLocal(utc.getValue().getEndZDT_utc());
            return new SimpleStringProperty(local.toString());
        });
    }

    /**
     * Return to the Reports page.
     * @param actionEvent
     */
    @FXML
    public void OnReportsClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("ReportsPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Reports");
        stage.show();
    }

    /**
     * Return to the Dashboard.
     * @param actionEvent
     */
    @FXML
    public void OnDashboardClick(ActionEvent actionEvent) throws IOException {
        this.goToMainMenu(actionEvent);
    }
}
