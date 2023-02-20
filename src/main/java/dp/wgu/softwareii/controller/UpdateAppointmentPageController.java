package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.model.Appointment;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the Update Appt Page.
 */
public class UpdateAppointmentPageController extends BaseController {

    /**
     * The appointment obj that is to be updated.
     */
    public static Appointment appt;

    /**Field for appt ID*/
    @FXML
    private TextField IDField;

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
     * Populates the data fields with the current appt data to be updated.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // populate the data
        IDField.setText(String.valueOf(appt.getId()));
        titleField.setText(appt.getTitle());
        locationField.setText(appt.getLocation());
        descriptionField.setText(appt.getDescription());
        timeField.setText(appt.getStartDateTime());


        // TODO: set dates, times
    }

    /**
     * Retrieve the form data and update the Appointment.
     * Return to Appointments after saving.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        // TODO: Modify current Appointment obj, save,
        // return to appointments page
        Parent newScene = this.loadScene("AppointmentsPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Appointments");
        stage.show();
    }

    /**
     * Cancel and return to Appointments.
     * @param actionEvent
     */
    @FXML
    public void OnCancelClick(ActionEvent actionEvent) throws IOException {
        // return to appointments page
        Parent newScene = this.loadScene("AppointmentsPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Appointments");
        stage.show();
    }
}
