package dp.wgu.softwareii.controller;

import dp.wgu.softwareii.model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class ContactSchedulePageController extends BaseController{

    /**Header label to indicate who the snapshot is for*/
    @FXML
    private Label headerLbl;

    /**Tableview for displaying appts from db*/
    @FXML
    private TableView apptTV;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptTitleCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptDescriptionCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptLocationCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptTypeCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn<Appointment, LocalDateTime> apptStartCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptEndCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptCustIDCol;

    /**Customer TableView column*/
    @FXML
    private TableColumn apptUserIdCol;

    /**
     * Return to the Reports page.
     * @param actionEvent
     */
    @FXML
    public void OnReportsClick(ActionEvent actionEvent) throws IOException {
        Parent newScene = this.loadScene("ReportsPage");
        Stage stage = this.getStageWithSetScene(actionEvent, newScene);
        stage.setTitle("Reports");
        stage.show();
    }

    /**
     * Return to the Dashboard.
     * @param actionEvent
     */
    @FXML
    public void OnDashboardClick(ActionEvent actionEvent) throws IOException {
        this.goToMainMenu(actionEvent);
    }
}
