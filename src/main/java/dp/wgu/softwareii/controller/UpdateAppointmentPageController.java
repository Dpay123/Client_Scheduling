package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.dbAccess.DBContacts;
import dp.wgu.softwareii.dbAccess.DBCustomers;
import dp.wgu.softwareii.model.Appointment;
import dp.wgu.softwareii.model.Contact;
import dp.wgu.softwareii.model.Customer;
import dp.wgu.softwareii.model.Type;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * The controller for the Update Appt Page.
 */
public class UpdateAppointmentPageController extends BaseController {

    /**
     * The appointment obj that is to be updated.
     */
    public static Appointment appt;

    /**A list of Customers for the combo box*/
    ObservableList<Customer> customers;

    /**A list of Contacts for the combo box*/
    ObservableList<Contact> contacts;

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
        customers = DBCustomers.getAll();
        customerCB.setItems(customers);
        contacts = DBContacts.getAll();
        contactCB.setItems(contacts);

        // set the combo boxes
        int customerID = appt.getCustomerId();
        for (Customer customer : customers) {
            if (customer.getId() == customerID) customerCB.setValue(customer);
        }
        int contactID = appt.getContactId();
        for (Contact contact : contacts) {
            if (contact.getId() == contactID) contactCB.setValue(contact);
        }
        typeCB.getItems().setAll(Arrays.asList(Type.values()));
        for (Object type : typeCB.getItems()) {
            Type t = (Type)type;
            if (t.toString().equals(appt.getType())) typeCB.setValue(type);
        }

        // TODO: set dates, times
    }

    /**
     * Retrieve the form data and update the Appointment.
     * Return to Appointments after saving.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        // retrieve input data
        int id = appt.getId();
        String title = titleField.getText();
        String location = locationField.getText();
        String description = descriptionField.getText();
        Type type = (Type)typeCB.getSelectionModel().getSelectedItem();
        // TODO: dates/times
        Customer customer = (Customer)customerCB.getSelectionModel().getSelectedItem();
        // TODO: userID
        int testUser = 1;
        Contact contact = (Contact)contactCB.getSelectionModel().getSelectedItem();

        // attempt to update the appointment
        boolean updated = DBAppointments.updateAppointment(
                id,
                title,
                location,
                description,
                type.toString(),
                customer.getId(),
                testUser,
                contact.getId());
        if (!updated) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setContentText("Could not update.");
            error.showAndWait();
        }
        else {
            // return to appointments page
            Parent newScene = this.loadScene("AppointmentsPage");
            Stage stage = this.getStageWithSetScene(actionEvent, newScene);
            stage.setTitle("Appointments");
            stage.show();
        }
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
