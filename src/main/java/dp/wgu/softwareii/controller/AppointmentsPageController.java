package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBAppointments;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsPageController extends BaseController {

    /**Tableview for displaying appts from db*/
    @FXML
    private TableView apptTV;

    /**Toggle group for appt filtering*/
    @FXML
    private ToggleGroup apptFilter;

    /**Button for filtering appts by all*/
    @FXML
    private RadioButton apptFilterAll;

    /**Button for filtering appts by week*/
    @FXML
    private RadioButton apptFilterWeek;

    /**Button for filtering appts by month*/
    @FXML
    private RadioButton apptFilterMonthRb;

    /**Button for adding appt*/
    @FXML
    private Button apptAddBtn;

    /**Button for updating appt*/
    @FXML
    private Button apptUpdateBtn;

    /**Button for deleting appt*/
    @FXML
    private Button apptDeleteBtn;

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
    private TableColumn apptContactCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptTypeCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptStartCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptEndCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptCustIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptUserIdCol;

    /**Button for returning to dashboard*/
    @FXML
    private Button dashboardBtn;

    /**
     * Populates the appt data.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set the data source for the appts table
        apptTV.setItems(DBAppointments.getAll());
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("startDateTime"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("endDateTime"));
        apptCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }

    /**
     * Filter the Appointments table to select all
     * @param actionEvent
     */
    @FXML
    public void OnApptFilterAllClick(ActionEvent actionEvent) {
        // TODO: filter functionality
    }

    /**
     * Filter the Appointments table to select those within a week
     * @param actionEvent
     */
    @FXML
    public void OnApptFilterWeekClick(ActionEvent actionEvent) {
        // TODO: filter functionality
    }

    /**
     * Filter the Appointments table to select those within month
     * @param actionEvent
     */
    @FXML
    public void OnApptFilterMonthClick(ActionEvent actionEvent) {
        // TODO: filter functionality
    }

    /**
     * Navigate to the Add Appointment page
     * @param actionEvent
     */
    @FXML
    public void OnApptAddClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("AddAppointmentPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Add Appointment");
        stage.show();
    }

    /**
     * Navigate to the Update Appointment page for a selected Appointment
     * @param actionEvent
     */
    @FXML
    public void OnApptUpdateClick(ActionEvent actionEvent) throws IOException{
        // TODO: retrieve the selected appointment and send to the update page
        // navigate to the update customer page
        Parent newScene = this.loadScene("UpdateAppointmentPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Update Appointment");
        stage.show();
    }

    /**
     * Delete an appointment.
     * User must confirm prior to deletion.
     * @param actionEvent
     */
    @FXML
    public void OnApptDeleteClick(ActionEvent actionEvent) {
        // TODO: confirmation of deletion
        // TODO: delete appt
    }

    /**
     * Return to Dashboard.
     * @param actionEvent
     */
    @FXML
    public void OnDashboardBtnClick(ActionEvent actionEvent) throws IOException {
        // return to Dashboard
        this.goToMainMenu(actionEvent);
    }
}
