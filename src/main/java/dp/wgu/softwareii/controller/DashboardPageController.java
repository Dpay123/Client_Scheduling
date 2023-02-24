package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.model.Appointment;
import dp.wgu.softwareii.model.User;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * The controller for the Dashboard Page.
 */
public class DashboardPageController extends BaseController{

    /**The user logged in.*/
    public static User user;

    /**Keep track if login or simple navigation to dash*/
    public static boolean uponLogin;

    /**Label for user local time display*/
    @FXML
    private Label dashTime;

    /**Label for greeting/displaying user name*/
    @FXML
    private Label dashGreeting;

    /**Label for notifiying of upcoming appointments*/
    @FXML
    private Label headsUpLabel;

    /**
     * Perform actions upon page initialization.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashGreeting.setText("Logged in as " + user);
        dashTime.setText("TimeZone: " + ZoneId.systemDefault().getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        ZoneId userZoneID = ZoneId.systemDefault();

        FilteredList<Appointment> appts = DBAppointments.getAll();

        // retrieve the current time and check to see if any appts are within 15 minutes
        Predicate<Appointment> within15Mins = i -> {
            ZonedDateTime nowLocal = ZonedDateTime.of(LocalDateTime.now(), userZoneID);
            // retrieve appt time (UTC)
            ZonedDateTime apptUTC = i.getStartDateTime();
            // convert to user timezone
            ZonedDateTime apptLocal = ZonedDateTime.ofInstant(apptUTC.toInstant(), userZoneID);
            // DEBUG
            System.out.println("\nDash: Checking for 15 min appts...");
            System.out.println("Local time in user zone: " + nowLocal + " verification: zone" + nowLocal.getZone());
            System.out.println("appt time in UTC from db: " + apptUTC + " verification: zone" + apptUTC.getZone());
            System.out.println("appt time in user zone: " + apptLocal + " verification: zone" + apptLocal.getZone() + '\n');
            return (apptLocal.isEqual(nowLocal) || apptLocal.isAfter(nowLocal)) && apptLocal.isBefore(nowLocal.plusMinutes(15));
        };
        appts.setPredicate(within15Mins);
        if (!appts.isEmpty()) {
            headsUpLabel.setText("Appointments soon!");
            // show pop up only upon login
            if (uponLogin) {
                String headsUp = "";
                for (Appointment a : appts) {
                    headsUp += "Appt ID: " + a.getId() + " --- Starts: " + a.getStart() + '\n';
                }
                Alert warning = new Alert(Alert.AlertType.INFORMATION);
                warning.setTitle("Appointments soon!");
                warning.setHeaderText("Upcoming within the next 15 minutes:");
                warning.setContentText(headsUp);
                warning.showAndWait();
            }

        }
        else {
            headsUpLabel.setText("No upcoming appointments");
        }
        uponLogin = false;
    }

    /**
     * Logout and return to the Login Screen.
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void OnLogoutClick(ActionEvent actionEvent) throws IOException{
        // navigate to the Login Page
        Parent newScene = this.loadScene("LoginPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Login Page");
        stage.show();
    }

    /**
     Exits the program.
     @param actionEvent the event that triggers the method.
     */
    @FXML
    public void OnExitClick(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Navigate to the Appointments page.
     * @param actionEvent
     */
    @FXML
    public void OnAppointmentsBtnClick(ActionEvent actionEvent) throws  IOException{
        Parent newScene = this.loadScene("AppointmentsPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Appointments");
        stage.show();
    }

    /**
     * Navigate to the Customers page.
     * @param actionEvent
     */
    @FXML
    public void OnCustomersBtnClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("CustomersPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Customers");
        stage.show();
    }

    /**
     * Navigate to the Reports page.
     * @param actionEvent
     */
    @FXML
    public void OnReportsBtnClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("ReportsPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Reports/Metrics");
        stage.show();
    }
}
