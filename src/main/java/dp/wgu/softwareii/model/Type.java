package dp.wgu.softwareii.model;

public enum Type {
    INITIAL("Initial"),
    PLANNING("Planning Session"),
    DEBRIEFING("De-Briefing"),
    UPDATE("Update"),
    APPROVAL("Approval"),
    DELIVERY("Delivery");

    private String label;

    Type(String theType) {
        this.label = theType;
    }

    public String toString() {
        return this.label;
    }
}
