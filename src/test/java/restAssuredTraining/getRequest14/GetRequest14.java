package restAssuredTraining.getRequest14;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends TestDataClass {

    /* http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
       Status kodun 200 olduğunu,
       5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
       Sondan 2. çalışanın maaşının 106450 olduğunu
       40,21 ve 19 yaslarında çalışanlar olup olmadığını
       11. Çalışan bilgilerinin
      {
       “id”:”11”
       "employee_name": "Jena Gaines",
       "employee_salary": "90560",
       "employee_age": "30",
       "profile_image": "" }
    }
    gibi olduğunu test edin.

    */
    private RequestSpecification xxx;

    @BeforeTest
    public void setUp(){

        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){

        xxx.pathParam("parametre","employees");

        Response response =
                given()
                        .accept(ContentType.JSON)
                        .spec(xxx)
                        .when().get("/{parametre}");

        JsonPath jsonPath = response.jsonPath();
        TestDataClass testDataClass = new TestDataClass();

        HashMap<String,Object> expectedDataMap = (HashMap<String, Object>) testDataClass.setupTestData();

        HashMap<String,Object> actualData = response.as(HashMap.class);

        //5. Çalışan isminin "Airi Satou" olduğunu
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"),((Map)((List) actualData.get("data")).get(4)).get("employee_name"));



    }
}
