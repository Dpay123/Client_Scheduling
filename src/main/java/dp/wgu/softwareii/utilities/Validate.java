package dp.wgu.softwareii.utilities;

/**
 * This is a utility class used to validate input text.
 * Validation criteria can vary depending on the reason for validation,
 * such as ensuring compliance to database constraints.
 */
public class Validate {

    /**
     * Validates that text string is not blank and is within a specified character limit for an SQL record.
     * @param str the text string to validate
     * @param limit the specified character limit
     * @return true if valid else false
     */
    public static boolean isValidText(String str, int limit) {
        return str.length() > 0 && str.length() <= limit;
    }
}
