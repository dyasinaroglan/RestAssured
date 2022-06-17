package restAssuredTraining.getRequest8;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetRequest8 {

    /*
      http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
       1) Butun calisanlarin isimlerini consola yazdıralim
       2) 3. calisan kisinin ismini konsola yazdıralim
       3) Ilk 5 calisanin adini konsola yazdiralim
       4) En son calisanin adini konsola yazdiralim
     */

    private RequestSpecification xxx;

    @BeforeTest
    public void setUp(){

        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){
        xxx.pathParams("parametre","employees");
        Response response =
                given().accept(ContentType.JSON)
                        .spec(xxx)
                        .when().get("/{parametre}");
        response.then().log().body();


        System.out.println(response.jsonPath().getString("data.employee_name"));

        //3.çalışanın ismi
        System.out.println("3. kişi : " + response.jsonPath().getString("data[2].employee_name"));
        Assert.assertEquals("Ashton Cox",response.jsonPath().getString("data[2].employee_name"));

        System.out.println(response.jsonPath().getString("data.employee_name[0,1,2,3,4]"));

        System.out.println(response.jsonPath().getString("data.employee_name[-1]"));
        Assert.assertEquals("Doris Wilder", response.jsonPath().getString("data.employee_name[-1]"));
        Assert.assertTrue(response.jsonPath().getList("data.employee_name").contains("Rhona Davidson"));


    }
}
