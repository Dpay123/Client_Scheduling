package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.dbAccess.DBContacts;
import dp.wgu.softwareii.dbAccess.DBCustomers;
import dp.wgu.softwareii.model.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Predicate;

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

    /**Combo box for user to select contact*/
    @FXML
    private ComboBox contactCB;

    /**Field for user to input start time*/
    @FXML
    private TextField startTF;

    /**Field for user to input end time*/
    @FXML
    private TextField endTF;

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
        // get localDate from and set as datePicker value
        LocalDate date = appt.getStartDateTime().toLocalDate();
        datePick.setValue(date);
        // get localTime from each and set as start/end time
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = appt.getStartDateTime().toLocalTime();
        LocalTime endTime = appt.getEndDateTime().toLocalTime();
        startTF.setText(startTime.format(dtf));
        endTF.setText(endTime.format(dtf));
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
        String description = descriptionField.getText();
        String location = locationField.getText();
        Type type = (Type)typeCB.getSelectionModel().getSelectedItem();
        Customer customer = (Customer)customerCB.getSelectionModel().getSelectedItem();
        User user = DashboardPageController.user;
        Contact contact = (Contact)contactCB.getSelectionModel().getSelectedItem();
        // build LocalDateTimes from date and times input
        LocalDate date = datePick.getValue();
        LocalTime startTime = LocalTime.parse(startTF.getText());
        LocalTime endTime = LocalTime.parse(endTF.getText());
        LocalDateTime startLDT =  LocalDateTime.of(date, startTime);
        LocalDateTime endLDT = LocalDateTime.of(date, endTime);
        // build ZoneDateTimes from above using user zoneID
        ZonedDateTime startZDT = ZonedDateTime.of(startLDT, ZoneId.systemDefault());
        ZonedDateTime endZDT = ZonedDateTime.of(endLDT, ZoneId.systemDefault());
        // convert to UTC offset
        ZonedDateTime startZDT_utc = ZonedDateTime.ofInstant(startZDT.toInstant(), ZoneId.of("UTC"));
        ZonedDateTime endZDT_utc = ZonedDateTime.ofInstant(endZDT.toInstant(), ZoneId.of("UTC"));

        // check for appt overlap for that customer
        var overlappingAppts = DBAppointments.getAll();
        Predicate<Appointment> overlaps = i -> {
            return i.getCustomerId() == customer.getId()
                    && i.getStartDateTime().isBefore(endZDT_utc)
                    && startZDT_utc.isBefore(i.getEndDateTime())
                    && id != i.getId();
        };
        overlappingAppts.setPredicate(overlaps);

        if (!overlappingAppts.isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Appointment overlap");
            String overlap = "The selected times overlaps an existing appointment for this day/customer:\n"
                    + overlappingAppts.get(0);
            error.setContentText(overlap);
            error.showAndWait();
            return;
        }

        // attempt to update the appointment
        boolean updated = DBAppointments.updateAppointment(
                id,
                title,
                description,
                location,
                type.toString(),
                startZDT_utc,
                endZDT_utc,
                customer.getId(),
                user.getId(),
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
