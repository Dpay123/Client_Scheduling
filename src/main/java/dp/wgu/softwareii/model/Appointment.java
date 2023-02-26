package dp.wgu.softwareii.model;

import java.time.ZonedDateTime;

/**
 * POJO class for appointment data from db.
 */
public class Appointment {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;

    /**Standardized to UTC- offset*/
    private ZonedDateTime startZDT_utc;

    /**Standardized to UTC- offset*/
    private ZonedDateTime endZDT_utc;

    private int customerId;
    private int userId;
    private int contactId;

    /**
     * Construct an Appointment obj
     * @param id the appt id
     * @param title the appt title
     * @param description the appt description
     * @param location the appt location
     * @param type the appt type
     * @param startZDT_utc the appt start time/ in UTC offset
     * @param endZDT_utc the appt end time/date in UTC offset
     * @param customerId the id of the customer of the appt
     * @param userId the id of the user who creates the appt
     * @param contactId the id of the contact for the appt
     */
    public Appointment(int id,
                       String title,
                       String description,
                       String location,
                       String type,
                       ZonedDateTime startZDT_utc,
                       ZonedDateTime endZDT_utc,
                       int customerId,
                       int userId,
                       int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startZDT_utc = startZDT_utc;
        this.endZDT_utc = endZDT_utc;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;
    }

    /**Get the id of the appt*/
    public int getId() {
        return id;
    }

    /**Get the title of the appt*/
    public String getTitle() {
        return title;
    }

    /**Get the description of the appt*/
    public String getDescription() {
        return description;
    }

    /**Get the location of the appt*/
    public String getLocation() {
        return location;
    }

    /**Get the type of the appt*/
    public String getType() {
        return type;
    }

    /**
     * Returns a UTC-offset ZDT.
     * @return
     */
    public ZonedDateTime getStartZDT_utc() {
        return startZDT_utc;
    }

    /**Returns a UTC-offset ZDT.
     * @return
     */
    public ZonedDateTime getEndZDT_utc() {
        return endZDT_utc;
    }

    /**Get the customer id of the appt*/
    public int getCustomerId() {
        return customerId;
    }

    /**Get the user id of the appt*/
    public int getUserId() {
        return userId;
    }

    /**Get the contact id of the appt*/
    public int getContactId() {
        return contactId;
    }

    /**
     * Return a string representation of the appt for the GUI.
     * @return
     */
    @Override
    public String toString() {
        return "ID: " + id
                + " | " + type
                + " | " + startZDT_utc.toLocalTime()
                + "-" + endZDT_utc.toLocalTime();
    }
}
