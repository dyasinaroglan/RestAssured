package restAssuredTraining.getRequest12;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest12 {

    /*
    https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
    Dönen response un
    Status kodunun 200, dönen body de,
        "completed": değerinin false
        "title”: değerinin “quis ut nam facilis et officia qui”
        "userId" sinin 1 ve header değerlerinden
        "Via" değerinin “1.1 vegur” ve
        "Server" değerinin “cloudflare” olduğunu test edin…
     */

    private RequestSpecification xxx;

    @BeforeTest
    public void setUp(){

        xxx = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
    @Test
    public void test1(){

        xxx.pathParams("parametre1","todos","parametre2",2);  //birden fazla parametre yazıyorsak params olacak

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("completed",false);
        expectedData.put("title","quis ut nam facilis et officia qui");
        expectedData.put("userId",1);
        expectedData.put("Via","1.1 vegur");
        expectedData.put("Server","cloudflare");

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .spec(xxx)
                        .when()
                        .get("/{parametre1}/{parametre2}");
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());

        response.then().assertThat().body("completed",equalTo(expectedData.get("completed")))
                .body("title",equalTo(expectedData.get("title")))
                .body("userId",equalTo(expectedData.get("userId")))
                .headers("Via",equalTo(expectedData.get("Via")))
                .headers("Server",equalTo(expectedData.get("Server")));
        // ya da

        Assert.assertEquals(expectedData.get("Via"),response.getHeader("Via"));
        Assert.assertEquals(expectedData.get("Server"),response.getHeader("Server"));
        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getBoolean("completed"));
    }
}
