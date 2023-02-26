package dp.wgu.softwareii.model;

/**
 * POJO class for customer data from db.
 */
public class Customer {
    private int id;
    private String name;
    private String address;
    private String postcode;
    private String phone;
    private int division;

    /**
     * Construct a Customer.
     * @param id the id of the customer
     * @param name the name of the customer
     * @param address the address of the customer
     * @param postcode the zip/postal code of the customer
     * @param phone the phone number of the customer
     * @param division the division/province of the customer
     */
    public Customer(int id,
                    String name,
                    String address,
                    String postcode,
                    String phone,
                    int division) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.phone = phone;
        this.division = division;
    }

    /**
     * Return the id.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Return the name.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Return the address.
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Return the postcode.
     * @return
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Return the phone number.
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Return the division.
     * @return
     */
    public int getDivision() {
        return division;
    }

    /**
     * Return a string representation of the Customer for the GUI.
     * @return
     */
    @Override
    public String toString() {
        return this.name;
    }
}
