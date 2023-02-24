package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.Utilities.TimeHandler;
import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.model.Appointment;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AppointmentsPageController extends BaseController {

    /**the data set for the tableview.
     * Can be filtered by Predicate.*/
    private FilteredList<Appointment> appts;

    /**Tableview for displaying appts from db*/
    @FXML
    private TableView apptTV;

    /**Button for filtering appts by all*/
    @FXML
    private RadioButton apptFilterAll;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptTitleCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptDescriptionCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptLocationCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptContactCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptTypeCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn<Appointment, String> apptStartCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn<Appointment, String> apptEndCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptCustIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptUserIdCol;

    /**
     * Populates the appt data.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set the data source for the appts table
        appts = DBAppointments.getAll();
        apptTV.setItems(appts);
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptCustIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        apptUserIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        // show the UTC times as local user offset time
        apptStartCol.setCellValueFactory(utc -> {
            ZonedDateTime local = TimeHandler.utcToLocalOffset(utc.getValue().getStartZDT_utc());
            return new SimpleStringProperty(local.format(TimeHandler.dateTimeFormat));
        });
        apptEndCol.setCellValueFactory(utc -> {
            ZonedDateTime local = TimeHandler.utcToLocalOffset(utc.getValue().getEndZDT_utc());
            return new SimpleStringProperty(local.format(TimeHandler.dateTimeFormat));
        });

        // set default filter
        apptFilterAll.setSelected(true);
    }

    /**
     * Filter the Appointments table to select all
     * @param actionEvent
     */
    @FXML
    public void OnApptFilterAllClick(ActionEvent actionEvent) {
        // set the filter to null
        appts.setPredicate(null);
    }

    /**
     * Filter the Appointments table to select those within a week.
     * Uses a lambda expression to set a Predicate which is used to filter the results list.
     * @param actionEvent
     */
    @FXML
    public void OnApptFilterWeekClick(ActionEvent actionEvent) {
        // want to filter by any appt with a date that falls within 7 days of todays date
        Predicate<Appointment> withinWeek = i -> {
            int today = LocalDate.now().getDayOfYear();
            int apptDay = TimeHandler.utcToLocalOffset(i.getStartZDT_utc()).getDayOfYear();
            return apptDay >= today && apptDay <= today+6;
        };
        appts.setPredicate(withinWeek);
    }

    /**
     * Filter the Appointments table to select those within month
     * @param actionEvent
     */
    @FXML
    public void OnApptFilterMonthClick(ActionEvent actionEvent) {
        // want to filter by any appt with a date that falls within 7 days of todays date
        Predicate<Appointment> withinMonth = i -> {
            int thisMonth = LocalDate.now().getMonthValue();
            int apptMonth = TimeHandler.utcToLocalOffset(i.getStartZDT_utc()).getMonthValue();
            return thisMonth == apptMonth;
        };
        appts.setPredicate(withinMonth);
    }

    /**
     * Navigate to the Add Appointment page
     * @param actionEvent
     */
    @FXML
    public void OnApptAddClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("AddAppointmentPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Add Appointment");
        stage.show();
    }

    /**
     * Navigate to the Update Appointment page for a selected Appointment.
     * Sets the appt chosen as a static data member in the Update Appointment Menu Controller.
     * If an appt is not selected, displays an error box suggesting to choose an appt.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void OnApptUpdateClick(ActionEvent actionEvent) throws IOException{
        // retrieve selected appt
        Appointment appt = (Appointment)apptTV.getSelectionModel().getSelectedItem();

        // ensure appt is selected
        if (appt == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select an appointment to update.");
            error.setTitle("No Appointment selected");
            error.showAndWait();
            return;
        }

        // set appt in modify controller
        UpdateAppointmentPageController.appt = appt;

        // navigate to the update customer page
        Parent newScene = this.loadScene("UpdateAppointmentPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Update Appointment");
        stage.show();
    }

    /**
     * Delete an appointment.
     * User must confirm prior to deletion.
     * @param actionEvent
     */
    @FXML
    public void OnApptDeleteClick(ActionEvent actionEvent) {
        // retrieve selected appt
        Appointment appt = (Appointment)apptTV.getSelectionModel().getSelectedItem();

        // check for null
        if (appt == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select an appointment to delete");
            error.setTitle("No appointment selected");
            error.showAndWait();
            return;
        }
        // pop up box for confirmation of deletion
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?");
        confirm.setTitle("Confirm Deletion");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Attempt to delete the appt from db
            boolean deleted = DBAppointments.delAppointment(appt.getId());
            if (!deleted) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setContentText("Could not delete.");
                error.showAndWait();
            }
            else {
                // reset table data source
                appts = DBAppointments.getAll();
                apptTV.setItems(appts);
                apptFilterAll.setSelected(true);
                Alert success = new Alert(Alert.AlertType.INFORMATION, "Appointment deleted.");
                success.setTitle("Appointment deleted success");
                success.showAndWait();
            }
        }
    }

    /**
     * Return to Dashboard.
     * @param actionEvent
     */
    @FXML
    public void OnDashboardBtnClick(ActionEvent actionEvent) throws IOException {
        // return to Dashboard
        this.goToMainMenu(actionEvent);
    }
}
