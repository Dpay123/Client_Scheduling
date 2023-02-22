package dp.wgu.softwareii.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * POJO class for appointment data from db
 */
public class Appointment {
    private int id;
    private String title;
    private String description;
    private String location;
    private String type;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
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
     * @param startDateTime the appt start time/date
     * @param endDateTime the appt end time/date
     * @param customerId the id of the customer of the appt
     * @param userId the id of the user who creates the appt
     * @param contactId the id of the contact for the appt
     */
    public Appointment(int id,
                       String title,
                       String description,
                       String location,
                       String type,
                       LocalDateTime startDateTime,
                       LocalDateTime endDateTime,
                       int customerId,
                       int userId,
                       int contactId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.customerId = customerId;
        this.userId = userId;
        this.contactId = contactId;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        this.start = startDateTime.format(dtf);
        this.end = endDateTime.format(dtf);
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
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

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
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
                + "|" + type
                + "|" + startDateTime.toLocalTime()
                + "-" + endDateTime.toLocalTime();
    }
}
