package dp.wgu.softwareii.model;

/**
 * Enum used for static list of appointment types.
 */
public enum Type {
    INITIAL("Initial"),
    PLANNING("Planning Session"),
    DEBRIEFING("De-Briefing"),
    UPDATE("Update"),
    APPROVAL("Approval"),
    DELIVERY("Delivery");

    private String label;

    /**
     * Construct the enum.
     * @param theType
     */
    Type(String theType) {
        this.label = theType;
    }

    /**Return a string representation of the enum for the GUI*/
    public String toString() {
        return this.label;
    }
}
