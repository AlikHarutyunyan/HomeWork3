public class City {

    private final String cityName;
    private final String district;
    private final String[] streets;

    public City (String cityName, String district, String[] streets) {
        this.cityName = cityName;
        this.district = district;
        this.streets = streets;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDistrict() {
        return district;
    }

    public String[] getStreets() {
        return streets;
    }

}
