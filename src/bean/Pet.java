package bean;

public class Pet {
    private long PetID;
    private String Name1;
    private long Age;
    private String Type1;
    private long cID;

    public Pet(){};

    public Pet(long petID, String name1, long age, String type1, long cID) {
        PetID = petID;
        Name1 = name1;
        Age = age;
        Type1 = type1;
        this.cID = cID;
    }

    public long getPetID() {
        return PetID;
    }

    public void setPetID(long petID) {
        PetID = petID;
    }

    public String getName1() {
        return Name1;
    }

    public void setName1(String name1) {
        Name1 = name1;
    }

    public long getAge() {
        return Age;
    }

    public void setAge(long age) {
        Age = age;
    }

    public String getType1() {
        return Type1;
    }

    public void setType1(String type1) {
        Type1 = type1;
    }

    public long getcID() {
        return cID;
    }

    public void setcID(long cID) {
        this.cID = cID;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "PetID=" + PetID +
                ", Name1='" + Name1 + '\'' +
                ", Age=" + Age +
                ", Type1='" + Type1 + '\'' +
                ", cID=" + cID +
                '}';
    }
}
