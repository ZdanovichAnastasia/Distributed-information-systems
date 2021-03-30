package BusinessLogicModels;

public class Customer {
    private int idCustomer;
    private int zip;
    private String name;
    private String city;

    public Customer(int idCustomer, String name, int zip, String city) {
        this.idCustomer = idCustomer;
        this.zip = zip;
        this.name = name;
        this.city = city;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }


    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
