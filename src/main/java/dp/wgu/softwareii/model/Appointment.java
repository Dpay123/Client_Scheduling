package dp.wgu.softwareii.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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

    private String start;
    private String end;
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

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        this.start = startZDT_utc.format(dtf);
        this.end = endZDT_utc.format(dtf);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns a UTC-offset ZDT.
     * @return
     */
    public ZonedDateTime getStartZDT_utc() {
        return startZDT_utc;
    }

    public void setStartZDT_utc(ZonedDateTime startZDT_utc) {
        this.startZDT_utc = startZDT_utc;
    }

    /**
     * Returns a UTC-offset ZDT.
     * @return
     */
    public ZonedDateTime getEndZDT_utc() {
        return endZDT_utc;
    }

    public void setEndZDT_utc(ZonedDateTime endZDT_utc) {
        this.endZDT_utc = endZDT_utc;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | " + type
                + " | " + startZDT_utc.toLocalTime()
                + "-" + endZDT_utc.toLocalTime();
    }
}
