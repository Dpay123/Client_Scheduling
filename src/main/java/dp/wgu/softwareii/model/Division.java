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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
