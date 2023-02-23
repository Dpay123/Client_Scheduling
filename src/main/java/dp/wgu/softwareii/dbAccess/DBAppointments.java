package dp.wgu.softwareii.dbAccess;

import dp.wgu.softwareii.database.JDBC;
import dp.wgu.softwareii.model.Appointment;
import dp.wgu.softwareii.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The class for handling access to the Appointments table in the db.
 */
public class DBAppointments {

    /**
     * Query the db for a list of all appointments and return the result set.
     * @return an ObservableList of appointments.
     */
    public static FilteredList<Appointment> getAll() {

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
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                // retrieve datetime from db as TimeStamp
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                // convert to localDateTime
                LocalDateTime startDT = start.toLocalDateTime();
                LocalDateTime endDT = end.toLocalDateTime();
                // create appointment obj
                Appointment a = new Appointment(id, title, description, location, type, startDT, endDT, custID, userID, contactID);
                aList.add(a);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        FilteredList<Appointment> filtered = new FilteredList<>(aList);
        return filtered;
    }

    /**
     * Query the db for a list of all appointments with a given contactId and return the result set.
     * @return an ObservableList of appointments.
     */
    public static ObservableList<Appointment> getContactAppointments(int contactId) {

        ObservableList<Appointment> aList = FXCollections.observableArrayList();

        try {
            // use a prepared statement to execute an sql query
            String sql = "SELECT * from appointments WHERE Contact_ID = " + contactId;
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // for every result in the result set, create an Appointment obj and add to list
            while (rs.next()) {
                int id = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                int custID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                // retrieve datetime from db as TimeStamp
                Timestamp start = rs.getTimestamp("Start");
                Timestamp end = rs.getTimestamp("End");
                // convert to localDateTime
                LocalDateTime startDT = start.toLocalDateTime();
                LocalDateTime endDT = end.toLocalDateTime();
                // create appointment obj
                Appointment a = new Appointment(id, title, description, location, type, startDT, endDT, custID, userID, contactID);
                aList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aList;
    }

    /**
     * Add an appt to the db.
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param custId
     * @param userId
     * @param contactId
     * @return
     */
    public static boolean addAppointment(String title,
                                         String description,
                                         String location,
                                         String type,
                                         LocalDateTime start,
                                         LocalDateTime end,
                                         int custId,
                                         int userId,
                                         int contactId) {
        String sql = "INSERT INTO appointments ("
                + "Title, "
                + "Description, "
                + "Location, "
                + "Type, "
                + "Start, "
                + "End, "
                + "Customer_ID, "
                + "User_ID, "
                + "Contact_ID) VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ? ,?)";

        try {
            // use a prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, custId);
            ps.setInt(8, userId);
            ps.setInt(9, contactId);
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


    /**
     * Update an appt in the db, return true if successful, else false.
     * @param id
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param custID
     * @param userID
     * @param contactID
     * @return
     */
    public static boolean updateAppointment(
            int id,
            String title,
            String description,
            String location,
            String type,
            LocalDateTime start,
            LocalDateTime end,
            int custID,
            int userID,
            int contactID)
    {
        String sql = "UPDATE appointments "
                + "SET Title = ?, "
                + "Description = ?, "
                + "Location = ?, "
                + "Type = ?, "
                + "Start = ?, "
                + "End = ?, "
                + "Customer_ID = ?, "
                + "User_ID = ?, "
                + "Contact_ID = ? WHERE Appointment_ID = " + id;

        try {
            // set parameters
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            ps.setTimestamp(5, Timestamp.valueOf(start));
            ps.setTimestamp(6, Timestamp.valueOf(end));
            ps.setInt(7, custID);
            ps.setInt(8, userID);
            ps.setInt(9, contactID);
            // execute
            ps.executeUpdate();
            System.out.println("Updated successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete all appointments from the db based upon a customer id, return true if success, else false.
     * @param customerID
     * @return
     */
    public static boolean delCustomerAppointments(int customerID) {
        String sql = "DELETE FROM appointments WHERE Customer_ID = " + customerID;

        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Deleted user appointments");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
