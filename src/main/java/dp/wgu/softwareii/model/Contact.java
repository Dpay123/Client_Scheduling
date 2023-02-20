package dp.wgu.softwareii.model;

/**
 * POJO for contact data from db.
 * A contact has an id, name, and email.
 */
public class Contact {
    private int id;
    private String name;
    private String email;

    /**
     * Construct a Contact.
     * @param id
     * @param name
     * @param email
     */
    public Contact(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Return a string representation of the Contact obj.
     * @return
     */
    @Override
    public String toString() {
        return this.name + " | " + this.email;
    }

    /**
     * Return the id of the contact.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of the contact.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return the name of the contact.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the contact.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the email of the contact.
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the contact.
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
