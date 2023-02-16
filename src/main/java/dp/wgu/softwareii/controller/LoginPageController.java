package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.dbAccess.DBCountries;
import dp.wgu.softwareii.model.Appointment;
import dp.wgu.softwareii.model.Country;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the Login Page.
 */
public class LoginPageController extends BaseController{

    /**Field for user to input username*/
    @FXML
    private TextField userField;

    /**Field for user to input password*/
    @FXML
    private TextField passwordField;

    /**Label for local time display*/
    @FXML
    private Label loginTime;

    /**Button to attempt login*/
    @FXML
    private Button loginButton;

    /**Button to exit the program*/
    @FXML
    private Button exitButton;

    /**
     * Code to execute upon initializing the page.
     * @param url the URL
     * @param resourceBundle the ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // TEST:
        // get a list of countries from the db
        ObservableList<Country> countries = DBCountries.getAll();
        for (Country c : countries) System.out.println(c.getName());
        // get a list of appts from the db
        ObservableList<Appointment> appts = DBAppointments.getAll();
        for (Appointment a : appts) System.out.println(a.getTitle());
    }

    /**
     * Attempt a Login.
     * Successful login passes user validation and directs to the Dashboard.
     * Unsuccessful login fails user validation and displays error.
     * @param actionEvent the event that triggers the method
     * @throws IOException
     */
    @FXML
    public void onLoginClick(ActionEvent actionEvent) throws IOException {
        // TODO: user validation
        // TODO: error popup if invalid user
        // navigate to the Dashboard
        this.goToMainMenu(actionEvent);
    }

    /**
     Exits the program.
     @param actionEvent the event that triggers the method.
     */
    @FXML
    public void onExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }
}