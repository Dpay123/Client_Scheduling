package dp.wgu.softwareii.model;

public enum Type {
    INITIAL("Initial"),
    PLANNING("Planning"),
    REVIEW("Review"),
    UPDATE("Update"),
    APPROVAL("Approval"),
    DELIVERY("Delivery");

    private String label;

    private Type(String theType) {
        this.label = theType;
    }
}
