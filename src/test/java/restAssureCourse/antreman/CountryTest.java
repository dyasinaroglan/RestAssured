package restAssureCourse.antreman;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CountryTest extends Country {


    Response response;
    String userID;

    @BeforeClass
    public void setUp() {

        baseURI = "https://demo.mersys.io";

        Country country = new Country();
        HashMap<String, Object> ulkeBilgi = (HashMap<String, Object>) country.expectedDataTest();

        response =
                given()
                        .body(ulkeBilgi)
                        .contentType(ContentType.JSON)
                        .when().post("/auth/login")
                        .then().statusCode(200)
                        .extract().response();

        Cookies cookies = response.getDetailedCookies();
        System.out.println(cookies);
    }
    @Test
    public void createCountry() {

        //https://demo.mersys.io/school-service/api/countries
        String randomName = RandomStringUtils.randomAlphabetic(6); //Alfabetik karakterlerden oluşan rastgele dizeler oluşturmak
        String randomCode = RandomStringUtils.randomAlphabetic(2); //Karakterler latin alfabetik karakterlerden oluşur(A-Z)
        //Maven Repository den apache.commons'u Pom a eklememiz gerekiyor.

        Country country = new Country();
        country.setName(randomName);
        country.setCode(randomCode);

            userID =
                    given()
                            .cookies(response.getDetailedCookies())
                            .contentType(ContentType.JSON)
                            .body(country)
                            .when()
                            .post("/school-service/api/countries")
                            .then().log().body().statusCode(201)
                            .extract().jsonPath().getString("id");

            Cookies cookies = response.getDetailedCookies();
        System.out.println(userID);

    }
    @Test(dependsOnMethods = "createCountry")
    public void updateCountry(){

        Country country = new Country();
        country.setId(userID);

        country.setName(RandomStringUtils.randomAlphabetic(7));
        country.setCode(RandomStringUtils.randomAlphabetic(2));

        Response response1 =
                given()
                        .cookies(response.getDetailedCookies())
                        .body(country)
                        .contentType(ContentType.JSON)
                        .when().put("/school-service/api/countries")
                        .then().log().body().statusCode(200)
                        .body("code",equalTo(country.getCode()))
                        .body("name",equalTo(country.getName()))
                        .extract().response();

        System.out.println(response1.jsonPath().getString("name"));
        System.out.println(response1.jsonPath().getString("code"));

    }
    @Test(dependsOnMethods = "updateCountry")
    public void deleteTest(){

        given()
                .cookies(response.getDetailedCookies())
                .contentType(ContentType.JSON)
                .when().delete("/school-service/api/countries/"+userID)
                .then()
                .statusCode(200);
    }

}
