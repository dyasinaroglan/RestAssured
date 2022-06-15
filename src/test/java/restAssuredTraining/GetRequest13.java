package restAssuredTraining;

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
import static org.hamcrest.CoreMatchers.equalTo;

public class GetRequest13 {

    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url'ine bir GET request gonderdigimizde
    donen response'un asagidaki gibi oldugunu test edin.
    Response Body
            {
            "status":"success",
            "data":{
                    "id":3,
                    "employee_name":"Ashton Cox",
                    "employee_salary":86000,
                    "employee_age":66,
                    "profile_image":""
                    },
           "message":"Successfully! Record has been fetched."
           }
     */

    private RequestSpecification xxx;

    @BeforeTest
    public void setUp(){

        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){

        xxx.pathParams("parametre1","employee","parametre2",3);

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("status","success");
        expectedData.put("id",3);
        expectedData.put("employee_name","Ashton Cox");
        expectedData.put("employee_salary","86000");
        expectedData.put("employee_age",66);
        expectedData.put("profile_image","");
        expectedData.put("message","Successfully! Record has been fetched.");

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .spec(xxx)
                        .when()
                        .get("/{parametre1}/{parametre2}");

        JsonPath jsonPath = response.jsonPath();
        response.then().assertThat().statusCode(200).contentType(ContentType.JSON);

        response.then().assertThat().body("status",equalTo(expectedData.get("status")));
        response.then().assertThat().body("id",equalTo(expectedData.get("id")));
        response.then().assertThat().body("employee_name",equalTo(expectedData.get("employee_name")));
        response.then().assertThat().body("employee_salary",equalTo(expectedData.get("employee_salary")));
        response.then().assertThat().body("employee_age",equalTo(expectedData.get("employee_age")));
        response.then().assertThat().body("profile_image",equalTo(expectedData.get("profile_image")));
        response.then().assertThat().body("message",equalTo(expectedData.get("message")));

        Assert.assertEquals(expectedData.get("status"),jsonPath.getString("status"));
        Assert.assertEquals(expectedData.get("id"),jsonPath.getInt("id"));
        Assert.assertEquals(expectedData.get("employee_name"),jsonPath.getString("employee_name"));
        Assert.assertEquals(expectedData.get("employee_salary"),jsonPath.getInt("employee_salary"));
    }
}
