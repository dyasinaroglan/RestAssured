package restAssuredTraining.requestPatch24;

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

public class PatchRequest24 {

    /*
       https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
      {

        "title": "API calismaliyim"

      }
      Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
     {
      "userId": 10,
      "title": "API calismaliyim"
      "completed": true,
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

        DataClass dataClass = new DataClass();
        HashMap<String,Object> requestBody = dataClass.requestBody();
        HashMap<String,Object> expectedData = dataClass.expectedData();


        response =
                 given()
                         .contentType(ContentType.JSON)
                         .spec(xxx).auth().basic("admin","password123")
                         .body(requestBody)
                         .patch("/{parametre1}/{parametre2");

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("title"),jsonPath.getString("title"));
        Assert.assertEquals(expectedData.get("completed"),jsonPath.getString("completed"));
        Assert.assertEquals(expectedData.get("userId"),jsonPath.getString("userId"));

    }

}
