package restAssuredTraining.postRequest18;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import restAssuredTraining.testBase.HerOkuAppTestBase;
import restAssuredTraining.testData.HerOkuAppTestData;

import java.util.HashMap;
import static io.restassured.RestAssured.given;
import org.junit.Test.*;
import org.junit.Assert.*;



public class PostRequest18 extends HerOkuAppTestBase {

    /*
    https://restful-booker.herokuapp.com/booking url ine, Request Body olarak
             {
               "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }
        gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
            "booking": {
                    "firstname": " Selim ",
                    "lastname": " Ak ",
                    "totalprice":  11111,
                    "depositpaid": true,
                    "bookingdates": {
                    "checkin": "2020-09-01",
                    "checkout": " 2020-09-21”
                     },
              }
             olduğunu test edin*/

    @Test
    public void test1(){

        xxx.pathParam("parametre","booking");

        HerOkuAppTestData testData = new HerOkuAppTestData();
        HashMap<String,Object> expectedData = testData.setupTestData();

        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .spec(xxx)
                        .auth().basic("admin","password123")
                        .body(expectedData)
                        .when()
                        .post("/{parametre}");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("firstname"),jsonPath.get("booking.firstname"));
        Assert.assertEquals(expectedData.get("lastname"),jsonPath.get("booking.lastname"));
        Assert.assertEquals(expectedData.get("totalprice"),jsonPath.get("booking.totalprice"));



    }
}
