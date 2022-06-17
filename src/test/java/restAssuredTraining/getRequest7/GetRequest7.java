package restAssuredTraining.getRequest7;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequest7 {

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

    private RequestSpecification xxx;

    @BeforeTest
    public void setUp(){

        xxx = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

    @Test
    public void test1(){

        xxx.pathParams("parametre1","todos", "parametre2",7);
        Response response =
                given().accept(ContentType.JSON)
                        .spec(xxx)
                        .when()
                        .get("/{parametre1}/{parametre2}");

        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);
        JsonPath jsonPath = response.jsonPath(); //bir nesnedeki değerleri kolayca almak için bir alternatif
        //jsonPath --> XML'deki Xpath ile aynı görevde.JsonPath'in ise bir JSON belgesindeki bir düğüme ulaşmak için bir yolu temsil etmesidir.
        //JSON biçiminde alabileceğim API'yi içerir.
        Assert.assertEquals("illo expedita consequatur quia in",jsonPath.getString("title"));
        Assert.assertEquals(7,jsonPath.getInt("id"));
        Assert.assertEquals(false,jsonPath.getBoolean("completed"));

    }
}
