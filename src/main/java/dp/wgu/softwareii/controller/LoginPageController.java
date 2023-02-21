package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBUsers;
import dp.wgu.softwareii.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The controller for the Login Page.
 */
public class LoginPageController extends BaseController{

    /**Store the zoneID of the user*/
    static ZoneId zoneID;

    /**Field for user to input username*/
    @FXML
    private TextField userField;

    /**Field for user to input password*/
    @FXML
    private TextField passwordField;

    /**Display the timezone*/
    @FXML
    private Label zoneLabel;

    /**
     * Code to execute upon initializing the page.
     * @param url the URL
     * @param resourceBundle the ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: show time info
        zoneID = ZonedDateTime.now().getZone();
        zoneLabel.setText(zoneID.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
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
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid");
            error.setContentText("Login credentials invalid");
            error.showAndWait();
        }
        else {
            // pass user to dashboard
            DashboardPageController.user = validUser;
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