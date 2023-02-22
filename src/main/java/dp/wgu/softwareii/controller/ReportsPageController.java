package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBCountries;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**The controller for the ReportsPage view*/
public class ReportsPageController extends BaseController{

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

    /**Initializes the combo box data set*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Navigates to the ContactSchedulePage.
     * @param actionEvent
     */
    @FXML
    public void OnCreateClick(ActionEvent actionEvent) {
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
