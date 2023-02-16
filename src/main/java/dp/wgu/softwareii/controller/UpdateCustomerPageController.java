package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;

/**
 * The controller for the Update Customer Page.
 */
public class UpdateCustomerPageController extends BaseController{

    /**Field for user to input name*/
    @FXML
    private TextField nameField;

    /**Field for user to input address*/
    @FXML
    private TextField addressField;

    /**Field for user to input postal code*/
    @FXML
    private TextField postalField;

    /**Field for user to input phone num*/
    @FXML
    private TextField phoneField;

    /**Combo box for user to select country*/
    @FXML
    private ComboBox countryComboBox;

    /**Combo box for user to select country*/
    @FXML
    private ComboBox stateComboBox;

    /**Button to add customer*/
    @FXML
    private Button AddButton;

    /**Button to cancel adding the customer*/
    @FXML
    private Button CancelBtn;

    /**
     * Retrieve the form data and update the Customer.
     * Return to Dashboard after saving customer.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        // TODO: update current Customer obj, save to db
        // return to Dashboard
        this.goToMainMenu(actionEvent);
    }

    /**
     * Cancel and return to Dashboard.
     * @param actionEvent
     */
    @FXML
    public void OnCancelClick(ActionEvent actionEvent) throws IOException {
        // return to Dashboard
        this.goToMainMenu(actionEvent);
    }
}
