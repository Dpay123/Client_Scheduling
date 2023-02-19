package dp.wgu.softwareii.dbAccess;

import dp.wgu.softwareii.database.JDBC;
import dp.wgu.softwareii.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for handling access to the Customer table in the db.
 */
public class DBCustomers {

    /**
     * Query the db for a list of all customers and return the result set.
     * @return an ObservableList of customers.
     */
    public static ObservableList<Customer> getAll() {
        ObservableList<Customer> cList = FXCollections.observableArrayList();

        try {
            // use a prepared statement to execute an sql query
            String sql = "SELECT * from customers";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // for every result in the result set, create a Customer obj and add to list
            while (rs.next()) {
                int id = rs.getInt("Customer_ID");
                String name = rs.getString("Customer_Name");
                String address = rs.getString("Address");
                String postcode = rs.getString("Postal_Code");
                String phone = rs.getString("Phone");
                int divisionID = rs.getInt("Division_ID");
                Customer c = new Customer(id, name, address, postcode, phone, divisionID);
                cList.add(c);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return cList;
    }

    /**
     * Create a customer record in the db.
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param id
     */
    public static void addCustomer(String name, String address, String postal, String phone, int id) {

        String sql = "INSERT INTO customers ("
                + "Customer_Name,"
                + "Address, "
                + "Postal_Code, "
                + "Phone, "
                + "Division_ID) VALUES ("
                + "?, ?, ?, ?, ?)";

        try {
            // set the parameters
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postal);
            ps.setString(4, phone);
            ps.setInt(5, id);
            // execute
            ps.executeUpdate();
            System.out.println("Added successfully)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
