package bean;

public class OrderPlaced {
    private String date;
    private int quantity;
    private int price;
    private int pID;
    private int cID;

    public OrderPlaced() {};

    public OrderPlaced(String date, int quantity, int price, int cID, int pID) {
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.cID = cID;
        this.pID = pID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getpID() {
        return pID;
    }

    public void setpID(int pID) {
        this.pID = pID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
    }

    @Override
    public String toString() {
        return "date: " + date + ", quantity: " + quantity + ", price: " + price + ", pID: " + pID + ", cID " + cID ;
    }
}
