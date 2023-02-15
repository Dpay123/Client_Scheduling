package dp.wgu.softwareii.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class DashboardPageController extends BaseController{
    @javafx.fxml.FXML
    private Label dashTime;
    @javafx.fxml.FXML
    private Label dashGreeting;
    @javafx.fxml.FXML
    private Button logoutBtn;
    @javafx.fxml.FXML
    private Button exitBtn;
    @javafx.fxml.FXML
    private TableView customerTV;
    @javafx.fxml.FXML
    private TableColumn partIdCol;
    @javafx.fxml.FXML
    private TableColumn partNameCol;
    @javafx.fxml.FXML
    private TableColumn partInvCol;
    @javafx.fxml.FXML
    private TableColumn partPriceCol;
    @javafx.fxml.FXML
    private Button custAddBtn;
    @javafx.fxml.FXML
    private Button custUpdateBtn;
    @javafx.fxml.FXML
    private Button custDeleteBtn;
    @javafx.fxml.FXML
    private RadioButton apptFilterAll;
    @javafx.fxml.FXML
    private ToggleGroup apptFilter;
    @javafx.fxml.FXML
    private RadioButton apptFilterWeek;
    @javafx.fxml.FXML
    private RadioButton apptFilterMonthRb;
    @javafx.fxml.FXML
    private TableView productTableView;
    @javafx.fxml.FXML
    private TableColumn productIdCol;
    @javafx.fxml.FXML
    private TableColumn productNameCol;
    @javafx.fxml.FXML
    private TableColumn productInvCol;
    @javafx.fxml.FXML
    private TableColumn productPriceCol;
    @javafx.fxml.FXML
    private Button apptAddBtn;
    @javafx.fxml.FXML
    private Button apptUpdateBtn;
    @javafx.fxml.FXML
    private Button apptDeleteBtn;

    @javafx.fxml.FXML
    public void OnLogoutClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnExitClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnCustAddClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnCustUpdateClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnCustDeleteClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnApptFilterAllClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnApptFilterWeekClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnApptFilterMonthClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnApptAddClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnApptUpdateClick(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void OnApptDeleteClick(ActionEvent actionEvent) {
    }
}
