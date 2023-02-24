package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.Utilities.TimeHandler;
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
import java.time.format.DateTimeParseException;
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

    /**
     * The user who is adjusting the appointment.
     */
    public static User user = DashboardPageController.user;

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

    private static ZonedDateTime businessHoursStart;
    private static ZonedDateTime businessHoursEnd;

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
        // convert UTC-offset ZDT to the users local time zone
        ZonedDateTime startZDT_local = TimeHandler.utcToLocalOffset(appt.getStartZDT_utc());
        ZonedDateTime endZDT_local = TimeHandler.utcToLocalOffset(appt.getEndZDT_utc());

        // set datePicker value to the local date of the user
        datePick.setValue(startZDT_local.toLocalDate());

        // set start/end time, formatted, to the local time of the user
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime startTime = startZDT_local.toLocalTime();
        LocalTime endTime = endZDT_local.toLocalTime();
        startTF.setText(startTime.format(dtf));
        endTF.setText(endTime.format(dtf));
    }

    /**
     * Validates that text string is between 1-50 characters
     * @param str
     * @return
     */
    private boolean valid(String str) {
        return str.length() > 0 && str.length() <= 50;
    }

    /**
     * Determine whether an appointment falls within business hours.
     * An appointment has a start and end that indicates the duration of the appointment.
     * @param apptStart
     * @param apptEnd
     * @return
     */
    public static boolean isDuringBusinessHours(ZonedDateTime apptStart, ZonedDateTime apptEnd) {
        ZoneId appointmentZone = apptStart.getZone();
        ZoneId businessZone = TimeHandler.businessZone;

        // Convert business hours to appointment time zone
        businessHoursStart = ZonedDateTime.of(
                apptStart.toLocalDate(),
                LocalTime.of(TimeHandler.openHour, 0),
                businessZone
        ).withZoneSameInstant(appointmentZone);

        businessHoursEnd = ZonedDateTime.of(
                apptStart.toLocalDate(),
                LocalTime.of(TimeHandler.closeHour, 0),
                businessZone
        ).withZoneSameInstant(appointmentZone);

        // Check for overlap between appointment and business hours
        return !apptStart.isBefore(businessHoursStart) && !apptEnd.isAfter(businessHoursEnd);
    }

    /**
     * Retrieve the form data and update the Appointment.
     * Return to Appointments after saving.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        int id = appt.getId();
        // retrieve and validate input text data
        String title = titleField.getText();
        String description = descriptionField.getText();
        String location = locationField.getText();
        if (!valid(title) || !valid(description) || !valid(location)) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid entry");
            error.setContentText("Must provide a title, description, and location between 1-50 characters");
            error.showAndWait();
            return;
        }
        // retrieve and validate input selection data
        Type type = (Type)typeCB.getSelectionModel().getSelectedItem();
        Customer customer = (Customer)customerCB.getSelectionModel().getSelectedItem();
        User user = DashboardPageController.user;
        Contact contact = (Contact)contactCB.getSelectionModel().getSelectedItem();
        LocalDate date = datePick.getValue();
        if (type == null || customer == null || contact == null || date == null) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid selection");
            error.setContentText("Must select a type, customer, contact, and date");
            error.showAndWait();
            return;
        }
        // retrieve and validate input times
        LocalTime startTime;
        LocalTime endTime;
        try {
            startTime = LocalTime.parse(startTF.getText());
            endTime = LocalTime.parse(endTF.getText());
        }
        catch (DateTimeParseException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid time format");
            error.setContentText("Must enter valid time formatted as HH:MM");
            error.showAndWait();
            return;
        }
        if (endTime.isBefore(startTime)) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid time entry");
            error.setContentText("End time cannot be <= Start time.");
            error.showAndWait();
            return;
        }
        // build LocalDateTimes from valid date and valid times input
        ZonedDateTime startZDT_utc = TimeHandler.getZonedDateTimeUTC(LocalDateTime.of(date, startTime));
        ZonedDateTime endZDT_utc = TimeHandler.getZonedDateTimeUTC(LocalDateTime.of(date, endTime));

        // check that the appointment does not overlap any non-business hours
        if (!isDuringBusinessHours(TimeHandler.utcToLocalOffset(startZDT_utc), TimeHandler.utcToLocalOffset(endZDT_utc))) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid time entry");
            String message = "Appointment must fall with business hours of 8am-10pm EST.\n"
                    + "In your time zone, this equates to: "
                    + businessHoursStart.format(TimeHandler.dateTimeFormat)
                    + " to "
                    + businessHoursEnd.format(TimeHandler.dateTimeFormat);

            error.setContentText(message);
            error.showAndWait();
            return;
        }

        // check for appt overlap for that customer
        var overlappingAppts = DBAppointments.getAll();
        Predicate<Appointment> overlaps = i -> {
            return i.getCustomerId() == customer.getId()
                    && i.getStartZDT_utc().isBefore(endZDT_utc)
                    && startZDT_utc.isBefore(i.getEndZDT_utc())
                    && id != i.getId();
        };
        overlappingAppts.setPredicate(overlaps);

        if (!overlappingAppts.isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Appointment overlap");
            String overlap = "The selected times overlaps an existing appointment for this day/customer:";
            for (Appointment a : overlappingAppts) {
                overlap += "\nAppt ID: " + a.getId()
                        + " from " + TimeHandler.utcToLocalOffset(a.getStartZDT_utc()).format(TimeHandler.timeFormat)
                        + " to " + TimeHandler.utcToLocalOffset(a.getEndZDT_utc()).format(TimeHandler.timeFormat);
            }
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
