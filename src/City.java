import java.util.Arrays;

public class City {

    private String cityName;
    private String district;
    private String[] streets;

    public City (String cityName, String district, String[] streets) {
        this.cityName = cityName;
        this.district = district;
        this.streets = streets;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String[] getStreets() {
        return streets;
    }

    public void setStreets(String[] streets) {
        this.streets = streets;
    }
}
