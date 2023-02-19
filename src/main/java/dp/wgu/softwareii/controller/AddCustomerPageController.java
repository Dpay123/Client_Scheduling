package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBCountries;
import dp.wgu.softwareii.dbAccess.DBCustomers;
import dp.wgu.softwareii.dbAccess.DBDivisions;
import dp.wgu.softwareii.model.Country;
import dp.wgu.softwareii.model.Customer;
import dp.wgu.softwareii.model.Division;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLOutput;
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

    /**Button to add customer*/
    @FXML
    private Button AddButton;

    /**Button to cancel adding the customer*/
    @FXML
    private Button CancelBtn;

    /**Initializes the combo box data set*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countries = DBCountries.getAll();
        countryComboBox.setItems(countries);
    }

    /**
     * Retrieve the form data and save as a new Customer.
     * Return to Customers page.
     * @param actionEvent
     */
    @FXML
    public void OnSaveClick(ActionEvent actionEvent) throws IOException {
        // TODO: save customer
        String name = nameField.getText();
        String address = addressField.getText();
        String postal = postalField.getText();
        String phone = phoneField.getText();
        var division = (Division)stateComboBox.getSelectionModel().getSelectedItem();
        DBCustomers.addCustomer(name, address, postal, phone, division.getId());

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
