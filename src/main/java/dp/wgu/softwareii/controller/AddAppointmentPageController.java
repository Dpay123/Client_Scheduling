package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class AddAppointmentPageController {
    @javafx.fxml.FXML
    private Label typeField;
    @javafx.fxml.FXML
    private Label partTypeLabel;
    @javafx.fxml.FXML
    private TextField titleField;
    @javafx.fxml.FXML
    private TextField locationField;
    @javafx.fxml.FXML
    private Label partTypeLabel1;
    @javafx.fxml.FXML
    private TextArea descriptionField;
    @javafx.fxml.FXML
    private ComboBox typeCB;
    @javafx.fxml.FXML
    private ComboBox customerCB;
    @javafx.fxml.FXML
    private DatePicker datePick;
    @javafx.fxml.FXML
    private TextField timeField;
    @javafx.fxml.FXML
    private ComboBox contactCB;
    @javafx.fxml.FXML
    private Button saveBtn;
    @javafx.fxml.FXML
    private Button cancelBtn;

    @javafx.fxml.FXML
    public void OnSaveClick(ActionEvent actionEvent) {
        // TODO: save appointment and return to Dashboard
    }

    @javafx.fxml.FXML
    public void OnCancelClick(ActionEvent actionEvent) {
        // TODO: return to Dashboard
    }
}
