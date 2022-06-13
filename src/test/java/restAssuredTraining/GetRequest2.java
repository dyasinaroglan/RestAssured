package restAssuredTraining;

import io.restassured.http.ContentType;
import io.restassured.internal.http.HttpResponseDecorator;
import io.restassured.internal.http.HttpResponseException;
import io.restassured.response.Response;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequest2 {


    /*
    https://restful-booker.herokuapp.com/booking url'ine
   accept type'i "application/json" olan GET request'i yolladigimda
   gelen response'un
   status kodunun 200
   content type'inin "application/json" oldugunu test edin
     */

    @Test
    public void test1(){

        String url = "https://restful-booker.herokuapp.com/booking";

        Response response = given().accept("application/json").when().get(url);
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

    }
}
