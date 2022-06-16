package restAssuredTraining.postRequest17;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import restAssuredTraining.getRequest16.FakeTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequest17 extends FakeDataTest1 {

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

    public RequestSpecification xxx;
    Response response;

    @BeforeTest
    public void setup(){
        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){

        FakeDataTest1 fakeDataTest1 = new FakeDataTest1();
        HashMap<String,Object> requestData = (HashMap<String, Object>) fakeDataTest1.setUpRequestBody();

        xxx.pathParam("parametre","create");
        response = given()
                .accept(ContentType.JSON)
                .spec(xxx).auth().basic("admin","password123")
                .body(requestData)
                .when()
                .post("/{parametre}");

        HashMap<String,Object> expectedData = (HashMap<String, Object>) fakeDataTest1.setUpExpectedData();
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(expectedData.get("status"),jsonPath.getString("status"));
        Assert.assertEquals(expectedData.get("message"),jsonPath.getString("message"));




    }
}
