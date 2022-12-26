public class Property {
    private String cityName;
    private String street;
    private int numberOfRooms;
    private int price;
    private int type;
    private Boolean forRent;
    private int houseNumber;
    private int floorNumber;
    private User user;

    public Property () {

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
        if (checkType(type)) {
            this.type = type;
        }
    }

    private boolean checkType (int type) {
        return type >= 1 && type <= 3;
    }

    public Boolean isForRent() {
        return forRent;
    }

    public void setForRent(int forRent) {
        switch (forRent) {
            case 1 -> this.forRent = true;
            case 2 -> this.forRent = false;
        }
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

    public String toString () {
        String output = this.cityName + " - " + this.street + " " + this.houseNumber + ". \n";
        switch(this.type){
            case 1 -> output += "Regular apartment ";
            case 2 -> output += "Penthouse apartment ";
            case 3 -> output += "Land house ";
        }
        if(isForRent()){
            output += "- for rent: ";
        }else{
            output += "- for sale: ";
        }
        output += this.numberOfRooms + " rooms, floor " + this.floorNumber + ".\n";
        output += "Price: " + this.price + "$.\n";
        output += "Contact info: " + this.user.getUserName() + " " + this.user.getPhoneNumber() + " ";
        if(user.getBroker()){
            output += "(real estate broker).";
        }else{
            output += "(regular user).";
        }

        return output;
    }
}
