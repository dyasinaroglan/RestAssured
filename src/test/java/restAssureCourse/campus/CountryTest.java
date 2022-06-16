package restAssureCourse.campus;

import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;

public class CountryTest {

    Response response;
    String userID;

    @BeforeClass
    public void login() {

        baseURI = "https://demo.mersys.io";

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", "richfield.edu");
        credentials.put("password", "richfield2020!");
        credentials.put("rememberMe", "true");

         response = given()
                .body(credentials)
                .contentType(ContentType.JSON)
                 .log().uri()
                .when()
                .post("/auth/login")
                .then().statusCode(200)
                .extract().response();

        Cookies cookies = response.getDetailedCookies();
        System.out.println(cookies);

    }
    @Test
    public void createCountry(){

        String randomName = RandomStringUtils.randomAlphabetic(6);
        String randomCode = RandomStringUtils.randomAlphabetic(2);

        Country country = new Country();
        country.setName(randomName);
        country.setCode(randomCode);

        userID = given()
                .cookies(response.getDetailedCookies())
                .body(country)
                .contentType(ContentType.JSON)
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

        country.setCode(RandomStringUtils.randomAlphabetic(3));
        country.setName(RandomStringUtils.randomAlphabetic(8));

        Response response1 = given()
                .cookies(response.getDetailedCookies())
                .body(country)
                .contentType(ContentType.JSON)
                .when()
                .put("/school-service/api/countries/")
                .then()
                .statusCode(200).log().body()
                .body("code", equalTo(country.getName()))
                .body("name", equalTo(country.getName()))
                .extract().response();

        System.out.println(response1.jsonPath().getString("code"));
        System.out.println(response1.jsonPath().getString("name"));

    }
    @Test(dependsOnMethods = "updateCountry")
    public void deleteCountry(){

        given()
                .cookies(response.getDetailedCookies())
                .when()
                .delete("/school-service/api/countries/" + userID)
                .then()
                .statusCode(200)
                .contentType(emptyOrNullString());


    }

}
