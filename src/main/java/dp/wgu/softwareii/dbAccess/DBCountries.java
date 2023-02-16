package dp.wgu.softwareii.dbAccess;

import dp.wgu.softwareii.database.JDBC;
import dp.wgu.softwareii.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class for handling access to the Country table in the db.
 */
public class DBCountries {

    /**
     * Query the db for a list of all countries and return the result set.
     * @return an ObservableList of countries.
     */
    public static ObservableList<Country> getAll() {

        ObservableList<Country> cList = FXCollections.observableArrayList();

        try {
            // use a prepared statement to execute an sql query
            String sql = "SELECT * from countries";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // for every result in the result set, create a Country obj and add to list
            while (rs.next()) {
                int countryId = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Country c = new Country(countryId, countryName);
                cList.add(c);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return cList;
    }

}
