package restAssureCourse.pojoClass;

import org.testng.annotations.Test;
import restAssureCourse.pojoClass.pojo.Location;
import restAssureCourse.pojoClass.pojo.Places;

import java.util.List;

import static io.restassured.RestAssured.given;

public class TestingPojo {

    @Test
    public void getLocation(){
        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                .log().body()
                .statusCode(200)

                ;
    }
    @Test
    public void exractingJsonAsPojo(){
       Location location = given()
                .when()
                .get("http://api.zippopotam.us/tr/01000")
                .then()
                //.log().body()
                .extract().as(Location.class);  //bu endpoint bana bir pojo classı döndürür. bir nesne sadece

        System.out.println(location);
        System.out.println(location.getCountry());
        System.out.println(location.getPostCode());
        System.out.println(location.getPlaces());
        System.out.println(location.getPlaces().get(0).getPlaceName());

        for (Places places : location.getPlaces()){
            if (places.getPlaceName().equals("Camuzcu Köyü")){
                System.out.println(places.getPlaceName());
                System.out.println(places.getLongitude());
                System.out.println(places.getState());
                System.out.println(places.getStateAbbreviation());
                System.out.println(places.getLatitude());
            }

            }
    }
    @Test
    public void extractJsonAsPojoPlacesList(){

        List<Places> placesList = given()
                .when()
                .get("http://api.zippopotam.us/tr/01000")
                .then()
                //.log().body()
                .extract().jsonPath().getList("places",Places.class);  //bu endpoint bana bir pojo classı döndürür. bir nesne sadece

        System.out.println(placesList);

        for (Places places : placesList) {
                System.out.println(places.getPlaceName());

            }
                
            }

}
