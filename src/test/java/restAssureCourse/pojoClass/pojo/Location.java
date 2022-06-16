package restAssureCourse.pojoClass.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Location {

            private int postCode;
            private String country;
            private String countryabbreviation;
            private List<Places> places;

    public int getPostCode() {
        return postCode;
    }
    @JsonProperty("post code")  //SADECE set edilen degerleri bu şekilde yazıyoruz
    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @JsonProperty("country abbreviation")  //iki kelime olup ayrı ayrı yazmak istersek bu şekilde yapabiliriz. burada kardeşim bunları ayrı ayrı
    public String getCountryabbreviation() {
        return countryabbreviation;
    }

    public void setCountryabbreviation(String countryabbreviation) {
        this.countryabbreviation = countryabbreviation;
    }

    public List<Places> getPlaces() {
        return places;
    }

    public void setPlaces(List<Places> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "***Location***" +
                "postCode = " + postCode +
                "country = " + country +
                "countryabbreviation = " + countryabbreviation +
                "places = " + places;
    }
}
