package dp.wgu.softwareii.dbAccess;

import dp.wgu.softwareii.database.JDBC;
import dp.wgu.softwareii.model.Appointment;
import dp.wgu.softwareii.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class for handling access to the Appointments table in the db.
 */
public class DBAppointments {

    /**
     * Query the db for a list of all appointments and return the result set.
     * @return an ObservableList of appointments.
     */
    public static ObservableList<Appointment> getAll() {

        ObservableList<Appointment> aList = FXCollections.observableArrayList();

        try {
            // use a prepared statement to execute an sql query
            String sql = "SELECT * from appointments";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // for every result in the result set, create an Appointment obj and add to list
            while (rs.next()) {
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                String start = rs.getString("Start");
                String end = rs.getString("End");
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointment a = new Appointment(id, title, description, location, type, start, end, custID, userID, contactID);
                aList.add(a);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return aList;
    }

}
