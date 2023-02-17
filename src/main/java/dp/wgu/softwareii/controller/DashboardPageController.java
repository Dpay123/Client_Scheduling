package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.dbAccess.DBCustomers;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the Dashboard Page.
 */
public class DashboardPageController extends BaseController{

    /**Label for user local time display*/
    @FXML
    private Label dashTime;

    /**Label for greeting/displaying user name*/
    @FXML
    private Label dashGreeting;

    /**Button for log out*/
    @FXML
    private Button logoutBtn;

    /**Button for exit*/
    @FXML
    private Button exitBtn;

    /**Tableview for displaying customers from db*/
    @FXML
    private TableView customerTV;

    /**Customer TableView column*/
    @FXML
    private TableColumn custIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn custNameCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn custPostCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn custPhoneCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn custDivisionCol;

    /**Button for adding customer*/
    @FXML
    private Button custAddBtn;

    /**Button for updating customer*/
    @FXML
    private Button custUpdateBtn;

    /**Button for deleting customer*/
    @FXML
    private Button custDeleteBtn;

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

    /**
     * Populates the dashboard data.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set the data source for the customers table
        customerTV.setItems(DBCustomers.getAll());
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        custPostCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));

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
     * Logout and return to the Login Screen.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void OnLogoutClick(ActionEvent actionEvent) throws IOException{
        // navigate to the Login Page
        Parent newScene = this.loadScene("LoginPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Login Page");
        stage.show();
    }

    /**
     Exits the program.
     @param actionEvent the event that triggers the method.
     */
    @FXML
    public void OnExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Navigate to the Add Customer page
     * @param actionEvent
     */
    @FXML
    public void OnCustAddClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("AddCustomerPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Add Customer");
        stage.show();
    }

    /**
     * Navigate to the Update Customer page for a selected customer
     * @param actionEvent
     */
    @FXML
    public void OnCustUpdateClick(ActionEvent actionEvent) throws IOException {
        // TODO: retrieve the selected customer and send to the update page
        // navigate to the update customer page
        Parent newScene = this.loadScene("UpdateCustomerPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Update Customer");
        stage.show();
    }

    /**
     * Delete a selected customer.
     * User must confirm prior to deletion.
     * All customer appointments must be deleted prior to deletion
     * @param actionEvent
     */
    @FXML
    public void OnCustDeleteClick(ActionEvent actionEvent) {
        // TODO: require confirmation for deletion
        // TODO: check for all deleted appts prior to customer deletion
        // TODO: delete the customer
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
    public void OnApptAddClick(ActionEvent actionEvent) throws IOException{
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
}
