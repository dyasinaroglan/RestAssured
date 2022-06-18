package restAssuredTraining.postRequest21;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class PostRequest21 {

    /*https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …
    }*/

    private RequestSpecification xxx;
    Response response ;

    @BeforeTest
    public void setUp(){

        //?format=json

        xxx = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
    @Test
    public void test1(){

        xxx.pathParam("parametre","todos");

        DataClass1 dataClass1 = new DataClass1();

        HashMap<String,Object> bodyRequest = dataClass1.requestBodyData();
        HashMap<String,Object> expectedResponse = dataClass1.expectedData();

        response =
                given()
                        .contentType(ContentType.JSON)
                        .spec(xxx).auth().basic("admin","password123")
                        .body(bodyRequest.toString())
                        .when()
                        .post("/{parametre}");

        JsonPath jsonPath = response.jsonPath();

        assertEquals(expectedResponse.get("completed"),jsonPath.getBoolean("completed"));
        assertEquals(expectedResponse.get("title"),jsonPath.getString("title"));



    }
}
