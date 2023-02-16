package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the Update Appt Page.
 */
public class UpdateAppointmentPageController extends BaseController {

    /**Field for user to input title*/
    @FXML
    private TextField titleField;

    /**Field for user to input location*/
    @FXML
    private TextField locationField;

    /**Field for user to input description*/
    @FXML
    private TextArea descriptionField;

    /**Combo box for user to select type*/
    @FXML
    private ComboBox typeCB;

    /**Combo box for user to select customer*/
    @FXML
    private ComboBox customerCB;

    /**Datepicker for user to select date*/
    @FXML
    private DatePicker datePick;

    /**Field for user to input time*/
    @FXML
    private TextField timeField;

    /**Combo box for user to select contact*/
    @FXML
    private ComboBox contactCB;

    /**Button to save Appt*/
    @FXML
    private Button saveBtn;

    /**Button to cancel*/
    @FXML
    private Button cancelBtn;

    /**
     * Retrieve the form data and update the Appointment.
     * Return to Dashboard after saving.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        // TODO: Modify current Appointment obj, save,
        // return to Dashboard
        this.goToMainMenu(actionEvent);
    }

    /**
     * Cancel and return to Dashboard.
     * @param actionEvent
     */
    @FXML
    public void OnCancelClick(ActionEvent actionEvent) throws IOException {
        // return to Dashboard
        this.goToMainMenu(actionEvent);
    }
}
