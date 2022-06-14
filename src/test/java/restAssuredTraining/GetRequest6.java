package restAssuredTraining;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest6 {

    /*
       https://jsonplaceholder.typicode.com/todos/123 url'ine
       accept type'i "application/json" olan GET request'i yolladigimda
       gelen response’un
       status kodunun 200
       ve content type'inin "application/json"
       ve Headers'daki "Server" in "cloudflare"
       ve response body'deki "userId"'nin 7
       ve "title" in "esse et quis iste est earum aut impedit"
       ve "completed" bolumunun false oldugunu test edin
     */

    private RequestSpecification spec1;    //specisification --> şartname, tanımlama, belirleme
                                           // request --> istek,talep
    @BeforeTest
    public void setup(){
        //    /todos/123
        spec1 = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
                                                     //burada base URİ yazıyoruz. diğerlerini parametre ile

    }

    @Test
    public void test1(){

        spec1.pathParams("parametre1","todos", "parametre2",123);
        Response response = given().accept(ContentType.JSON)
                .spec(spec1)
                .when()
                .get("/{parametre1}/{parametre2}");

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        response.then().assertThat().headers("Server",equalTo("cloudflare"))
                .body("userId",equalTo(7)).body("title",equalTo("esse et quis iste est earum aut impedit"))
                .body("completed",equalTo(false));
    }
}
