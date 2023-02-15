package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
        Parent newScene = this.loadScene("DashboardPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Dashboard");
        stage.show();
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