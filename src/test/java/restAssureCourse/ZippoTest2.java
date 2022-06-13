package restAssureCourse;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.defaultParser;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public class ZippoTest2 {

    //     http://api.zippopotam.us
    //    /us/90210 parametre

    @Test
    public void pathParamTest(){
        /*
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200).body("places.size()", equalTo(1));
        //ya da
        given().when().get("http://api.zippopotam.us/us/90210").then().statusCode(200).body("places", hasSize(1)).log().body();

         */

        given().pathParam("country", "us").
                pathParam("postalCode", "90210").
                log().uri().
                when().get("http://api.zippopotam.us/{country}/{postalCode}").
                then().statusCode(200).body("places.size()", equalTo(1)).log().body();

        //https://gorest.co.in/public-api/users?page=1

    }
    @Test
    public void queryParamTest(){
        int page1 = 1;

        given().queryParam("page", page1).
                log().uri().
                when().get("https://gorest.co.in/public-api/users").  ////https://gorest.co.in/public-api/users?page=1 page=1 siliyoruz
                then().log().body().statusCode(200).body("data[0].name", equalTo("Gouranga Trivedi I"));


    }
    @Test
    public void queryParamTestMulti(){

        for (int page = 1; page <= 10; page++) {
            given().param("page", page).
                    log().uri().
                    when().get("https://gorest.co.in/public-api/users").
                    then().log().body().statusCode(200).body("meta.pagination.page", equalTo(page));
        }
            
        }

    @Test
    public void queryParamTestMulti1(){

        HashMap<String,String> params = new HashMap<>();
        params.put("page", "1");
        params.put("id", "34344");

        for (int page = 1; page <= 10; page++) {
            given().param("page", page).
                    queryParam("page", page).queryParam("id","12").
                    queryParams(params).
                    log().uri().
                    when().get("https://gorest.co.in/public-api/users").
                    then().log().body().statusCode(200).body("meta.pagination.page", equalTo(page));
        }

    }


}
