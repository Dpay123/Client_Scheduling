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
}
