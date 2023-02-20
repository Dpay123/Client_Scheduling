package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBAppointments;
import dp.wgu.softwareii.dbAccess.DBCustomers;
import dp.wgu.softwareii.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomersPageController extends BaseController {

    /**Tableview for displaying customers from db*/
    @FXML
    private TableView customerTV;

    /**Customer TableView column*/
    @FXML
    private TableColumn custIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn custNameCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn custPostCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn custPhoneCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn custDivisionCol;

    /**Button for adding customer*/
    @FXML
    private Button custAddBtn;

    /**Button for updating customer*/
    @FXML
    private Button custUpdateBtn;

    /**Button for deleting customer*/
    @FXML
    private Button custDeleteBtn;

    /**Button for navigating to Dashboard*/
    @FXML
    private Button dashboardBtn;

    /**
     * Populates the customer data.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set the data source for the customers table
        customerTV.setItems(DBCustomers.getAll());
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        custPostCol.setCellValueFactory(new PropertyValueFactory<>("postcode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        custDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));
    }

    /**
     * Navigate to the Add Customer page
     * @param actionEvent
     */
    @FXML
    public void OnCustAddClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("AddCustomerPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Add Customer");
        stage.show();
    }

    /**
     * Navigate to the Update Customer page for a selected customer.
     * Sets the selected customer as a static data member in the Update Customer page controller.
     * If a cust is not selected, displays an error box suggesting to choose an appt
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void OnCustUpdateClick(ActionEvent actionEvent) throws IOException {
        // retrieve selected customer
        Customer cust = (Customer)customerTV.getSelectionModel().getSelectedItem();

        // ensure cust is selected
        if (cust == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select a customer to update.");
            error.setTitle("No Customer selected");
            error.showAndWait();
            return;
        }

        // set cust in modify controller
        UpdateCustomerPageController.customer = cust;

        // navigate to the update customer page
        Parent newScene = this.loadScene("UpdateCustomerPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Update Customer");
        stage.show();
    }

    /**
     * Delete a selected customer.
     * User must confirm prior to deletion.
     * All customer appointments must be deleted prior to deletion
     * @param actionEvent
     */
    @FXML
    public void OnCustDeleteClick(ActionEvent actionEvent) {
        // retrieve selected customer
        Customer customer = (Customer)customerTV.getSelectionModel().getSelectedItem();

        // check for null
        if (customer == null) {
            Alert error = new Alert(Alert.AlertType.ERROR, "Please select a customer to delete");
            error.setTitle("No customer selected");
            error.showAndWait();
            return;
        }

        // TODO: customer cannot be deleted if it has associated appointments

        // pop up box to confirm deletion
        String message = "Are you sure you want to delete this customer? Any associated appointments will be deleted!!";
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, message);
        confirm.setTitle("Confirm Deletion");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {

            // Attempt to delete appointments associated with the  customer
            boolean deletedCustomerAppointments = DBAppointments.delCustomerAppointments(customer.getId());
            if (!deletedCustomerAppointments) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setContentText("Could not delete the required appointments.");
                error.showAndWait();
            }
            else {
                // Attempt to delete the customer from db
                boolean deleted = DBCustomers.delCustomer(customer.getId());
                if (!deleted) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Error");
                    error.setContentText("Could not delete.");
                    error.showAndWait();
                }
                else {
                    customerTV.getItems().remove(customer);
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "Customer deleted.");
                    success.setTitle("Customer deleted success");
                    success.showAndWait();
                }
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
