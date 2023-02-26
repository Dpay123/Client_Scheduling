package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.utilities.TimeHandler;
import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.dbAccess.DBContacts;
import dp.wgu.softwareii.dbAccess.DBCustomers;
import dp.wgu.softwareii.dbAccess.DBUsers;
import dp.wgu.softwareii.model.*;
import dp.wgu.softwareii.utilities.Validate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.*;
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

    /**A list of Users for the combo box*/
    ObservableList<User> users;

    /**Used to validate if appt falls within business hours*/
    private static ZonedDateTime businessHoursStart;

    /**Used to validate if appt falls within business hours*/
    private static ZonedDateTime businessHoursEnd;

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

    /**Combo box for user to select contact*/
    @FXML
    private ComboBox contactCB;

    /**Field for user to input start time*/
    @FXML
    private TextField startTF;

    /**Field for user to input end time*/
    @FXML
    private TextField endTF;

    /**DatePick for user to select appt start date*/
    @FXML
    private DatePicker startDatePick;

    /**DatePick for user to select appt end date*/
    @FXML
    private DatePicker endDatePick;

    /**Combobox for selection of assigned user*/
    @FXML
    private ComboBox userCB;

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
        users = DBUsers.getAll();
        userCB.setItems(users);

        // set the combo boxes to current appt data
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
        for (User user : users) {
            if (user.getId() == appt.getUserId()) userCB.setValue(user);
        }
        // convert UTC-offset ZDT to the users local time zone
        ZonedDateTime startZDT_local = TimeHandler.utcToLocalOffset(appt.getStartZDT_utc());
        ZonedDateTime endZDT_local = TimeHandler.utcToLocalOffset(appt.getEndZDT_utc());

        // set datePicker values to the local date of the user
        startDatePick.setValue(startZDT_local.toLocalDate());
        endDatePick.setValue(endZDT_local.toLocalDate());

        // set start/end time, formatted, to the local time of the user
        LocalTime startTime = startZDT_local.toLocalTime();
        LocalTime endTime = endZDT_local.toLocalTime();
        startTF.setText(startTime.format(TimeHandler.timeFormat));
        endTF.setText(endTime.format(TimeHandler.timeFormat));
    }

    /**
     * Determine whether an appointment falls within business hours.
     * Business hours are a known period of time in a known zone, and are converted
     * to the zone of the user to check if the appointment times are valid.
     * @param apptStart the start of the appointment, local to the zone of the user
     * @param apptEnd the end of the appointment, local to the zone of the user
     * @return true if the appointment is within business hours, else false
     */
    public static boolean isDuringBusinessHours(ZonedDateTime apptStart, ZonedDateTime apptEnd) {
        // determine the zone of the user
        ZoneId appointmentZone = apptStart.getZone();
        // retrieve the known zone of the business for time conversion
        ZoneId businessZone = TimeHandler.businessZone;
        // determine the opening of the business relative to the user zone
        businessHoursStart = ZonedDateTime.of(
                apptStart.toLocalDate(),
                LocalTime.of(TimeHandler.openHour, 0),
                businessZone
        ).withZoneSameInstant(appointmentZone);
        // determine the closing of the business by incrementing the business start time by the # of open hours
        businessHoursEnd = businessHoursStart.plusHours(TimeHandler.closeHour - TimeHandler.openHour);
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
        if (!Validate.isValidText(title, 50)
                || !Validate.isValidText(description, 50)
                || !Validate.isValidText(location, 50)
        )
        {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid entry");
            error.setContentText("Must provide a title, description, and location between 1-50 characters");
            error.showAndWait();
            return;
        }
        // retrieve and validate input selection data
        Type type = (Type)typeCB.getSelectionModel().getSelectedItem();
        Customer customer = (Customer)customerCB.getSelectionModel().getSelectedItem();
        User assignedUser = (User)userCB.getSelectionModel().getSelectedItem();
        Contact contact = (Contact)contactCB.getSelectionModel().getSelectedItem();
        LocalDate startDate = startDatePick.getValue();
        LocalDate endDate = endDatePick.getValue();
        if (type == null
                || customer == null
                || contact == null
                || startDate == null
                || endDate == null
                || assignedUser == null)
        {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid selection");
            error.setContentText("Must select a type, customer, contact, dates, and user");
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
        // check formatting errors
        catch (DateTimeParseException e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid time format");
            error.setContentText("Must enter valid time formatted as HH:MM");
            error.showAndWait();
            return;
        }

        // build LocalDateTimes from valid date and valid times input
        ZonedDateTime startZDT_utc = TimeHandler.getZonedDateTimeUTC(LocalDateTime.of(startDate, startTime));
        ZonedDateTime endZDT_utc = TimeHandler.getZonedDateTimeUTC(LocalDateTime.of(endDate, endTime));

        // check that appointment end time > start time
        if (endZDT_utc.isBefore(startZDT_utc)) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid time entry");
            error.setContentText("End time cannot be <= Start time.");
            error.showAndWait();
            return;
        }

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
                assignedUser.getId(),
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


    /**
     * Auto select the end date as well when the start date is selected.
     * For the majority of the use cases, this will select the same day.
     * Users in offset time zones might have to manually select the end date as the following day.
     * @param actionEvent
     */
    @FXML
    public void OnStartDatePick(ActionEvent actionEvent) {
        endDatePick.setValue(startDatePick.getValue());
    }
}
