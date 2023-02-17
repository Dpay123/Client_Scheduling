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

    /**Button for navigating to Appointments page*/
    @FXML
    private Button appointmentsBtn;

    /**Button for navigating to Customer page*/
    @FXML
    private Button customersBtn;

    /**Button for navigating to Reports page*/
    @FXML
    private Button reportsBtn;

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
     * Navigate to the Appointments page.
     * @param actionEvent
     */
    @FXML
    public void OnAppointmentsBtnClick(ActionEvent actionEvent) throws  IOException{
        Parent newScene = this.loadScene("AppointmentsPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Appointments");
        stage.show();
    }

    /**
     * Navigate to the Customers page.
     * @param actionEvent
     */
    @FXML
    public void OnCustomersBtnClick(ActionEvent actionEvent) {
    }

    /**
     * Navigate to the Reports page.
     * @param actionEvent
     */
    @FXML
    public void OnReportsBtnClick(ActionEvent actionEvent) {
    }
}
