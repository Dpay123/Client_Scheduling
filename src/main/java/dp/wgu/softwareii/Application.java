package dp.wgu.softwareii;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dp.wgu.softwareii.database.JDBC;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The main application file.
 */
public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/LoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 300);
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    /**Main method for entry into the application*/
    public static void main(String[] args) {

        // open the connection to the db
        JDBC.openConnection();
        // run the application
        launch();
        // close the connection upon termination of the program
        JDBC.closeConnection();
    }
}