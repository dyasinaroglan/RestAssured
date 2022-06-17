package restAssuredTraining.postRequest19;

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

public class PostRequest19 {

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

    @BeforeTest
    public void setUp(){
        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){

        xxx.pathParam("parametre1","create");

        DataClass dataClass = new DataClass();
        HashMap<String,Object> requestBodyMap= dataClass.requestBody();
        HashMap<String,Object> expectedDataMap= dataClass.setupData();

        Response response =
                given()
                        .accept(ContentType.JSON)

                        .spec(xxx).auth().basic("admin","password123")
                        .body(requestBodyMap)
                        .when()
                        .post("/{parametre1}");

        JsonPath jsonPath = response.jsonPath();


        Assert.assertEquals(expectedDataMap.get("statusCode"),jsonPath.getString("statusCode"));
        Assert.assertEquals(expectedDataMap.get("status"),jsonPath.getString("status"));
        Assert.assertEquals(jsonPath.getString("message"),expectedDataMap.get("message"));
        Assert.assertEquals(expectedDataMap.get("id"),jsonPath.getString("id"));
        Assert.assertEquals(expectedDataMap.get("name"),jsonPath.getString("name"));
    }
}
