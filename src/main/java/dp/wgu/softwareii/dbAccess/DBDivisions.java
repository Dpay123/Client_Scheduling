package dp.wgu.softwareii.dbAccess;

import dp.wgu.softwareii.database.JDBC;
import dp.wgu.softwareii.model.Customer;
import dp.wgu.softwareii.model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for handling access to the Divisions table in the db.
 */
public class DBDivisions {

    /**
     * Query the db for a list of all divisions and return the result set.
     * @return an ObservableList of divisions.
     */
    public static ObservableList<Division> getAll() {
        ObservableList<Division> dList = FXCollections.observableArrayList();

        try {
            // use a prepared statement to execute an sql query
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // for every result in the result set, create a Division obj and add to list
            while (rs.next()) {
                int id = rs.getInt("Division_ID");
                String name = rs.getString("Division");
                int countryID = rs.getInt("COUNTRY_ID");
                Division d = new Division(id, name, countryID);
                dList.add(d);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return dList;
    }

}