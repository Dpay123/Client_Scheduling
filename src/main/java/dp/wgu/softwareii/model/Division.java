package dp.wgu.softwareii.model;

/**
 * POJO class for division data from db.
 */
public class Division {
    private int id;
    private String name;
    private int countryId;

    /**
     * Construct a Division obj.
     * @param id the division id
     * @param name the name/title of the division
     * @param countryId the id of the country that the division belongs to
     */
    public Division(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    /**Return the id of the division*/
    public int getId() {
        return id;
    }

    /**Return the name of the division*/
    public String getName() {
        return name;
    }

    /**Return the country id of the division*/
    public int getCountryId() {
        return countryId;
    }

    /**
     * Return a string representation of the Division for the GUI.
     * @return
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
