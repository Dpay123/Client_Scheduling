package dp.wgu.softwareii.dbAccess;

import dp.wgu.softwareii.database.JDBC;
import dp.wgu.softwareii.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class for handling access to the Contacts table in the db.
 */
public class DBContacts {

    /**
     * Query the db for a list of all contacts and return the results set.
     * @return an ObservableList of contacts.
     */
    public static ObservableList<Contact> getAll() {
        ObservableList<Contact> cList = FXCollections.observableArrayList();

        try {
            // use a prepared statement to execute an sql query
            String sql = "SELECT * from contacts";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // for every result in the result set, create a Contact obj and add to list
            while (rs.next()) {
                int id = rs.getInt("Contact_ID");
                String name = rs.getString("Contact_Name");
                String email = rs.getString("Email");
                Contact contact = new Contact(id, name, email);
                cList.add(contact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cList;
    }
}
