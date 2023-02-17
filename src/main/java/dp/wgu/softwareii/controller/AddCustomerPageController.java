package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the Add Customer Page.
 */
public class AddCustomerPageController extends BaseController {

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
     * Retrieve the form data and save as a new Customer.
     * Return to Customers page.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        // TODO: save customer
        // return to customers page
        Parent newScene = this.loadScene("CustomersPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Customers");
        stage.show();
    }

    /**
     * Cancel and return to Customers page.
     * @param actionEvent
     */
    @FXML
    public void OnCancelClick(ActionEvent actionEvent) throws IOException{
        // return to customers page
        Parent newScene = this.loadScene("CustomersPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Customers");
        stage.show();
    }
}
