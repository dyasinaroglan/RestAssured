package restAssuredTraining.getRequest15_Matcher;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.ResponseSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class GetRequest15 extends FakeTestData15 {

    /*  http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
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
    Response response;

    @BeforeTest
    public void setUp(){

        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }
    @Test
    public void test1(){

        xxx.pathParam("parametre","employees");
        response =
                 given()
                        .accept(ContentType.JSON)
                        .spec(xxx)
                        .when()
                        .get("/{parametre}");
        //ben FakeTestData15 Class'ında oluşturduğum setupTestData methodumu bu class da kullanabilmek için FakeTestData15 class'ından
        //fakeTestData15 nesnesi oluşturuyorum ki setupTestData methodunu burada kullanabileyim.
        FakeTestData15 fakeTestData15 = new FakeTestData15();
        HashMap<String,Object> dataMap = (HashMap<String, Object>) fakeTestData15.setupTestData();

        response.then().assertThat()
                .body("data[4].employee_name",equalTo(dataMap.get("besinciCalısanIsmi")));

        response.then().assertThat()
                .body("data[-2].employee_salary",equalTo(dataMap.get("sondan2calısanınMaası")));

        response.then().assertThat()
                .body("data.employee_age",hasItem(((List)dataMap.get("arananYaslar")).get(0)),
                        "data.employee_age",hasItem(((List)dataMap.get("arananYaslar")).get(1)));

        response.then().assertThat()
                .body("data[10].employee_name",equalTo(((Map)dataMap.get("11CalısanBilgileri")).get("employee_name")));

        response.then().assertThat()
                .body("data[10].employee_salary",equalTo(((Map)dataMap.get("11CalısanBilgileri")).get("employee_salary")));
    }


}
