package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBCountries;
import dp.wgu.softwareii.dbAccess.DBCustomers;
import dp.wgu.softwareii.dbAccess.DBDivisions;
import dp.wgu.softwareii.model.Country;
import dp.wgu.softwareii.model.Division;
import dp.wgu.softwareii.utilities.Validate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller for the Add Customer Page.
 */
public class AddCustomerPageController extends BaseController {

    /**A list of countries to populate the combo box*/
    ObservableList<Country> countries;

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

    /**Initializes the combo box data set*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countries = DBCountries.getAll();
        countryComboBox.setItems(countries);
    }

    /**
     * Retrieve the form data and save as a new Customer.
     * No data may be blank/null and must be formatted correctly, or else an error message will display
     * and the customer will not be saved. Return to Customers page after successful save, otherwise stay on page.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        // retrieve and validate input text data
        String name = nameField.getText();
        String address = addressField.getText();
        String postal = postalField.getText();
        String phone = phoneField.getText();
        if (
                !Validate.isValidText(name, 50)
                || !Validate.isValidText(address, 100)
                || !Validate.isValidText(postal, 50)
                || !Validate.isValidText(phone, 50)
        )
        {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid entry");
            error.setContentText("Must provide a name, postal code, and phone within 50 characters "
                    + "and an address within 100 characters.");
            error.showAndWait();
            return;
        }
        // retrieve and validate the division data
        var division = (Division)stateComboBox.getSelectionModel().getSelectedItem();
        if (division == null) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Invalid entry");
            error.setContentText("Please choose a country and division");
            error.showAndWait();
            return;
        }
        // attempt to add the customer to the db
        boolean added = DBCustomers.addCustomer(name, address, postal, phone, division.getId());
        if (!added) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setContentText("Could not add.");
            error.showAndWait();
        }
        else {
            // return to customers page
            Parent newScene = this.loadScene("CustomersPage");
            Stage stage = this.getStageWithSetScene(actionEvent, newScene);
            stage.setTitle("Customers");
            stage.show();
        }
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

    /**Upon selection of countryComboBox, set the data for the division combo box.
     * @param actionEvent
     */
    @FXML
    public void OnCountrySelect(ActionEvent actionEvent) {
        Country countryToChoose = (Country)countryComboBox.getSelectionModel().getSelectedItem();
        ObservableList<Division> divisions = DBDivisions.getAll(countryToChoose.getId());
        stateComboBox.setItems(divisions);
    }
}
