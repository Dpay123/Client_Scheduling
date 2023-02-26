package dp.wgu.softwareii.dbAccess;
import dp.wgu.softwareii.database.JDBC;
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
     * @param country the id of the country to fetch the divisions of
     * @return an ObservableList of divisions.
     */
    public static ObservableList<Division> getAll(int country) {
        ObservableList<Division> dList = FXCollections.observableArrayList();

        try {
            // use a prepared statement to execute an sql query
            String sql = "SELECT * from first_level_divisions WHERE COUNTRY_ID = " + country;
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

    /**
     * Given a division ID, fetch the division object, parse the country ID, and return it
     * @param divisionID
     * @return the country id of the division object
     */
    public static int getCountryByDivisionID(int divisionID) {

        Division d = DBDivisions.getDivisionByID(divisionID);
        return d.getCountryId();

    }

    /**
     * Given a division ID, fetch the division object.
     * @param divisionID
     * @return
     */
    public static Division getDivisionByID(int divisionID) {

        try {
            // use a prepared statement to excute an sql query
            String sql = "SELECT * from first_level_divisions WHERE Division_ID = " + divisionID;
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("Division_ID");
                String division = rs.getString("Division");
                int countryId = rs.getInt("Country_ID");
                return new Division(id, division, countryId);
            }
            else {
                System.out.println("No query results for division ID: " + divisionID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}