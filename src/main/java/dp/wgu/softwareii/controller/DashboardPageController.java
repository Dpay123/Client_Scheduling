package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.model.User;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the Dashboard Page.
 */
public class DashboardPageController extends BaseController{

    /**The user logged in.*/
    public static User user;

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

    /**Button for navigating to Appointments page*/
    @FXML
    private Button appointmentsBtn;

    /**Button for navigating to Customer page*/
    @FXML
    private Button customersBtn;

    /**Button for navigating to Reports page*/
    @FXML
    private Button reportsBtn;

    @FXML
    private Button reportsBtn1;

    /**
     * Perform actions upon page initialization.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashGreeting.setText("Hello, " + user + "!");
        // TODO: set time
        // TODO: alert for urgent appts
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
    public void OnCustomersBtnClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("CustomersPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Customers");
        stage.show();
    }

    /**
     * Navigate to the Reports page.
     * @param actionEvent
     */
    @FXML
    public void OnReportsBtnClick(ActionEvent actionEvent) {
    }
}
