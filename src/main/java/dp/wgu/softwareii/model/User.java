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

    /**Return the user name*/
    public String getName() {
        return name;
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
