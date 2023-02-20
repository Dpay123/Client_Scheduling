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

    /**
     * Create an Appointment record in the db.
     * @param title
     * @param location
     * @param description
     * @param type
     * @param custId
     * @param userId
     * @param contactId
     */
    public static boolean addAppointment(String title,
                                         String location,
                                         String description,
                                         String type,
                                         int custId,
                                         int userId,
                                         int contactId) {
        String sql = "INSERT INTO appointments ("
                + "Title, "
                + "Description, "
                + "Location, "
                + "Type, "
                + "Customer_ID, "
                + "User_ID, "
                + "Contact_ID) VALUES ("
                + "?, ?, ?, ?, ?, ? ,?)";

        try {
            // use a prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, location);
            ps.setString(3, description);
            ps.setString(4, type);
            ps.setInt(5, custId);
            ps.setInt(6, userId);
            ps.setInt(7, contactId);
            // execute
            ps.executeUpdate();
            System.out.println("Added successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete an appt record in the db based upon id.
     * @param id
     * @return true if succesful else false
     */
    public static boolean delAppointment(int id) {

        String sql = "DELETE FROM appointments WHERE Appointment_ID = " + id;

        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Removed succesfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
