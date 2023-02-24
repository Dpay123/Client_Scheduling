package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.dbAccess.DBContacts;
import dp.wgu.softwareii.model.Appointment;
import dp.wgu.softwareii.model.Contact;
import dp.wgu.softwareii.model.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**The controller for the ReportsPage view*/
public class ReportsPageController extends BaseController{

    /**The list of appts to filter*/
    private static FilteredList<Appointment> appts;

    /**The list of contacts to select*/
    private static ObservableList<Contact> contacts;

    /**The list of types to select*/
    private static ObservableList<String> typeList;

    /**The comboBox for filtering appts by month*/
    @FXML
    private ComboBox monthCB;

    /**The comboBox for filtering appts by type*/
    @FXML
    private ComboBox typeCB;

    /**The label for displaying appt num metric*/
    @FXML
    private Label apptNumLbl;

    /**The label for displaying month metric*/
    @FXML
    private Label monthLbl;

    /**The label for displaying contact name metric*/
    @FXML
    private Label contactLbl;

    /**The comboBox for choosing a contact*/
    @FXML
    private ComboBox contactCB;

    /**Initializes the data sets*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // total appt metrics
        appts = DBAppointments.getAll();
        apptNumLbl.setText(String.valueOf(appts.size()));
        ObservableList<String> months = FXCollections.observableArrayList(
                "All", "January", "February", "March",
                "April", "May", "June", "July", "August",
                "September", "October", "November", "December"
        );
        monthCB.setItems(months);
        monthCB.getSelectionModel().selectFirst();

        // Set type cb as list of strings of enum Type and also "All"
        typeList = FXCollections.observableArrayList(Arrays.asList("All"));
        for (Type t : Type.values()) typeList.add(t.toString());
        typeCB.setItems(typeList);
        typeCB.getSelectionModel().selectFirst();

        // busiest month metric - if tie, report the first busiest month
        int monthNum = getBusiestMonth(appts);
        monthLbl.setText(months.get(monthNum));

        // populate contact cb for generating schedule
        contacts = DBContacts.getAll();
        contactCB.setItems(contacts);

        // busiest contact metric - if tie, report the first busiest contact
        int contactID = getBusiestContact(appts);
        for (Contact c : contacts) {
            if (c.getId() == contactID) contactLbl.setText(c.getName());
        }
    }

    /**
     * From a list of appts, return the busiest contact id.
     * @param appts
     * @return
     */
    private int getBusiestContact(FilteredList<Appointment> appts) {
        // iterate each appt and map contactID to appt count
        var contactMap = new HashMap<Integer, Integer>();
        for (Appointment a : appts) {
            int contactID = a.getContactId();
            if (!contactMap.containsKey(contactID)) contactMap.put(contactID, 1);
            else contactMap.put(contactID, contactMap.get(contactID) + 1);
        }
        // find the contact with the max count
        int ansID = -1;
        int maxCount = 0;
        for (Integer id : contactMap.keySet()) {
            int count = contactMap.get(id);
            if (count > maxCount) {
                ansID = id;
                maxCount = count;
            }
        }

        return ansID;
    }

    /**
     * From a list of appts, return the busiest month number.
     * @param appts
     * @return
     */
    private int getBusiestMonth(FilteredList<Appointment> appts) {
        // array to count month totals
        int[] months = new int[13];
        // parse appt month and increment in array
        for (Appointment a : appts) {
            int monthNum = a.getStartZDT_utc().getMonthValue();
            months[monthNum]++;
        }
        int ans = 0;
        // iterate array to find and return the monthNum with the most appointments
        for (int i = 1; i < months.length; i++) {
            if (months[i] > months[ans]) ans = i;
        }
        return ans;
    }

    /**
     * Filter the appts list.
     * @param actionEvent
     */
    @FXML
    public void OnFilterClick(ActionEvent actionEvent) {
        int monthNum = monthCB.getSelectionModel().getSelectedIndex();
        String typeFilter = (String)typeCB.getSelectionModel().getSelectedItem();

        // filter for month selection
        Predicate<Appointment> month = i -> {
            return i.getStartZDT_utc().getMonthValue() == monthNum;
        };
        // filter for type selection
        Predicate<Appointment> type = i -> {
            return i.getType().equals(typeFilter);
        };

        if (monthNum == 0 && typeFilter.equals("All")) appts.setPredicate(null);
        // if only month filtered
        else if (typeFilter.equals("All")) {
            appts.setPredicate(month);
        }
        // if only type filtered
        else if (monthNum == 0) {
            appts.setPredicate(type);
        }
        // filter by both
        else {
            appts.setPredicate(type.and(month));
        }
        // count the filtered data
        apptNumLbl.setText(String.valueOf(appts.size()));
    }

    /**
     * Navigates to the ContactSchedulePage.
     * @param actionEvent
     */
    @FXML
    public void OnCreateClick(ActionEvent actionEvent) throws IOException {
        // retrieve the selected contact and send to page
        Contact contact = (Contact)contactCB.getSelectionModel().getSelectedItem();
        ContactSchedulePageController.contact = contact;
        // navigate to the contact schedule page
        Parent newScene = this.loadScene("ContactSchedulePage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Contact Schedule Snapshot");
        stage.show();
    }

    /**
     * Return to the Dashboard.
     * @param actionEvent
     */
    @FXML
    public void OnDashClick(ActionEvent actionEvent) throws IOException {
        this.goToMainMenu(actionEvent);
    }
}
