package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddCustomerPageController extends BaseController {
    @javafx.fxml.FXML
    private Label partTypeLabel;
    @javafx.fxml.FXML
    private TextField nameField;
    @javafx.fxml.FXML
    private TextField addressField;
    @javafx.fxml.FXML
    private TextField postalField;
    @javafx.fxml.FXML
    private TextField phoneField;
    @javafx.fxml.FXML
    private Label partTypeLabel1;
    @javafx.fxml.FXML
    private ComboBox countryComboBox;
    @javafx.fxml.FXML
    private ComboBox stateComboBox;
    @javafx.fxml.FXML
    private Button AddButton;
    @javafx.fxml.FXML
    private Button CancelBtn;

    @javafx.fxml.FXML
    public void OnSaveClick(ActionEvent actionEvent) {
        // TODO: save customer and return to Dashboard
    }

    @javafx.fxml.FXML
    public void OnCancelClick(ActionEvent actionEvent) {
        // TODO: return to DashBoard
    }
}
