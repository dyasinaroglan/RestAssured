package restAssuredTraining;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class GetRequest1 {

    /*
    https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
    HTTP status kodunun 200
    Content Type'in Json
    Ve Status Line'in HTTP/1.1 200 OK
    Oldugunu test edin
     */
    @Test
    public void test1(){
        // 1. APİ testi yaparken önce url(endpoint) belirlenmeli.
        String url = "https://restful-booker.herokuapp.com/booking/3";
        Response response = given().accept("application/json").when().get(url);
        response.prettyPeek();

        System.out.println("statusCode : " + response.statusCode());
        System.out.println("contentType : " + response.contentType());

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON).statusLine("HTTP/1.1 200 OK");
    }


}
