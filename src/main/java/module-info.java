module dp.wgu.softwareii {
    requires javafx.controls;
    requires javafx.fxml;


    opens dp.wgu.softwareii to javafx.fxml;
    exports dp.wgu.softwareii;
}