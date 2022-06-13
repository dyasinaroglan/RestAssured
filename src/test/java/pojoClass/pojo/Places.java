package pojoClass.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Places {


    private String placeName;
    private String longitude;
    private String state;
    private String stateAbbreviation;
    private String latitude;

    public String getPlaceName() {
        return placeName;
    }
    @JsonProperty("place name")
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }
    @JsonProperty("state abbreviation")
    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "\n***Places***" +
                "\nplaceName = " + placeName +
                "\nlongitude = " + longitude +
                "\nstate = " + state +
                "\nstateAbbreviation = " + stateAbbreviation +
                "latitude = " + latitude ;
    }
}
