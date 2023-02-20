package dp.wgu.softwareii.dbAccess;

import dp.wgu.softwareii.database.JDBC;
import dp.wgu.softwareii.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The class for handling access to the Users table in the tb.
 */
public class DBUsers {


    /**
     * Given a username and password, query the database and determine if valid user.
     * If valid, return the user obj, else return null;
     * @param username
     * @param password
     * @return either a valid user or null
     */
    public static User validate(String username, String password) {
        User user;

        try {
            // use a prepared statement to execute the sql query
            String sql = "SELECT * from users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = JDBC.getConnection().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) return new User(rs.getInt("User_ID"), rs.getString("User_Name"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
