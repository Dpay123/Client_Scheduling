package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginPageController extends BaseController{

    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label loginTime;

    @FXML
    private Button loginButton;
    @FXML
    private Button exitButton;

    /**
     * Exits the program.
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
        stage.setTitle("DashBoard");
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