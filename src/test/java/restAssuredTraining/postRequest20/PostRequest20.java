package restAssuredTraining.postRequest20;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest20 {
    /*
        http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
        {
           "name":"Ahmet Aksoy",
           "salary":"1000",
           "age":"18",
        }
           gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin ,
       {
          "status": "success",
          "data": {
          “id”:…
        },
          "message": "Successfully! Record has been added."
        }
           olduğunu test edin
      */

    private RequestSpecification xxx;
    Response response ;

    @BeforeTest
    public void setUp(){

        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){

        xxx.pathParam("parametre","create");

        DataClass dataClass = new DataClass();
        HashMap<String,Object> bodyRequest = dataClass.requestBody();
        HashMap<String,Object> expectedData = dataClass.setupExpectedData();

        response =
                (Response) given()
                        .accept(ContentType.JSON)
                        .spec(xxx).auth().basic("admin","password123")
                        .body(bodyRequest)
                        .when()
                        .post("/{parametre}");

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("statusCode"),jsonPath.getString("statusCode"));
        Assert.assertEquals(expectedData.get("message"),jsonPath.getString("message"));
        Assert.assertEquals(expectedData.get("id"),jsonPath.getString("id"));
        Assert.assertEquals(expectedData.get("name"),jsonPath.getString("name"));
    }
}
