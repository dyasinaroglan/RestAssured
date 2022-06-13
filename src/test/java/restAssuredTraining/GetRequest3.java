package restAssuredTraining;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.EasyMock2Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetRequest3 {

    /*
     url'ine bir GET request gonderdigimizde donen Response'un,
          status code'unun 200,
           ve content type'inin "application/json"; charset=utf-8,
           ve Server isimli Header'in degerinin Cowboy,
           ve status Line'in HTTP/1.1 200 OK
           ve response suresinin 5 sn'den kisa oldugunu manuel olarak test ediniz.
     */

    @Test
    public void test1(){
        String url = "https://restful-booker.herokuapp.com/booking/10";

        Response response = given().when().get(url);
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON)
                .header("Server", equalTo("Cowboy")).statusLine("HTTP/1.1 200 OK");

        response.then().assertThat().time(lessThan((long)5000)); //response süresinin 5 saniyeden  kısa olduğunu
    }


}
