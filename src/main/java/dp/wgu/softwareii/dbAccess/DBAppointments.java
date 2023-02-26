package dp.wgu.softwareii.dbAccess;

import dp.wgu.softwareii.utilities.TimeHandler;
import dp.wgu.softwareii.controller.DashboardPageController;
import dp.wgu.softwareii.database.JDBC;
import dp.wgu.softwareii.model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * The class for handling access to the Appointments table in the db.
 */
public class DBAppointments {

    private static ZoneId utcZoneId = ZoneId.of("UTC");

    /**
     * Query the db for a list of all appointments and return the result set.
     * @return a filteredList of appointments.
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
                // retrieve datetime from db as LocalDateTime
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                // convert to ZonedDateTime
                ZonedDateTime startZDT = ZonedDateTime.of(start, utcZoneId);
                ZonedDateTime endZDT = ZonedDateTime.of(end, utcZoneId);
                // create appointment obj
                Appointment a = new Appointment(id, title, description, location, type, startZDT, endZDT, custID, userID, contactID);
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
                // retrieve datetime from db as LocalDateTime
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                // convert to ZonedDateTime
                ZonedDateTime startZDT = ZonedDateTime.of(start, utcZoneId);
                ZonedDateTime endZDT = ZonedDateTime.of(end, utcZoneId);
                // create appointment obj
                Appointment a = new Appointment(id, title, description, location, type, startZDT, endZDT, custID, userID, contactID);
                aList.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aList;
    }

    /**
     * Add an appt to the db, return true if successful, else false.
     * The ZonedDateTime should be passed in as UTC - offset
     * @param title
     * @param description
     * @param location
     * @param type
     * @param start
     * @param end
     * @param custId
     * @param assignedUserID
     * @param contactId
     * @return
     */
    public static boolean addAppointment(String title,
                                         String description,
                                         String location,
                                         String type,
                                         ZonedDateTime start,
                                         ZonedDateTime end,
                                         int custId,
                                         int assignedUserID,
                                         int contactId) {
        String sql = "INSERT INTO appointments ("
                + "Title, "
                + "Description, "
                + "Location, "
                + "Type, "
                + "Start, "
                + "End, "
                + "Create_Date, "
                + "Created_By, "
                + "Last_Update, "
                + "Last_Updated_By, "
                + "Customer_ID, "
                + "User_ID, "
                + "Contact_ID) VALUES ("
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";

        try {

            // use a prepared statement
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, type);
            Timestamp t1 = Timestamp.valueOf(start.toLocalDateTime());
            Timestamp t2 = Timestamp.valueOf(end.toLocalDateTime());
            Timestamp t3 = Timestamp.valueOf(TimeHandler.getZonedDateTimeUTC(LocalDateTime.now()).toLocalDateTime());
            ps.setTimestamp(5, t1);
            ps.setTimestamp(6, t2);
            ps.setTimestamp(7, t3);
            ps.setString(8, DashboardPageController.user.getName());
            ps.setTimestamp(9, t3);
            ps.setString(10, DashboardPageController.user.getName());
            ps.setInt(11, custId);
            ps.setInt(12, assignedUserID);
            ps.setInt(13, contactId);
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
     * Update an appt in the db, return true if successful, else false.
     * The start and end times should be passed in as UTC-offset ZDT
     * @param id
     * @param title
     * @param description
     * @param location
     * @param type
     * @param startZDT_utc
     * @param endZDT_utc
     * @param custID
     * @param assignedUserID
     * @param contactID
     * @return
     */
    public static boolean updateAppointment(
            int id,
            String title,
            String description,
            String location,
            String type,
            ZonedDateTime startZDT_utc,
            ZonedDateTime endZDT_utc,
            int custID,
            int assignedUserID,
            int contactID)
    {
        String sql = "UPDATE appointments "
                + "SET Title = ?, "
                + "Description = ?, "
                + "Location = ?, "
                + "Type = ?, "
                + "Start = ?, "
                + "End = ?, "
                + "Last_Update = ?, "
                + "Last_Updated_By = ?, "
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
            Timestamp t1 = Timestamp.valueOf(startZDT_utc.toLocalDateTime());
            Timestamp t2 = Timestamp.valueOf(endZDT_utc.toLocalDateTime());
            ps.setTimestamp(5, t1);
            ps.setTimestamp(6, t2);
            Timestamp t3 = Timestamp.valueOf(TimeHandler.getZonedDateTimeUTC(LocalDateTime.now()).toLocalDateTime());
            ps.setTimestamp(7, t3);
            ps.setString(8, DashboardPageController.user.getName());
            ps.setInt(9, custID);
            ps.setInt(10, assignedUserID);
            ps.setInt(11, contactID);
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
