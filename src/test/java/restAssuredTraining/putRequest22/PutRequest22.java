package restAssuredTraining.putRequest22;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PutRequest22 {

    /*
       https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
     {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
          Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
     {
       "userId": 21,
       "title": "Wash the dishes",
       "completed": false,
       "id": 198
     }
     */

    private RequestSpecification xxx;
    Response response;

    @BeforeTest
    public void setUp(){
        xxx = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
    @Test
    public void test1(){
        xxx.pathParams("parametre1","todos","parametre2",198);

        DataClass2 dataClass2 = new DataClass2();
        HashMap<String,Object> bodyRequest2 = dataClass2.requestBody();
        HashMap<String,Object> expectedData2 = dataClass2.expectedBilgiler();

        response =
                given()
                        .contentType(ContentType.JSON)
                        .spec(xxx).auth().basic("admin","password123")
                        .body(bodyRequest2)
                        .when()
                        .put("/{parametre1}/{parametre2}");

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(expectedData2.get("userId"),jsonPath.getInt("userId"));
        Assert.assertEquals(expectedData2.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData2.get("completed"),jsonPath.getBoolean("completed"));
    }
}
