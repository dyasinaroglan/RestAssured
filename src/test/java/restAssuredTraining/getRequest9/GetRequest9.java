package restAssuredTraining.getRequest9;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest9 {

    /*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde,
    status kodun 200,
    gelen body de,
    5. çalışanın isminin "Airi Satou" olduğunu ,
    6. çalışanın maaşının "372000" olduğunu ,
    Toplam 24 tane çalışan olduğunu,
    "Rhona Davidson" ın employee lerden biri olduğunu
    "21", "23", "61" yaşlarında employeeler olduğunu test edin
     */

    private RequestSpecification xxx;

    @BeforeTest
    public void setUp(){

        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){

        xxx.pathParams("parametre","employees");

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .spec(xxx).get("/{parametre}");

        JsonPath jsonPath = response.jsonPath();
        response.then().assertThat().statusCode(200);
        // ya da
        Assert.assertEquals(200,response.getStatusCode()); //şeklinde de yazabilirim
        Assert.assertEquals("application/json",response.getContentType());

        //toplam 24 tane çalışanın olduğunu
        System.out.println(jsonPath.getList("data.id").size());
        Assert.assertEquals(24,jsonPath.getList("data.id").size());

        Assert.assertEquals("Airi Satou",jsonPath.getString("data[4].employee_name"));
        Assert.assertEquals(372000,jsonPath.getInt("data[5].employee_salary"));

        Assert.assertTrue(jsonPath.getList("data.employee_name").contains("Rhona Davidson"));

        List<Integer> arananYaslar = Arrays.asList(21,23,61);

        Assert.assertTrue(jsonPath.getList("data.emloyee_age").containsAll(arananYaslar));


    }
}
