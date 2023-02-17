package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.dbAccess.DBCustomers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
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
     * Navigate to the Update Customer page for a selected customer
     * @param actionEvent
     */
    @FXML
    public void OnCustUpdateClick(ActionEvent actionEvent) throws IOException {
        // TODO: retrieve the selected customer and send to the update page
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
        // TODO: require confirmation for deletion
        // TODO: check for all deleted appts prior to customer deletion
        // TODO: delete the customer
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
