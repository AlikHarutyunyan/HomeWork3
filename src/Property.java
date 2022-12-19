public class Property {
    private String cityName;
    private String street;
    private int numberOfRooms;
    private int price;
    private int type;
    private boolean forRent;
    private int houseNumber;
    private int floorNumber;
    private User user;

    public Property (String cityName, String street, int numberOfRooms, int price,
                     int type, boolean forRent, int houseNumber, int floorNumber, User user) {
        setCityName(cityName);
        setStreet(street);
        setNumberOfRooms(numberOfRooms);
        setPrice(price);
        setType(type);
        setForRent(forRent);
        setFloorNumber(floorNumber);
        setUser(user);
        setHouseNumber(houseNumber);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isForRent() {
        return forRent;
    }

    public void setForRent(boolean forRent) {
        this.forRent = forRent;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
