package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginPageController {

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

    @FXML
    public void onLoginClick(ActionEvent actionEvent) {
        // TODO: user validation
        // TODO: error popup if invalid user
    }

    @FXML
    public void onExitClick(ActionEvent actionEvent) {
        // TODO: close the program
    }
}