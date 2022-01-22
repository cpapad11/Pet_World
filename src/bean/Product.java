package bean;

public class Product {
    private long pID;
    private String Name1;
    private long Price;
    private long Quantity;
    private String sName;
    private String phoneNumber;

    public Product() {
    }

    public Product(long pID, String name1, long price, long quantity, String sName, String phoneNumber) {
        this.pID = pID;
        Name1 = name1;
        Price = price;
        Quantity = quantity;
        this.sName = sName;
        this.phoneNumber = phoneNumber;
    }

    public long getpID() {
        return pID;
    }

    public void setpID(long pID) {
        this.pID = pID;
    }

    public String getName1() {
        return Name1;
    }

    public void setName1(String name1) {
        Name1 = name1;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }

    public long getQuantity() {
        return Quantity;
    }

    public void setQuantity(long quantity) {
        Quantity = quantity;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  "pID=" + pID +
                ", Name1='" + Name1 + '\'' +
                ", Price=" + Price +
                ", Quantity=" + Quantity +
                ", sName='" + sName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'';
    }
}
