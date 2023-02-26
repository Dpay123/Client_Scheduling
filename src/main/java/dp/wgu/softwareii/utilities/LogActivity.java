package dp.wgu.softwareii.utilities;

import dp.wgu.softwareii.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Captures and records log information to desired output location.
 * This class is intended to handle logging of multiple types of data
 * from various sources to various locations.
 */
public class LogActivity {

    /**The path of the file to track user login data.*/
    private static String loginFile = "login_activity.txt";

    public static void loginAttempt(String username, boolean isValidUser) {
        // get timestamp of attempt in UTC
        ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
        // set record string
        String log;
        if (!isValidUser) {
            log = "Unsuccessful login occurred at "
                    + timestamp.format(TimeHandler.dateTimeFormat)
                    + " UTC for username '" + username + "'\n";
        }
        else {
            log = "Successful login occurred at "
                    + timestamp.format(TimeHandler.dateTimeFormat)
                    + " UTC for username '" + username + "'\n";
        }
        // record log
        recordDataToFile(log, loginFile);
    }

    public static void logout(User user) {
        // get timestamp in UTC
        ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
        // set record string
        String log = "Successful logout occurred at "
                + timestamp.format(TimeHandler.dateTimeFormat)
                + "UTC for user '" + user.getName() + "'\n";
        // record log
        recordDataToFile(log, loginFile);
    }

    /**
     * Record a string to a specified log file.
     * @param data the data to record
     * @param file the specified file to save the string to
     */
    private static void recordDataToFile(String data, String file) {
        try {
            // open the file for appending (or create it if it doesn't exist)
            FileWriter writer = new FileWriter(file, true);
            // append text to the file
            writer.write(data);
            // close the writer
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
