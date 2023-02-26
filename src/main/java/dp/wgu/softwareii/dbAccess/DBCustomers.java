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
     * @return true if successful, else false
     */
    public static boolean addCustomer(String name, String address, String postal, String phone, int id) {

        String sql = "INSERT INTO customers ("
                + "Customer_Name, "
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Delete a customer reccord in the db based upon id.
     * @param id
     * @return true if successful else false
     */
    public static boolean delCustomer(int id) {

        String sql = "DELETE FROM customers WHERE Customer_ID = " + id;

        try {
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.executeUpdate();
            System.out.println("Removed successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update a customer from the db based upon id, return true if success, else false.
     * @param id
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param division
     */
    public static boolean updateCustomer(int id, String name, String address, String postal, String phone, int division) {
        String sql = "UPDATE customers "
                + "SET Customer_Name = ?, "
                + "Address = ?, "
                + "Postal_Code = ?, "
                + "Phone = ?, "
                + "Division_ID = ? WHERE Customer_ID = " + id;

        try {
            // set parameters
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, postal);
            ps.setString(4, phone);
            ps.setInt(5, division);
            // execute
            ps.executeUpdate();
            System.out.println("Updated successful");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
