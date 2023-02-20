package dp.wgu.softwareii.model;

/**
 * POJO class for user data from the db.
 * A user has an id and name.
 */
public class User {
    private int id;
    private String name;

    /**
     * Construct a User obj
     * @param id
     * @param name
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**Return the user id*/
    public int getId() {
        return id;
    }

    /**Set the user id*/
    public void setId(int id) {
        this.id = id;
    }

    /**Return the user name*/
    public String getName() {
        return name;
    }

    /**Set the user id*/
    public void setName(String name) {
        this.name = name;
    }

    /**
     * String representation of user for GUI.
     * @return
     */
    @Override
    public String toString() {
        return name;
    }
}
