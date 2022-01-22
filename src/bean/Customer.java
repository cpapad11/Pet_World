package bean;

public class Customer {
    private int cID;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int age;

    public Customer () {};

    public Customer(int cID, String name, String address, String phone, String email, int age) {
        this.cID = cID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.age = age;
    }

    public int getcID() {
        return cID;
    }

    public void setcID(int cID) {
        this.cID = cID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "cID=" + cID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age ;
    }
}
