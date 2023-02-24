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
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * The controller for the Add Appt Page.
 */
public class AddAppointmentPageController extends BaseController{

    /**A list of Customers for the combo box*/
    ObservableList<Customer> customers;

    /**A list of Contacts for the combo box*/
    ObservableList<Contact> contacts;

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
     * Initialize the combo boxes.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customers = DBCustomers.getAll();
        customerCB.setItems(customers);

        contacts = DBContacts.getAll();
        contactCB.setItems(contacts);

        typeCB.getItems().setAll(Arrays.asList(Type.values()));
    }

    /**
     * Retrieve the form data and save as a new Appointment.
     * Return to Appointments after saving.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        // retrieve input data
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
        ZonedDateTime startZDT_utc = TimeHandler.getZonedDateTimeUTC(LocalDateTime.of(date, startTime));
        ZonedDateTime endZDT_utc = TimeHandler.getZonedDateTimeUTC(LocalDateTime.of(date, endTime));
        LocalDateTime startLDT =  LocalDateTime.of(date, startTime);
        LocalDateTime endLDT = LocalDateTime.of(date, endTime);
        // build ZoneDateTimes from above using user zoneID
        //ZoneId userZoneID = ZoneId.systemDefault();
        //ZoneId utcZoneID = ZoneId.of("UTC");
        //ZonedDateTime startZDT = ZonedDateTime.of(startLDT, userZoneID);
        //ZonedDateTime endZDT = ZonedDateTime.of(endLDT, userZoneID);
        // convert to UTC offset
        //ZonedDateTime startZDT_utc = ZonedDateTime.ofInstant(startZDT.toInstant(), utcZoneID);
        //ZonedDateTime endZDT_utc = ZonedDateTime.ofInstant(endZDT.toInstant(), utcZoneID);

        // DEBUG
        System.out.println("\nAddPage inputs.....");
        System.out.println("Parsed Locals-- start: " + startLDT + "   Parsed end: " + endLDT);
        System.out.println("Zoned-> UTC  -- start: " + startZDT_utc + " utc end: " + endZDT_utc + '\n');

        // check for appt overlap for that customer
        var overlappingAppts = DBAppointments.getAll();
        Predicate<Appointment> overlaps = i -> {
            return i.getCustomerId() == customer.getId()
                    && i.getStartDateTime().isBefore(endZDT_utc)
                    && startZDT_utc.isBefore(i.getEndDateTime());
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

        // attempt to save the appointment
        boolean saved = DBAppointments.addAppointment(
                title,
                description,
                location,
                type.toString(),
                startZDT_utc,
                endZDT_utc,
                customer.getId(),
                user.getId(),
                contact.getId());
        if (!saved) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setContentText("Could not save.");
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
     * Cancel and return to Appointments page.
     * @param actionEvent
     */
    @FXML
    public void OnCancelClick(ActionEvent actionEvent) throws IOException{
        // return to appointments page
        Parent newScene = this.loadScene("AppointmentsPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Appointments");
        stage.show();
    }
}
