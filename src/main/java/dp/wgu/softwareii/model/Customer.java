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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }
}
