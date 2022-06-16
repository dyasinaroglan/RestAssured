package restAssuredTraining.getRequest16;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends FakeTestData{

    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde

        Status kodun 200 olduğunu,
        En yüksek maaşın 725000 olduğunu,
        En küçük yaşın 19 olduğunu,
        İkinci en yüksek maaşın 675000
        olduğunu test edin

     */
    private RequestSpecification xxx;
    Response response;

    @BeforeTest
    public void setUp() {

        xxx = new RequestSpecBuilder().setBaseUri("http://dummy.restapiexample.com/api/v1").build();
    }

    @Test
    public void test1() {
        xxx.pathParam("parametre", "employees");
        response =
                given()
                        .accept(ContentType.JSON)
                        .spec(xxx)
                        .when()
                        .get("/{parametre}");

        FakeTestData fakeTestData = new FakeTestData();
        HashMap<String,Object> dataMap = (HashMap<String, Object>) fakeTestData.setupData();
        //------------------1.yöntem(json)--------------------

        JsonPath jsonPath = response.jsonPath();
        //A) En yüksek maaş
        List<Integer> salaries = jsonPath.getList("data.employee_salary"); // maaş listesini aldık
        Collections.sort(salaries); //Küçükten büyüğe sıraladık
        Assert.assertEquals(dataMap.get("enyuksekMaas"), salaries.get(salaries.size()-1));

        //B) En küçük yaş
        List<Integer> ages = jsonPath.getList("data.employee_age"); // yaş listesini aldık
        Collections.sort(ages); //Küçükten büyüğe sıraladık
        Assert.assertEquals(dataMap.get("enKucukYas"),ages.get(0));

        //C) ikinci en yüksek maaş
        List<Integer> salaries1 = jsonPath.getList("data.emloyee_salary");
        Collections.sort(salaries1);
        Assert.assertEquals(dataMap.get("ikinciEnYüksekMaas"),salaries1.get(salaries1.size())-2);

    }

}
