package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBUsers;
import dp.wgu.softwareii.model.User;
import dp.wgu.softwareii.utilities.LogActivity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The controller for the Login Page.
 */
public class LoginPageController extends BaseController{

    /**Used to determine language of display text*/
    private ResourceBundle rb;

    /**Store the zoneID of the user*/
    static ZoneId zoneID;

    /**Field for user to input username*/
    @FXML
    private TextField userField;

    /**Field for user to input password*/
    @FXML
    private TextField passwordField;

    /**The header for the login page*/
    @FXML
    private Label headerLbl;

    /**The username field label*/
    @FXML
    private Label userLbl;

    /**The password field label*/
    @FXML
    private Label passwordLbl;

    /**The zone field label*/
    @FXML
    private Label zoneLbl;

    /**The zone data label*/
    @FXML
    private Label zoneData;

    /**The login button*/
    @FXML
    private Button loginButton;

    /**The exit button*/
    @FXML
    private Button exitButton;

    /**
     * Code to execute upon initializing the page.
     * Get zone info and language info and use to set text.
     * @param url the URL
     * @param resourceBundle the ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // language detection
        this.rb = ResourceBundle.getBundle("Bundle", Locale.getDefault());
        System.out.println("Locale detected: " + rb.getString("Locale"));
        // get the zoneId of the user based upon their system default
        zoneID = ZoneId.systemDefault();
        // set text fields based on language
        headerLbl.setText(rb.getString("header"));
        userLbl.setText(rb.getString("userLbl"));
        passwordLbl.setText(rb.getString("passwordLbl"));
        zoneLbl.setText(rb.getString("zoneLbl"));
        zoneData.setText(zoneID.toString());
        loginButton.setText(rb.getString("login"));
        exitButton.setText(rb.getString("exit"));
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
        // user validation
        String username = userField.getText();
        String password = passwordField.getText();
        User validUser = DBUsers.validate(username, password);

        if (validUser == null) {
            // record login attempt
            LogActivity.loginAttempt(username, false);
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle(rb.getString("errorTitle"));
            error.setContentText(rb.getString("errorMsg"));
            error.setHeaderText(rb.getString("errorTitle"));
            error.showAndWait();
        }
        else {
            // record login attempt
            LogActivity.loginAttempt(username, true);
            // pass user to dashboard
            DashboardPageController.user = validUser;
            DashboardPageController.uponLogin = true;
            // navigate to the Dashboard
            this.goToMainMenu(actionEvent);
        }
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