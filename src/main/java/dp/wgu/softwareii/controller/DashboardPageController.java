package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the Dashboard Page.
 */
public class DashboardPageController extends BaseController{
    @FXML
    private Label dashTime;
    @FXML
    private Label dashGreeting;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private TableView customerTV;
    @FXML
    private TableColumn partIdCol;
    @FXML
    private TableColumn partNameCol;
    @FXML
    private TableColumn partInvCol;
    @FXML
    private TableColumn partPriceCol;
    @FXML
    private Button custAddBtn;
    @FXML
    private Button custUpdateBtn;
    @FXML
    private Button custDeleteBtn;
    @FXML
    private RadioButton apptFilterAll;
    @FXML
    private ToggleGroup apptFilter;
    @FXML
    private RadioButton apptFilterWeek;
    @FXML
    private RadioButton apptFilterMonthRb;
    @FXML
    private TableView productTableView;
    @FXML
    private TableColumn productIdCol;
    @FXML
    private TableColumn productNameCol;
    @FXML
    private TableColumn productInvCol;
    @FXML
    private TableColumn productPriceCol;
    @FXML
    private Button apptAddBtn;
    @FXML
    private Button apptUpdateBtn;
    @FXML
    private Button apptDeleteBtn;

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
