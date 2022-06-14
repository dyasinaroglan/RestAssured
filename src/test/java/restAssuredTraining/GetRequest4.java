package restAssuredTraining;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest4 {

    @Test
    public void test1(){

        String url="https://restful-booker.herokuapp.com/booking/7";

                given()
                        .when()
                        .get(url)
                        .then()
                        .assertThat().statusCode(200)
                        .body("firstname",equalTo("Susan"),"lastname",equalTo("Wilson"),
                                "totalprice",equalTo("903"));
    }
}
