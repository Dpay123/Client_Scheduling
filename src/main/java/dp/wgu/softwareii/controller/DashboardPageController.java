package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.utilities.LogActivity;
import dp.wgu.softwareii.utilities.TimeHandler;
import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.model.Appointment;
import dp.wgu.softwareii.model.User;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
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
     * Perform actions upon page initialization including setting the dash greeting and timezone.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashGreeting.setText("Logged in as " + user);
        dashTime.setText("TimeZone: " + ZoneId.systemDefault().toString());

        // get a list of appts to check for upcoming
        FilteredList<Appointment> appts = DBAppointments.getAll();

        // retrieve the current time and check to see if any appts are within 15 minutes
        Predicate<Appointment> within15Mins = i -> {
            ZonedDateTime nowLocal = ZonedDateTime.now();
            // retrieve appt time (UTC) and convert to user timezone
            ZonedDateTime apptLocal = TimeHandler.utcToLocalOffset(i.getStartZDT_utc());
            return (apptLocal.isEqual(nowLocal) || apptLocal.isAfter(nowLocal)) && apptLocal.isBefore(nowLocal.plusMinutes(15));
        };
        // filter for upcoming appts
        appts.setPredicate(within15Mins);

        if (!appts.isEmpty()) {
            headsUpLabel.setText("Appointments soon!");
            headsUpLabel.setTextFill(Color.RED);
            headsUpLabel.setStyle("-fx-font-weight: bold; -fx-font-style: normal");
            // show pop up only upon login
            if (uponLogin) {
                String headsUp = "";
                for (Appointment a : appts) {
                    headsUp += "Appt ID: " + a.getId()
                            + " --- Starts: " + TimeHandler.utcToLocalOffset(a.getStartZDT_utc()).toLocalTime() + '\n';
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
            headsUpLabel.setTextFill(Color.BLACK);
            headsUpLabel.setStyle("-fx-font-weight: normal; -fx-font-style: italic;");
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
        // record logout
        LogActivity.logout(user);
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
