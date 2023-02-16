package dp.wgu.softwareii.model;

/**
 * POJO model for country data from db.
 * A Country has an id and a name.
 */
public class Country {
    private int id;
    private String name;

    /**
     * Construct a country.
     * @param id the ID of the country
     * @param name the name of the country
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get the id of the country.
     * @return the id as int
     */
    public int getId() { return id; }

    /**
     * Get the name of the country.
     * @return the name as String
     */
    public String getName() { return name; }
}
